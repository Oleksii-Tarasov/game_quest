package ua.com.javarush.escape_quest.constant;

import java.util.List;

public final class LocationRules {
    private LocationRules() {
    }

    public static final List<String> DONT_DISPLAY_INVENTORY_IN_LOCATIONS = List.of("prison", "firebridge");
    public static final List<String> GOOD_ENDS = List.of("garden", "thronehall");
    public static final List<String> BAD_ENDS = List.of("cage", "cellar", "firecage", "hellend");
}
