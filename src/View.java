import java.util.ArrayList;

/**
 * This class creates a vieweble elemnt and prints it to the terminal
 *
 * @author psoderlu
 * @version 42
 */

public class View {

    // ***********************
    // Variables
    // ***********************
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_ITALIC = "\u001B[3m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ANSI_BLINK = "\u001B[5m";

    public static final String ANSI_CLEAR = "\033\143";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";

    public static final String LIGHT_LINE =     ANSI_BRIGHT_BLACK + "-------------------------------------------------------------------------" + ANSI_RESET;
    public static final String MEDIUM_LINE =    "=========================================================================" + ANSI_RESET;
    public static final String HEAVY_LINE =     ANSI_BRIGHT_BLACK + "#########################################################################" + ANSI_RESET;

    private Map map;
    private Player player;
    private ArrayList<NPC> npcs;
    private Place playerPlace;

    // ***********************
    // Constructor
    // ***********************
    /**
     * Creates a view
     *
     * @param map    the map to view
     * @param player the player
     */
    public View(Map map, Player player, ArrayList<NPC> npcs) {
        this.map = map;
        this.player = player;
        this.npcs = npcs;
    }

    // ***********************
    // Main Methods
    // ***********************
    public void print() {
        playerPlace = player.getPlace();
        System.out.print(ANSI_CLEAR);
        System.out.println(HEAVY_LINE);
        System.out.println("[Map of the World]");
        System.out.println(MEDIUM_LINE);
        System.out.println(getPrintableMap());
        System.out.println(MEDIUM_LINE);
        System.out.println();

        System.out.println(ANSI_BOLD  + "[Player]: " + player.getName() + ANSI_RESET);
        System.out.println(MEDIUM_LINE);
        System.out.println("[Stats]: " + player.getStats());
        System.out.println(LIGHT_LINE);
        System.out.println("[Inventory]:\n" + player.getInventory());
        System.out.println(MEDIUM_LINE);
        System.out.println();

        System.out.println(ANSI_BOLD + "[Place]: " + ANSI_ITALIC + playerPlace.getName() + ANSI_RESET);
        System.out.println(MEDIUM_LINE);
        System.out.println("[NPCs]: " + playerPlace.npcsToString());
        System.out.println(LIGHT_LINE);
        System.out.println("[Items]: " + playerPlace.itemsToString());
        System.out.println(MEDIUM_LINE);
        System.out.println(HEAVY_LINE);
        System.out.println();
    }

    /**
     * Returns a printable map of the world with colors
     *
     * @return a map of the world
     */
    public String getPrintableMap() {
        String s = "";
        int height = map.getHeight();
        int width = map.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Place l = map.getLocation(j, i);

                if (l == player.getPlace()) {
                    s += ANSI_BLINK;
                }
                if (l.isCharted()) {
                    if (l.hasNPC()) {
                        s += ANSI_UNDERLINE;
                    }
                    if (l.hasItem()) {
                        s += ANSI_ITALIC;
                    }

                    if (l.isDangerous()) {
                        s += ANSI_RED;
                    } else if (l.isHelpful()) {
                        s += ANSI_GREEN;
                    } else if (l.isNeutral()) {
                        s += ANSI_BRIGHT_BLACK;
                    }
                }
                s += l.getShortName() + ANSI_RESET + "\t";
            }
            s += "\n";
        }
        return s.substring(0, s.length()-2);
    }

}