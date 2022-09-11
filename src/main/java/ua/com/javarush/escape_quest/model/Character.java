package ua.com.javarush.escape_quest.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Character {
    private String nickname;
    private Map<String, Item> inventory = new HashMap<>();
    private String currentLocation;
    private int amountOfLives;
    private boolean isWinner;

    public Character(String nickname, int amountOfLives) {
        this.nickname = nickname;
        this.amountOfLives = amountOfLives;
    }
}
