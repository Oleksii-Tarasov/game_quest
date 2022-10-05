package ua.com.javarush.escape_quest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Location {
    private String locationId;
    private String storyBlock;
    private String image;
    private String sound;
    private List<String> itemsInLocation;
}
