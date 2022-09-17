package ua.com.javarush.escape_quest.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Character {
    private long characterId;
    private String nickname;
    private List<String> inventory = new ArrayList<>();
    private Map<String, Location> gameLocations = new HashMap<>();
    private int amountOfLives;
    private boolean isWinner;

    public Character(long characterId, String nickname, int amountOfLives) {
        this.characterId = characterId;
        this.nickname = nickname;
        this.amountOfLives = amountOfLives;
    }
}
