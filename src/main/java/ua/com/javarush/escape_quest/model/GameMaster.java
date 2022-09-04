package ua.com.javarush.escape_quest.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.javarush.escape_quest.service.ItemLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static ua.com.javarush.escape_quest.constant.ResourceFilePath.ITEMS_FILE_PATH;
import static ua.com.javarush.escape_quest.constant.ResourceFilePath.LOCATIONS_FILE_PATH;

public class GameMaster {
    private static GameMaster gameMaster;
    private ItemLoader itemLoader;
    private String playerNickname;
    private HashMap playerInventory;
    private HashMap gameItems;
    private HashMap gameLocations;

    private GameMaster() {
        itemLoader = new ItemLoader();
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

    public HashMap getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(HashMap playerInventory) {
        this.playerInventory = playerInventory;
    }

    public HashMap getGameItems() {
        return gameItems;
    }

    public void loadGameItems() {
        this.gameItems = itemLoader.loadGameResourcesFromFile(ITEMS_FILE_PATH);
    }

    public HashMap getGameLocations() {
        return gameLocations;
    }

    public void loadGameLocations() {
        this.gameLocations = itemLoader.loadGameResourcesFromFile(LOCATIONS_FILE_PATH);
    }
}
