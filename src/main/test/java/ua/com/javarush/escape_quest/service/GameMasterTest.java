package ua.com.javarush.escape_quest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
    void testCanCharacterFight_ReturnTrue_WhenTriesMoreThanZero_And_InventoryNotEmpty() {
        Character character = new Character(1, "user", 1);
        character.getInventory().add("sword");

        boolean actualResult = gameMaster.canCharacterFight(character);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @MethodSource("charactersForCanCharacterFightTest")
    void testCanCharacterFight_ReturnFalse_WhenTriesLessThanZero_Or_InventoryIsEmpty(Character character) {
        boolean actualResult = gameMaster.canCharacterFight(character);

        assertFalse(actualResult);
    }

    static Stream<Arguments> charactersForCanCharacterFightTest() {
        Character character1 = new Character(1, "user1", 0);
        character1.getInventory().add("sword");

        Character character2 = new Character(1, "user2", 1);

        Character character3 = new Character(1, "user3", -1);

        return Stream.of(
                Arguments.of(character1),
                Arguments.of(character2),
                Arguments.of(character3)
        );
    }

}
