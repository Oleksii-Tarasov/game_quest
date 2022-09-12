package ua.com.javarush.escape_quest.configuration;

import lombok.Data;

@Data
public class ItemProperties {
    private String itemId;
    private String description;
    private String effect;
}
