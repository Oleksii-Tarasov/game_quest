package ua.com.javarush.escape_quest.service;

import lombok.Data;
import ua.com.javarush.escape_quest.configuration.ItemProperties;
import ua.com.javarush.escape_quest.configuration.ModelConfig;
import ua.com.javarush.escape_quest.configuration.LocationProperties;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;

import java.util.HashMap;
import java.util.Map;

import static ua.com.javarush.escape_quest.constant.ResourceFilePath.ITEMS_FILE_PATH;
import static ua.com.javarush.escape_quest.constant.ResourceFilePath.LOCATIONS_FILE_PATH;

@Data
public class GameConstructor {
    private ResourceLoader resourceLoader;
    private Map<String, Location> gameLocations;
    private Map<String, Item> gameItems;

    public GameConstructor(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void createLocations() {
        gameLocations = new HashMap<>();
        ModelConfig locationConfig = resourceLoader.loadResourcesFromFile(LOCATIONS_FILE_PATH);

        for (LocationProperties properties: locationConfig.getLocationProperties()) {
            Location location = new Location(properties.getTitle(), properties.getPagePath(), properties.getImagePath(), properties.getSoundPath());
            gameLocations.put(properties.getTitle(), location);
        }
    }

    public void createItems() {
        gameItems = new HashMap<>();
        ModelConfig itemConfig = resourceLoader.loadResourcesFromFile(ITEMS_FILE_PATH);

        for (ItemProperties properties: itemConfig.getItemProperties()) {
            Item item = new Item(properties.getTitle(), properties.getDescription());
            gameItems.put(properties.getTitle(), item);
        }
    }
}
