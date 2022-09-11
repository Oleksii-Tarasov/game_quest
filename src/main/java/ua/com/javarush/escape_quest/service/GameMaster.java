package ua.com.javarush.escape_quest.service;

import lombok.Data;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class GameMaster {
    private static GameMaster gameMaster;
    private final GameConstructor gameConstructor = new GameConstructor(new ResourceLoader());
    private Character character;
    private Map<String, Location> gameLocations;
    private Map<String, Item> gameItems;

    private GameMaster() {
    }

    public static GameMaster getGameMaster() {
        if (gameMaster == null) {
            gameMaster = new GameMaster();
        }
        return gameMaster;
    }

    public void createGameWorld() {
        gameLocations = gameConstructor.createLocations();
        gameItems = gameConstructor.createItems();
    }

    public void createCharacter(String nickname) {
        if (("<enter your name>").equals(nickname)) {
            nickname = "Unknown Hero";
        }

        int amountOfLives = 3;

        character = new Character(nickname, amountOfLives);
    }


    public void addItemsToCharacterInventory(String[] items) {
        Arrays.stream(items).forEach(it -> {
            Item item = gameItems.get(it);
            character.getInventory().put(item.getTitle(), item);
        });
    }

    public void removeItemsFromLocation(String locationTitle, String[] items) {
        Arrays.stream(items).forEach(item -> {
            Location location = gameLocations.get(locationTitle);
            location.getItemsInLocation().remove(item);
        });
    }

    public void rememberCurrentLocation(String locationTitle) {
        character.setCurrentLocation(locationTitle);
    }

    public Map<String, String> showItemsInLocation(String locationTitle) {
        return gameLocations.get(locationTitle).getItemsInLocation().stream()
                .collect(Collectors.toMap(
                        item -> gameItems.get(item).getTitle(),
                        item -> gameItems.get(item).getDescription())
                );
    }

    public Map<String, String> showCharacterInventory() {
        return character.getInventory().entrySet().stream()
                .collect(Collectors.toMap(
                        item -> item.getValue().getTitle(),
                        item -> item.getValue().getDescription())
                );
    }

    public void dontShowCharacterInventory() {
        character.getInventory().clear();
    }

    public String attackBossAndGetResult(String itemTitle) {
        Item item = gameItems.get(itemTitle);

        if ("waterBucket".equals(itemTitle)) {
            character.setWinner(true);
            return item.getEffect();
        }

        int amountOfLives = character.getAmountOfLives();
        amountOfLives--;
        character.setAmountOfLives(amountOfLives);

        character.getInventory().remove(itemTitle);

        return item.getEffect();
    }

    public boolean canCharacterFight() {
        return countTries() > 0 && !character.getInventory().isEmpty();
    }

    public int countTries() {
        int tries = character.getInventory().size();
        int amountOfLives = character.getAmountOfLives();

        if (tries < amountOfLives) {
            amountOfLives = tries;
        }

        return amountOfLives;
    }
}
