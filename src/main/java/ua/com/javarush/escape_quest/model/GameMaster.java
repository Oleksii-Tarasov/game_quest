package ua.com.javarush.escape_quest.model;

import lombok.Data;
import ua.com.javarush.escape_quest.service.GameConstructor;
import ua.com.javarush.escape_quest.service.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

import static ua.com.javarush.escape_quest.constant.ResourceFilePath.ITEMS_FILE_PATH;
import static ua.com.javarush.escape_quest.constant.ResourceFilePath.LOCATIONS_FILE_PATH;

@Data
public class GameMaster {
    private static GameMaster gameMaster;
    private GameConstructor gameConstructor;
    private final ResourceLoader resourceLoader;
    private String playerNickname;
    private Map<String, String> playerInventory;
    private HashMap gameItems;
    private HashMap gameLocations;
    private String currentLocation;
    private int tries;

    private GameMaster() {
        resourceLoader = new ResourceLoader();
        playerInventory = new HashMap<>();
        gameConstructor = new GameConstructor(new ResourceLoader());
    }

    public static GameMaster getGameMaster() {
        if (gameMaster == null) {
            gameMaster = new GameMaster();
        }
        return gameMaster;
    }





    public void addItemToPlayerInventory(String item, String itemDescription) {
        playerInventory.put(item, itemDescription);
    }

    public void loadGameItems() {
        this.gameItems = resourceLoader.loadGameResourcesFromFile(ITEMS_FILE_PATH);
    }
}
