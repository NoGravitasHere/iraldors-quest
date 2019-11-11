package items;

import java.util.Collections;
import java.util.*;

public enum Rarities {
    NORMAL,
    UNCOMON,
    RARE,
    EPIC,
    LEGENDARY;

    private static final List<Rarities> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Rarities randomRarity()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}