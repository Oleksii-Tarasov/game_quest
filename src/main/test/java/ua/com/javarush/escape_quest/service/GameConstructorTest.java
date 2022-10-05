package ua.com.javarush.escape_quest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameConstructorTest {
    private final GameConstructor gameConstructor = new GameConstructor(new ResourceLoader());

    @ParameterizedTest
    @MethodSource("argumentsForCreateLocationsTest")
    void testCreateLocations_ShouldCreateLocationsFromTemplate(String locationId, String storyBlock, String image, String sound, List<String> itemsInLocation) {
        Location expectedLocation = new Location(locationId, storyBlock, image, sound, itemsInLocation);

        Map<String, Location> actualLocationMap = gameConstructor.createLocations();

        Location actualLocation = actualLocationMap.get(locationId);

        assertEquals(expectedLocation.getLocationId(), actualLocation.getLocationId());
        assertEquals(expectedLocation.getStoryBlock(), actualLocation.getStoryBlock());
        assertEquals(expectedLocation.getImage(), actualLocation.getImage());
        assertEquals(expectedLocation.getSound(), actualLocation.getSound());
        assertEquals(expectedLocation.getItemsInLocation(), actualLocation.getItemsInLocation());
    }

    static Stream<Arguments> argumentsForCreateLocationsTest() {
        return Stream.of(
                //location with sound/without item
                Arguments.of("prison", "/view/prison.jsp", "/img/prison.jpg", "/sound/prison.mp3", new ArrayList<>()),
                //location without sound/with item
                Arguments.of("mainhall", "/view/mainhall.jsp", "/img/mainhall.jpg", "", List.of("sword"))
        );
    }

    @ParameterizedTest
    @MethodSource("itemsForCreateItemsTest")
    void testCreateItems_ShouldCreateItemsFromTemplate(String itemId, Item expectedItem) {
        Map<String, Item> actualItemMap = gameConstructor.createItems();

        Item actualItem = actualItemMap.get(itemId);

        assertEquals(expectedItem.getItemId(), actualItem.getItemId());
        assertEquals(expectedItem.getDescription(), actualItem.getDescription());
        assertEquals(expectedItem.getEffect(), actualItem.getEffect());
    }

    static Stream<Arguments> itemsForCreateItemsTest() {
        Item item1 = new Item("pizza", "Pizza slice", "Lord of the Underworld is not hungry");
        Item item2 = new Item("book", "Poetry book", "You recite a verse from a book, but the Lord of the Underworld does not get kinder");

        return Stream.of(
                Arguments.of("pizza", item1),
                Arguments.of("book", item2)
        );
    }

    @Test
    void testCreateCharacter_ShouldCreateNewCharacterWithChosenNickName() {
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
