package ua.com.javarush.escape_quest.service;

import lombok.Data;
import ua.com.javarush.escape_quest.configuration.ItemProperties;
import ua.com.javarush.escape_quest.configuration.ModelConfig;
import ua.com.javarush.escape_quest.configuration.LocationProperties;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.com.javarush.escape_quest.constant.ResourceFilePath.ITEMS_FILE_PATH;
import static ua.com.javarush.escape_quest.constant.ResourceFilePath.LOCATIONS_FILE_PATH;

@Data
public class GameConstructor {
    private ResourceLoader resourceLoader;
    private long characterId;
    public GameConstructor(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Map<String, Location> createLocations() {
        Map<String, Location> gameLocations = new HashMap<>();
        ModelConfig locationConfig = resourceLoader.loadResourcesFromFile(LOCATIONS_FILE_PATH);

        for (LocationProperties properties: locationConfig.getLocationProperties()) {
            Location location = new Location(properties.getLocationId(), properties.getStoryBlock(), properties.getImage(), properties.getSound(), properties.getItemsInLocation());
            gameLocations.put(properties.getLocationId(), location);
        }

        return gameLocations;
    }

    public Map<String, Item> createItems() {
        Map<String, Item> gameItems = new HashMap<>();
        ModelConfig itemConfig = resourceLoader.loadResourcesFromFile(ITEMS_FILE_PATH);

        for (ItemProperties properties: itemConfig.getItemProperties()) {
            Item item = new Item(properties.getItemId(), properties.getDescription(), properties.getEffect());
            gameItems.put(properties.getItemId(), item);
        }

        return gameItems;
    }

    public Character createCharacter(long characterId, String nickname) {
        if (("<enter your name>").equals(nickname)) {
            nickname = "Unknown Hero";
        }

        int amountOfLives = 3;

        Character character = new Character(characterId, nickname, amountOfLives);

        return character;
    }
}
