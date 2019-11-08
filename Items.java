import java.util.Collections;
import java.util.*;

public enum Items{
    AXE,
    CHICKEN,
    SWORD,
    SPOON,
    MAGIC_WAND;

    private static final List<Items> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Items randomItem()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}