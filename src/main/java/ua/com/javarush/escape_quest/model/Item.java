package ua.com.javarush.escape_quest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Item {
    private String itemId;
    private String description;
    private String effect;
}
