package ua.com.javarush.escape_quest.repository;

import lombok.Getter;
import lombok.Setter;
import ua.com.javarush.escape_quest.model.Character;
import ua.com.javarush.escape_quest.model.Item;
import ua.com.javarush.escape_quest.model.Location;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GameRepository {
    private Map<Long, Character> gameCharacters = new HashMap<>();
    private Map<String, Location> gameLocations = new HashMap<>();
    private Map<String, Item> gameItems = new HashMap<>();
}
