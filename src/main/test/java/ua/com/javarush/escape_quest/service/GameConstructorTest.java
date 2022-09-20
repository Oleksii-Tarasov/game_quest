package ua.com.javarush.escape_quest.service;

import org.junit.jupiter.api.Test;
import ua.com.javarush.escape_quest.model.Character;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameConstructorTest {
    @Test
    void testCreateCharacter_ShouldCreateNewCharacterWithChosenNickName() {
        GameConstructor gameConstructor = new GameConstructor(new ResourceLoader());
        //create first character:
        Character expectedFirstCharacter = new Character(1, "user1", 3);

        String nickname = "user1";
        Character actualFirstCharacter = gameConstructor.createCharacter(nickname);

        assertEquals(expectedFirstCharacter, actualFirstCharacter);

        //create second character:
        Character expectedSecondCharacter = new Character(2, "user2", 3);

        nickname = "user2";
        Character actualSecondCharacter = gameConstructor.createCharacter(nickname);

        assertEquals(expectedSecondCharacter, actualSecondCharacter);
    }

    @Test
    void testCreateCharacter_IfNicknameNotSelected_ThenCreateCharacterWithDefaultNickname() {
        String expectedName = "Unknown Hero";
        Character character = new GameConstructor(new ResourceLoader()).createCharacter("<enter your name>");
        String actualName = character.getNickname();

        assertEquals(expectedName, actualName);
    }
}
