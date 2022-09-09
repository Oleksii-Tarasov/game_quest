package ua.com.javarush.escape_quest.model;

import lombok.Data;

import java.util.Map;

@Data
public class Location {
    private String title;
    private String pagePath;
    private String imagePath;
    private String soundPath;
    private Map<String, Item> itemsInLocation;

    public Location(String title, String pagePath, String imagePath, String soundPath) {
        this.title = title;
        this.pagePath = pagePath;
        this.imagePath = imagePath;
        this.soundPath = soundPath;
    }
}
