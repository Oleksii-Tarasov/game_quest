package ua.com.javarush.escape_quest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Location {
    private String locationId;
    private String storyBlock;
    private String image;
    private String sound;
    private List<String> itemsInLocation;
}
