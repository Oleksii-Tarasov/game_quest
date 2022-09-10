package ua.com.javarush.escape_quest.configuration;

import lombok.Data;

import java.util.List;

@Data
public class LocationProperties {
    private String title;
    private String storyBlock;
    private String image;
    private String sound;
    private List<String> itemsInLocation;
}
