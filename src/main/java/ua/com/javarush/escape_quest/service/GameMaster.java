package ua.com.javarush.escape_quest.service;

import lombok.Data;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class GameMaster {
    private static GameMaster gameMaster;
    private final GameConstructor gameConstructor = new GameConstructor(new ResourceLoader());
    private Map<Long, Character> gameCharacters = new HashMap<>();
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

    public void loadGameLocations() {
        gameLocations = gameConstructor.createLocations();
    }

    public void loadGameItems() {
        gameItems = gameConstructor.createItems();
    }

    public void loadCharacter(long characterId, String nickname) {
        gameCharacters.put(characterId, gameConstructor.createCharacter(characterId, nickname));
    }

    public void setLocationsForCurrentCharacter(long characterId) {
        Character character = gameCharacters.get(characterId);

        Map<String, Location> gameLocationsForCurrentCharacter = gameLocations.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        character.setGameLocations(gameLocationsForCurrentCharacter);
    }

    public Location getLocationForCurrentCharacter(Character character, String locationId) {
        return character.getGameLocations().get(locationId);
    }

    public void resetCharacterStats(Character character) {
        character.setAmountOfLives(3);
        character.getInventory().clear();
        character.setWinner(false);
//        loadGameLocations();
        setLocationsForCurrentCharacter(character.getId());
    }

    public void addItemsToCharacterInventory(Character character, String[] items) {
        Arrays.stream(items).forEach(itemId -> character.getInventory().add(itemId));
    }

    public void removeItemsFromCharacterLocation(Character character, String locationId, String[] items) {
        Arrays.stream(items).forEach(item -> {
            Location location = character.getGameLocations().get(locationId);
            location.getItemsInLocation().remove(item);
        });
    }

    public Map<String, String> showItemsInLocation(Location location) {
        return location.getItemsInLocation().stream()
                .collect(Collectors.toMap(
                        item -> gameItems.get(item).getItemId(),
                        item -> gameItems.get(item).getDescription())
                );
    }

    public Map<String, String> showCharacterInventory(Character character) {
        return character.getInventory().stream()
                .collect(Collectors.toMap(
                        itemId -> gameItems.get(itemId).getItemId(),
                        itemId -> gameItems.get(itemId).getDescription()
                ));
    }

    public void dontShowCharacterInventory(Character character) {
        character.getInventory().clear();
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
        int tries = character.getInventory().size();
        int amountOfLives = character.getAmountOfLives();

        if (tries < amountOfLives) {
            amountOfLives = tries;
        }

        return amountOfLives;
    }
}
