package ua.com.javarush.escape_quest.service;

import lombok.Data;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;
import ua.com.javarush.escape_quest.repository.GameRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.com.javarush.escape_quest.constant.CharacterRules.MAX_AMOUNT_OF_LIVES;

@Data
public class GameMaster {
    private static GameMaster gameMaster;
    private final GameConstructor gameConstructor;
    private final GameRepository gameRepository;
    private Map<Long, Map<String, Location>> locationsForCharacter;

    private GameMaster() {
        this.gameConstructor = new GameConstructor(new ResourceLoader());
        this.gameRepository = new GameRepository();
        this.locationsForCharacter = new HashMap<>();
    }

    public static GameMaster getGameMaster() {
        if (gameMaster == null) {
            gameMaster = new GameMaster();
        }
        return gameMaster;
    }

    public void loadGameLocations() {
        gameRepository.setGameLocations(gameConstructor.createLocations());
    }

    public void loadGameItems() {
        gameRepository.setGameItems(gameConstructor.createItems());
    }

    public Character loadGameCharacter(String nickname) {
        Character character = gameConstructor.createCharacter(nickname);

        gameRepository.getGameCharacters().put(character.getCharacterId(), character);

        locationsForCharacter.put(character.getCharacterId(), gameRepository.getGameLocations());

        return character;
    }

    public void resetCharacterStats(Character character) {
        character.setAmountOfLives(MAX_AMOUNT_OF_LIVES);
        character.getInventory().clear();
        character.setWinner(false);
        loadGameLocations();
        locationsForCharacter.put(character.getCharacterId(), gameRepository.getGameLocations());
    }

    public void moveItemFromLocationToCharacterInventory(Character character, String locationId, String itemId) {
        Location location = locationsForCharacter.get(character.getCharacterId()).get(locationId);
        character.getInventory().add(itemId);
        location.getItemsInLocation().remove(itemId);
    }

    public Map<String, String> showItems(List<String> itemsIdList) {
        return itemsIdList.stream()
                .collect(Collectors.toMap(
                        item -> gameRepository.getGameItems().get(item).getItemId(),
                        item -> gameRepository.getGameItems().get(item).getDescription())
                );
    }

    public String attackBossAndGetResult(Character character, String itemId) {
        Item item = gameRepository.getGameItems().get(itemId);

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
