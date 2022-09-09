package ua.com.javarush.escape_quest.configuration;

import lombok.Data;

import java.util.Map;

@Data
public class LocationProperties {
    private String title;
    private String pagePath;
    private String imagePath;
    private String soundPath;
    private Map<String, String> itemsInLocation;
}
