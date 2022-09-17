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
    private int gameAttempt = 0;
    private int goodEndsNumber = 0;
    private int badEndsNumber = 0;
    private boolean isWinnerInBattle;

    public Character(long characterId, String nickname, int amountOfLives) {
        this.characterId = characterId;
        this.nickname = nickname;
        this.amountOfLives = amountOfLives;
    }
}
