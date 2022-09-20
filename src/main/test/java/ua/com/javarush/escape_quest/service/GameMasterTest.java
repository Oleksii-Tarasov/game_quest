package ua.com.javarush.escape_quest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.javarush.escape_quest.model.Character;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GameMasterTest {
    private final GameMaster gameMaster = GameMaster.getGameMaster();

    @Test
    void isGetGameMaster_Returns_SingletonInstance() {
        GameMaster gameMaster1 = GameMaster.getGameMaster();
        GameMaster gameMaster2 = GameMaster.getGameMaster();

        assertEquals(gameMaster1, gameMaster2);
    }

    @Test
    void testLoadGameCharacter_ShouldCreateNewCharacterFromTemplateByNickname() {
        //create first character:
        Character expectedFirstCharacter = new Character(1, "user1", 3);
        gameMaster.loadGameLocations();
        expectedFirstCharacter.setGameLocations(gameMaster.getGameLocations());

        String nickname = "user1";
        Character actualFirstCharacter = gameMaster.loadGameCharacter(nickname);

        assertEquals(expectedFirstCharacter, actualFirstCharacter);

        //create second character:
        Character expectedSecondCharacter = new Character(2, "user2", 3);
        expectedSecondCharacter.setGameLocations(gameMaster.getGameLocations());

        nickname = "user2";
        Character actualSecondCharacter = gameMaster.loadGameCharacter(nickname);

        assertEquals(expectedSecondCharacter, actualSecondCharacter);
    }

    @ParameterizedTest
    @MethodSource("charactersForResetCharacterStatsTest")
    void testResetCharacterStats_ShouldResetCharacterToDefaultSettings(Character actualCharacter) {
        int expectedAmountOfLives = 3;
        int expectedInventorySize = 0;

        gameMaster.resetCharacterStats(actualCharacter);

        assertEquals(expectedAmountOfLives, actualCharacter.getAmountOfLives());
        assertEquals(expectedInventorySize, actualCharacter.getInventory().size());
        assertFalse(actualCharacter.isWinner());
    }

    static Stream<Character> charactersForResetCharacterStatsTest() {
        Character character1 = new Character(1, "user", 2);
        character1.getInventory().add("sword");
        character1.setWinner(true);

        Character character2 = new Character(1, "user", 0);
        character2.getInventory().add("sword");
        character2.getInventory().add("pizza");
        character2.getInventory().add("book");
        character2.setWinner(false);

        Character character3 = new Character(1, "user", 99);

        return Stream.of(
                character1,
                character2,
                character3
        );
    }

    @ParameterizedTest
    @CsvSource({
            "mainhall, sword",
            "firehall, pizza",
            "dungeon, waterBucket"
    })
    void testMoveItemFromLocationToCharacterInventory(String locationId, String itemId) {
        Character character = new Character(1, "user", 3);
        gameMaster.loadGameLocations();
        character.setGameLocations(gameMaster.getGameLocations());

        gameMaster.moveItemFromLocationToCharacterInventory(character, locationId, itemId);

        boolean isInventoryHasRightItem = character.getInventory().contains(itemId);
        assertTrue(isInventoryHasRightItem);

        boolean isLocationHasDisplacedItem = character.getGameLocations().get(locationId).getItemsInLocation().contains("sword");
        assertFalse(isLocationHasDisplacedItem);

    }

    @Test
    void testShowItems_ShouldCreateMapWithItems_FromItemsIdList() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("sword", "Old Sword");
        expectedMap.put("pizza", "Pizza slice");
        expectedMap.put("book", "Poetry book");

        List<String> itemsIdList = List.of("sword", "pizza", "book");

        gameMaster.loadGameItems();
        Map<String, String> actualMap = gameMaster.showItems(itemsIdList);

        assertEquals(expectedMap, actualMap);
    }

    @ParameterizedTest
    @MethodSource("argumentsForReduceLivesTest")
    void testAttackBossAndGetResult_ShouldReducePlayerLives_WhenPlayerUseIncorrectItem(Character character, String itemId, int expectedAmountOfLives) {
        gameMaster.loadGameItems();
        gameMaster.attackBossAndGetResult(character, itemId);

        int actualAmountOfLives = character.getAmountOfLives();

        assertEquals(expectedAmountOfLives, actualAmountOfLives);
    }

    static Stream<Arguments> argumentsForReduceLivesTest() {
        Character character1 = new Character(1, "user1", 0);
        int expectedAmountOfLives1 = -1;
        Character character2 = new Character(1, "user2", 6);
        int expectedAmountOfLives2 = 6;
        Character character3 = new Character(1, "user3", 100);
        int expectedAmountOfLives3 = 99;

        return Stream.of(
                Arguments.of(character1, "sword", expectedAmountOfLives1),
                Arguments.of(character2, "waterBucket", expectedAmountOfLives2),
                Arguments.of(character3, "book", expectedAmountOfLives3)
        );
    }

    @Test
    void testCanCharacterFight_ReturnTrue_WhenBattleTriesMoreThanZero_AndInventoryNotEmpty() {
        Character character = new Character(1, "user", 1);
        character.getInventory().add("sword");

        boolean actualResult = gameMaster.canCharacterFight(character);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @MethodSource("charactersForCanCharacterFightTest")
    void testCanCharacterFight_ReturnFalse_WhenBattleTriesLessThanZero_OrInventoryIsEmpty(Character character) {
        boolean actualResult = gameMaster.canCharacterFight(character);

        assertFalse(actualResult);
    }

    static Stream<Character> charactersForCanCharacterFightTest() {
        Character character1 = new Character(1, "user1", 0);
        character1.getInventory().add("sword");

        Character character2 = new Character(1, "user2", 1);

        Character character3 = new Character(1, "user3", -1);

        return Stream.of(
                character1,
                character2,
                character3
        );
    }

    //countBattleTries tests:
    @Test
    void IfInventorySizeGreaterThanAmountOfLives_ThenBattleTriesEqualsAmountOfLives() {
        Character character = new Character(1, "user", 2);
        character.getInventory().add("sword");
        character.getInventory().add("book");
        character.getInventory().add("pizza");
        character.getInventory().add("textbook");

        int expectedBattleTries = 2;
        int actualBattleTries = gameMaster.countBattleTries(character);

        assertEquals(expectedBattleTries, actualBattleTries);
    }

    @Test
    void IfInventorySizeLessThanAmountOfLives_ThenBattleTriesEqualsInventorySize() {
        Character character = new Character(1, "user", 3);
        character.getInventory().add("sword");

        int expectedBattleTries = 1;
        int actualBattleTries = gameMaster.countBattleTries(character);

        assertEquals(expectedBattleTries, actualBattleTries);
    }
    //countBattleTries tests end.

    //calculateStatistics tests:
    @Test
    void ifCharacterWinnerIsTrue_ThenIncreaseGoodEndsNumber() {
        Character character = new Character(1, "user", 3);
        int goodEndsNumber = 5;
        character.setGoodEndsNumber(goodEndsNumber);
        character.setWinner(true);
        int expectedGoodEndsNumber = 6;

        gameMaster.calculateStatistics(character);
        int actualGoodEndsNumber = character.getGoodEndsNumber();

        assertEquals(expectedGoodEndsNumber, actualGoodEndsNumber);
    }

    @Test
    void ifCharacterWinnerIsFalse_ThenIncreaseBadEndsNumber() {
        Character character = new Character(1, "user", 3);
        int badEndsNumber = 0;
        character.setBadEndsNumber(badEndsNumber);
        character.setWinner(false);
        int expectedBadEndsNumber = 1;

        gameMaster.calculateStatistics(character);
        int actualBadEndsNumber = character.getBadEndsNumber();

        assertEquals(expectedBadEndsNumber, actualBadEndsNumber);
    }

    @Test
    void testCalculateStatistics_ShouldIncreaseGameAttempts() {
        Character character = new Character(1, "user", 3);
        character.setGameAttempt(99);
        //for winner:
        character.setWinner(true);
        int expectedGameAttemptForWinner = 100;

        gameMaster.calculateStatistics(character);
        int actualGameAttemptForWinner = character.getGameAttempt();

        assertEquals(expectedGameAttemptForWinner, actualGameAttemptForWinner);

        //for loser:
        character.setWinner(false);
        int expectedGameAttemptForLoser = 101;

        gameMaster.calculateStatistics(character);
        int actualGameAttemptForLoser = character.getGameAttempt();

        assertEquals(expectedGameAttemptForLoser, actualGameAttemptForLoser);
    }
    //calculateStatistics tests end.
}
