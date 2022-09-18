package ua.com.javarush.escape_quest.service;

import lombok.Data;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class GameMaster {
    private static GameMaster gameMaster;
    private final GameConstructor gameConstructor;
    private Map<Long, Character> gameCharacters;
    private Map<String, Location> gameLocations;
    private Map<String, Item> gameItems;

    private GameMaster() {
        this.gameConstructor = new GameConstructor(new ResourceLoader());
        this.gameCharacters = new HashMap<>();
    }

    public static GameMaster getGameMaster() {
        if (gameMaster == null) {
            gameMaster = new GameMaster();
        }
        return gameMaster;
    }

    public void loadGameLocations() {
        gameLocations = gameConstructor.createLocations();
    }

    public void loadGameItems() {
        gameItems = gameConstructor.createItems();
    }

    public Character loadGameCharacter(String nickname) {
        Character character = gameConstructor.createCharacter(nickname);
        character.setGameLocations(gameLocations);

        gameCharacters.put(character.getCharacterId(), character);

        return character;
    }

    public void resetCharacterStats(Character character) {
        character.setAmountOfLives(3);
        character.getInventory().clear();
        character.setWinner(false);
        loadGameLocations();
        character.setGameLocations(gameLocations);
    }

    public void moveItemFromLocationToCharacterInventory(Character character, String locationId, String itemId) {
        Location location = character.getGameLocations().get(locationId);
        character.getInventory().add(itemId);
        location.getItemsInLocation().remove(itemId);
    }

    public Map<String, String> showItemsInLocation(List<String> itemsInLocation) {
        return itemsInLocation.stream()
                .collect(Collectors.toMap(
                        item -> gameItems.get(item).getItemId(),
                        item -> gameItems.get(item).getDescription())
                );
    }

    public Map<String, String> showItemsInInventory(List<String> inventory) {
        return inventory.stream()
                .collect(Collectors.toMap(
                        itemId -> gameItems.get(itemId).getItemId(),
                        itemId -> gameItems.get(itemId).getDescription()
                ));
    }

    public String attackBossAndGetResult(Character character, String itemId) {
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

    public boolean canCharacterFight(Character character) {
        return countTries(character) > 0 && !character.getInventory().isEmpty();
    }

    public int countTries(Character character) {
        int battleTries = character.getInventory().size();
        int amountOfLives = character.getAmountOfLives();

        if (battleTries < amountOfLives) {
            amountOfLives = battleTries;
        }

        return amountOfLives;
    }

    public void calculateStatistics(Character character) {
        if (character.isWinner()) {
            int countGoodEnd = character.getGoodEndsNumber();
            countGoodEnd++;
            character.setGoodEndsNumber(countGoodEnd);
        } else {
            int countBadEnd = character.getBadEndsNumber();
            countBadEnd++;
            character.setBadEndsNumber(countBadEnd);
        }

        int countGameAttempt = character.getGameAttempt();
        countGameAttempt++;
        character.setGameAttempt(countGameAttempt);
    }
}
