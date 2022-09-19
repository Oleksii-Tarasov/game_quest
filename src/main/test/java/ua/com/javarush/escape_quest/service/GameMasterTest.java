package ua.com.javarush.escape_quest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameMasterTest {
    private final GameMaster gameMaster = GameMaster.getGameMaster();

    @Test
    public void isGetGameMaster_Returns_SingletonInstance() {
        GameMaster gameMaster1 = GameMaster.getGameMaster();
        GameMaster gameMaster2 = GameMaster.getGameMaster();

        Assertions.assertEquals(gameMaster1, gameMaster2);
    }

//    @Test
//    public void isLoadGameCharacter_Create_NewCharacterByTemplate() {
//        String nickname = "user";
//        long id = 1;
//        int amountOfLives = 3;
//
//        Character character1 = new Character(id, nickname, amountOfLives);
//
//        gameMaster.loadGameLocations();
//        character1.setGameLocations(gameMaster.getGameLocations());
//
//        Character character2 = gameMaster.loadGameCharacter("user");
//
//        Assertions.assertEquals(character1, character2);
//    }

    @Test
    public void testMoveItemFromLocationToCharacterInventory_ByItemId() {
        List<String> itemsInLocation = new ArrayList<>();
        itemsInLocation.add("sword");

        Location testLocation = new Location("mainhall", "/view/mainhall.jsp", "/img/mainhall.jpg", "", itemsInLocation);
        Character testCharacter = new Character(1,"user", 3);

        testCharacter.getInventory().add("sword");
        testLocation.getItemsInLocation().remove("sword");

        List<String> testInventory = testCharacter.getInventory();
        List<String> testItemsInLocation = testLocation.getItemsInLocation();

        gameMaster.loadGameLocations();
        Character expectedCharacter = gameMaster.loadGameCharacter("user");
        String locationId = "mainhall";
        String itemId = "sword";
        gameMaster.moveItemFromLocationToCharacterInventory(expectedCharacter, locationId, itemId);

        List<String> expectedInventory = expectedCharacter.getInventory();
        List<String> expectedItemsInLocation = expectedCharacter.getGameLocations().get("mainhall").getItemsInLocation();

        Assertions.assertEquals(testInventory, expectedInventory);
        Assertions.assertEquals(testItemsInLocation, expectedItemsInLocation);
    }
}
