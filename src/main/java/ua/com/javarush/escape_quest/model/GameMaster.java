package ua.com.javarush.escape_quest.model;

import lombok.Data;
import ua.com.javarush.escape_quest.service.GameConstructor;
import ua.com.javarush.escape_quest.service.ResourceLoader;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.com.javarush.escape_quest.constant.BossFightMessage.*;

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

    public Map<String, String> showCharacterInventory() {
        return character.getInventory().entrySet().stream()
                .collect(Collectors.toMap(
                        item -> item.getValue().getTitle(),
                        item -> item.getValue().getDescription())
                );
    }

    public Map<String, String> showItemsInLocation(String locationTitle) {
        return gameLocations.get(locationTitle).getItemsInLocation().stream()
                .collect(Collectors.toMap(
                        item -> gameItems.get(item).getTitle(),
                        item -> gameItems.get(item).getDescription())
                );
    }

    public String attackBossAndGetResult(String itemTitle) {
        character.getInventory().remove(itemTitle);
        Item item = gameItems.get(itemTitle);

        return item.getEffect();
    }

    public boolean isCharacterLoser() {
        int amountOfLives = character.getAmountOfLives();
        amountOfLives--;
        character.setAmountOfLives(amountOfLives);

        return amountOfLives == 0 || character.getInventory().isEmpty();
    }

}
