package ua.com.javarush.escape_quest.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Character {
    private long characterId;
    private String nickname;
    private List<String> inventory;
    private int amountOfLives;
    private int gameAttempt;
    private int goodEndsNumber;
    private int badEndsNumber;
    private boolean isWinner;

    public Character(long characterId, String nickname, int amountOfLives) {
        this.characterId = characterId;
        this.nickname = nickname;
        this.amountOfLives = amountOfLives;
        this.gameAttempt = 0;
        this.goodEndsNumber = 0;
        this.badEndsNumber = 0;
        this.inventory = new ArrayList<>();
    }
}
