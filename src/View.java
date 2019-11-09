import java.util.ArrayList;

/**
 * This class creates a vieweble elemnt and prints it to the terminal
 * @author psoderlu
 * @version 42
 */

public class View {

    //***********************
    // Variables
    //***********************
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_ITALIC = "\u001B[3m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ANSI_BLINK = "\u001B[5m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";

    private Map map;
    private Player player;
    private ArrayList<NPC> npc;

    //***********************
    // Constructor
    //***********************
    /**
     * Creates a view
     * @param map the map to view
     * @param player the player
     */
    public View(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    //***********************
    // Main Methods
    //***********************
    /**
     * Prints the map in the terminal
     */
    public void printMap(){
        System.out.println(getPrintableMap());
    }

    /**
     * Returns a printable map of the world with colors
     * @return a map of the world
     */
    private String getPrintableMap() {
        String s = "";
        int height = map.getHeight();
        int width = map.getWidth();

        for (int i = 0; i <  height; i++) {
            s += "|    ";
            for (int j = 0; j < width; j++) {
                Place l = map.getLocation(j, i);

                //Fixes colors based on the locations attributes / players / npcs
                if(l == player.getPlace()){
                    s += ANSI_BLINK;
                }
                if(l.isCharted()){
                    if (l.hasNPC()){
                        s += ANSI_UNDERLINE;
                    }
                    if (l.hasItem()) {
                        s += ANSI_ITALIC;
                    }

                    if(l.isDangerous()){
                        s += ANSI_RED;
                    } else if (l.isHelpful()){
                        s += ANSI_GREEN;
                    } else if (l.isNeutral()){
                        s += ANSI_BRIGHT_BLACK;
                    }
                }
                s += l.getShortName() + ANSI_RESET + "\t";
            }
            s += "|\n";
        }

        String lines = "";
        for (int i = 0; i < width*16; i++) {
            lines += "-";
        }
        lines += "-";

        return lines + "\n" + s + lines;
    }

}