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
    private Map<Long, Map<String, Location>> locationsForCharacters;

    private GameMaster() {
        this.gameConstructor = new GameConstructor(new ResourceLoader());
        this.gameCharacters = new HashMap<>();
        this.locationsForCharacters = new HashMap<>();
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

        gameCharacters.put(character.getCharacterId(), character);

        locationsForCharacters.put(character.getCharacterId(), gameLocations);

        return character;
    }

    public void resetCharacterStats(Character character) {
        character.setAmountOfLives(3);
        character.getInventory().clear();
        character.setWinner(false);
        loadGameLocations();
        locationsForCharacters.put(character.getCharacterId(), gameLocations);
    }

    public void moveItemFromLocationToCharacterInventory(Character character, String locationId, String itemId) {
        Location location = locationsForCharacters.get(character.getCharacterId()).get(locationId);
        character.getInventory().add(itemId);
        location.getItemsInLocation().remove(itemId);
    }

    public Map<String, String> showItems(List<String> itemsIdList) {
        return itemsIdList.stream()
                .collect(Collectors.toMap(
                        item -> gameItems.get(item).getItemId(),
                        item -> gameItems.get(item).getDescription())
                );
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
        return countBattleTries(character) > 0 && !character.getInventory().isEmpty();
    }

    public int countBattleTries(Character character) {
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
