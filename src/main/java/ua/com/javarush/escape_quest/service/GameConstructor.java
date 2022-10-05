package ua.com.javarush.escape_quest.service;

import lombok.Data;
import ua.com.javarush.escape_quest.configuration.ItemProperties;
import ua.com.javarush.escape_quest.configuration.LocationProperties;
import ua.com.javarush.escape_quest.configuration.ModelConfig;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;

import java.util.HashMap;
import java.util.Map;

import static ua.com.javarush.escape_quest.constant.CharacterRules.DEFAULT_NICKNAME;
import static ua.com.javarush.escape_quest.constant.CharacterRules.MAX_AMOUNT_OF_LIVES;
import static ua.com.javarush.escape_quest.constant.ResourceFilesPath.ITEMS_FILE_PATH;
import static ua.com.javarush.escape_quest.constant.ResourceFilesPath.LOCATIONS_FILE_PATH;

@Data
public class GameConstructor {
    private ResourceLoader resourceLoader;
    private long characterId;

    public GameConstructor(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.characterId = 0;
    }

    public Map<String, Location> createLocations() {
        Map<String, Location> gameLocations = new HashMap<>();
        ModelConfig locationConfig = resourceLoader.loadResourcesFromFile(LOCATIONS_FILE_PATH);

        for (LocationProperties properties : locationConfig.getLocationProperties()) {
            Location location = Location.builder()
                    .locationId(properties.getLocationId())
                    .storyBlock(properties.getStoryBlock())
                    .image(properties.getImage())
                    .sound(properties.getSound())
                    .itemsInLocation(properties.getItemsInLocation())
                    .build();

            gameLocations.put(properties.getLocationId(), location);
        }

        return gameLocations;
    }

    public Map<String, Item> createItems() {
        Map<String, Item> gameItems = new HashMap<>();
        ModelConfig itemConfig = resourceLoader.loadResourcesFromFile(ITEMS_FILE_PATH);

        for (ItemProperties properties : itemConfig.getItemProperties()) {
            Item item = Item.builder()
                    .itemId(properties.getItemId())
                    .description(properties.getDescription())
                    .effect(properties.getEffect())
                    .build();

            gameItems.put(properties.getItemId(), item);
        }

        return gameItems;
    }

    public Character createCharacter(String nickname) {
        if (("<enter your name>").equals(nickname)) {
            nickname = DEFAULT_NICKNAME;
        }

        characterId++;

        return new Character(characterId, nickname, MAX_AMOUNT_OF_LIVES);
    }
}
