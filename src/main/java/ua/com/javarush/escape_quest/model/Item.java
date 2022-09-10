package ua.com.javarush.escape_quest.model;

import lombok.Data;

@Data
public class Item {
    private String title;
    private String description;
    private String effect;

    public Item(String title, String description, String effect) {
        this.title = title;
        this.description = description;
        this.effect = effect;
    }
}
