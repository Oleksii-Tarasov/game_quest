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
        Arrays.stream(items).forEach(itemId -> character.getInventory().add(itemId));
    }

    public void removeItemsFromLocation(String locationId, String[] items) {
        Arrays.stream(items).forEach(item -> {
            Location location = gameLocations.get(locationId);
            location.getItemsInLocation().remove(item);
        });
    }

    public void rememberCurrentLocation(String locationId) {
        character.setCurrentLocation(locationId);
    }

    public Map<String, String> showItemsInLocation(String locationId) {
        return gameLocations.get(locationId).getItemsInLocation().stream()
                .collect(Collectors.toMap(
                        item -> gameItems.get(item).getItemId(),
                        item -> gameItems.get(item).getDescription())
                );
    }

    public Map<String, String> showCharacterInventory() {
        return character.getInventory().stream()
                .collect(Collectors.toMap(
                        itemId -> gameItems.get(itemId).getItemId(),
                        itemId -> gameItems.get(itemId).getDescription()
                ));
    }

    public void dontShowCharacterInventory() {
        character.getInventory().clear();
    }

    public String attackBossAndGetResult(String itemId) {
        Item item = gameItems.get(itemId);

        if ("waterBucket".equals(itemId)) {
            character.setWinner(true);
            return item.getEffect();
        }

        int amountOfLives = character.getAmountOfLives();
        amountOfLives--;
        character.setAmountOfLives(amountOfLives);

        character.getInventory().remove(itemId);

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
