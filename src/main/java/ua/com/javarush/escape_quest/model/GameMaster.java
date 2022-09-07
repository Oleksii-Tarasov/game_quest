package ua.com.javarush.escape_quest.model;

import ua.com.javarush.escape_quest.service.ItemLoader;

import java.util.HashMap;
import java.util.Map;

import static ua.com.javarush.escape_quest.constant.ResourceFilePath.ITEMS_FILE_PATH;
import static ua.com.javarush.escape_quest.constant.ResourceFilePath.LOCATIONS_FILE_PATH;

public class GameMaster {
    private static GameMaster gameMaster;
    private final ItemLoader itemLoader;
    private String playerNickname;
    private Map<String, String> playerInventory;
    private HashMap gameItems;
    private HashMap gameLocations;
    private String currentLocation;
    private int tries;

    private GameMaster() {
        itemLoader = new ItemLoader();
        playerInventory = new HashMap<>();
    }

    public static GameMaster getGameMaster() {
        if (gameMaster == null) {
            gameMaster = new GameMaster();
        }
        return gameMaster;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public Map<String, String> getPlayerInventory() {
        return playerInventory;
    }

    public void addItemToPlayerInventory(String item, String itemDescription) {
        playerInventory.put(item, itemDescription);
    }

    public HashMap getGameItems() {
        return this.gameItems;
    }


    public HashMap getGameLocations() {
        return gameLocations;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public void loadGameLocations() {
        this.gameLocations = itemLoader.loadGameResourcesFromFile(LOCATIONS_FILE_PATH);
    }

    public void loadGameItems() {
        this.gameItems = itemLoader.loadGameResourcesFromFile(ITEMS_FILE_PATH);
    }
}
