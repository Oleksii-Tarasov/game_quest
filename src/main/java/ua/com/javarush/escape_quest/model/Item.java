package ua.com.javarush.escape_quest.model;

import lombok.Data;

@Data
public class Item {
    private String title;
    private String description;

    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
