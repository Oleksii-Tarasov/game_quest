package ua.com.javarush.escape_quest.model;

import lombok.Data;

@Data
public class Item {
    private String itemId;
    private String description;
    private String effect;

    public Item(String itemId, String description, String effect) {
        this.itemId = itemId;
        this.description = description;
        this.effect = effect;
    }
}
