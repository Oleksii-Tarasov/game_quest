package ua.com.javarush.escape_quest.model;

import lombok.Data;

import java.util.Map;

@Data
public class Player {
    private String nickname;
    private Map<String, Item> inventory;
    private String currentLocation;
    private int tries;

    public Player(String nickname) {
        this.nickname = nickname;
    }
}
