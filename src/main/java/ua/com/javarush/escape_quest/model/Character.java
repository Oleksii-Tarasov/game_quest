package ua.com.javarush.escape_quest.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Character {
    private String nickname;
    private List<String> inventory = new ArrayList<>();
    private String currentLocation;
    private int amountOfLives;
    private boolean isWinner;

    public Character(String nickname, int amountOfLives) {
        this.nickname = nickname;
        this.amountOfLives = amountOfLives;
    }
}
