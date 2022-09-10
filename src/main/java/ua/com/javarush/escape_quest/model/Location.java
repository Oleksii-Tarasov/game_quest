package ua.com.javarush.escape_quest.model;

import lombok.Data;

import java.util.List;

@Data
public class Location {
    private String title;
    private String storyBlock;
    private String image;
    private String sound;
    private List<String> itemsInLocation;

    public Location(String title, String storyBlock, String image, String sound, List<String> itemsInLocation) {
        this.title = title;
        this.storyBlock = storyBlock;
        this.image = image;
        this.sound = sound;
        this.itemsInLocation = itemsInLocation;
    }
}
