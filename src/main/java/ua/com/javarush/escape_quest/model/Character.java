package ua.com.javarush.escape_quest.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Character {
    private long id;
    private String nickname;
    private List<String> inventory = new ArrayList<>();
    private String currentLocationId;
    private Map<String, Location> gameLocations = new HashMap<>();
    private int amountOfLives;
    private boolean isWinner;

    public Character(long id, String nickname, int amountOfLives) {
        this.id = id;
        this.nickname = nickname;
        this.amountOfLives = amountOfLives;
    }
}
