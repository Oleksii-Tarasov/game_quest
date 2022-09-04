package ua.com.javarush.escape_quest.model;

import java.util.HashMap;

public class Player {
    private static Player player;
    private String nickname;

    private HashMap<String, String> inventory;

    private Player(String nickname) {
        this.nickname = nickname;
    }

    public static Player getPlayer(String nickname) {
        if (player == null) {
            player = new Player(nickname);
        }
        return player;
    }

    public String getNickname() {
        return nickname;
    }

    public HashMap<String, String> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, String> inventory) {
        this.inventory = inventory;
    }
}
