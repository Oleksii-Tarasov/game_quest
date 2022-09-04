package ua.com.javarush.escape_quest.constant;

import java.util.List;

public final class GoodBadEnds {
    private GoodBadEnds() {
    }

    public static final List<String> GOOD_ENDS = List.of("exit", "thronehall");

    public static final List<String> BAD_ENDS = List.of("cage", "hell", "dungeon");
}
