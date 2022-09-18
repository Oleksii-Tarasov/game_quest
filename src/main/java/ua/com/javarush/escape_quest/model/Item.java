package ua.com.javarush.escape_quest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
    private String itemId;
    private String description;
    private String effect;
}
