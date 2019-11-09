import java.util.ArrayList;

/**
 * This class is a model of a place on the map in the game
 *
 * @author psoderlu
 * @version 42
 */

public class Place {

    // ***********************
    // Variables
    // ***********************
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";

    private String biome;
    private String attribute;
    private boolean charted;
    private boolean dangerous;
    private boolean helpful;
    private boolean neutral;

    private ArrayList<NPC> npcs;
    private ArrayList<Item> items;

    // ***********************
    // Constructors
    // ***********************
    /**
     * Creates a new place
     */
    public Place() {
        this.biome = "Start";
        this.attribute = "    ";
        this.dangerous = false;
        this.helpful = false;
        this.neutral = false;
        this.charted = false;
        npcs = new ArrayList<>();
        items = new ArrayList<>();
    }

    /**
     * Creates a new place
     *
     * @param biome the biome
     */
    public Place(String biome) {
        this();
        this.biome = biome;
    }

    /**
     * Creates a new place
     *
     * @param biome     the biome
     * @param attribute the attribute of the biome
     */
    public Place(String biome, String attribute) {
        this(biome);
        this.attribute = attribute;
    }

    /**
     * Creates a new place
     *
     * @param biome     the biome
     * @param attribute the attribute of the biome
     * @param dangerous whether the attribute is dangerous
     */
    public Place(String biome, String attribute, boolean dangerous) {
        this(biome, attribute);
        this.dangerous = dangerous;
    }

    /**
     * Creates a new place
     *
     * @param biome     the biome
     * @param attribute the attribute of the biome
     * @param dangerous whether the attribute is dangerous
     * @param helpful   whetether the attribute is helpful
     */
    public Place(String biome, String attribute, boolean dangerous, boolean helpful) {
        this(biome, attribute, dangerous);
        this.helpful = helpful;
    }

    /**
     * Creates a new place
     *
     * @param biome     the biome
     * @param attribute the attribute of the biome
     * @param dangerous whether the attribute is dangerous
     * @param helpful   whetether the attribute is helpful
     * @param neutral   whether the attribute is neutral
     */
    public Place(String biome, String attribute, boolean dangerous, boolean helpful, boolean neutral) {
        this(biome, attribute, dangerous, helpful);
        this.neutral = neutral;
    }

    /**
     * Creates a new place
     *
     * @param biome     the biome
     * @param attribute the attribute of the biome
     * @param dangerous whether the attribute is dangerous
     * @param helpful   whetether the attribute is helpful
     * @param neutral   whether the attribute is neutral
     * @param charted   determins if the place is visible to the player
     */
    public Place(String biome, String attribute, boolean dangerous, boolean helpful, boolean neutral, boolean charted) {
        this(biome, attribute, dangerous, helpful, neutral);
        this.charted = charted;
    }

    // ***********************
    // Methods
    // ***********************
    @Override
    public String toString() {
        String s = "";

        if (isDangerous()) {
            s += ANSI_RED;
        } else if (isHelpful()) {
            s += ANSI_GREEN;
        } else if (isNeutral()) {
            s += ANSI_BRIGHT_BLACK;
        }
        s += attribute + " " + biome + ANSI_RESET;
        s += "\n---------------------------------\n";
        s += "[NPCs]: " + npcsToString() + "\n";
        s += "[Items]: " + itemsToString() + "\n";
        s += "---------------------------------";
        return s;
    }

    /**
     * Adds a npc to the place
     *
     * @param npc the npc to add
     */
    public void addNPCs(NPC... npcs) {
        for (NPC npc : npcs) {
            this.npcs.add(npc);
        }
    }

    /**
     * Removes a npc;
     *
     * @param npc the npc to remove
     */
    public void removeNPC(NPC npc) {
        npcs.remove(npc);
    }

    /**
     * Wether the place has an npc
     *
     * @return true if it has else false.
     */
    public boolean hasNPC() {
        return !npcs.isEmpty();
    }

    /**
     * Returns a string of the npcs names
     *
     * @return a string of npc names
     */
    public String npcsToString() {
        String s = "";
        for (NPC npc : npcs) {
            s += npc.toString() + ", ";
        }
        return s;
    }

    public void addItems(Item... items) {
        for (Item item : items) {
            this.items.add(item);
        }
    }

    public boolean hasItem() {
        return !items.isEmpty();
    }

    public String itemsToString() {
        String s = "";
        for (Item item : items) {
            s += item.toString() + ", ";
        }
        return s;
    }

    public String getName() {
        return "The " + attribute + " " + biome;
    }

    // ***********************
    // Getters & Setters
    // ***********************
    /**
     * Returns a nine characther string of the four first letters in the attribute
     * and biome.
     *
     * @return the string described above. If the place is uncharted "???? ????".
     */
    public String getShortName() {
        if (!isCharted()) {
            return "???? ????";
        }

        String s = "";
        s += attribute.substring(0, 4);
        s += " ";
        s += biome.substring(0, 4);
        return s;
    }

    /**
     * @return the charted
     */
    public boolean isCharted() {
        return charted;
    }

    /**
     * @return the dangerous
     */
    public boolean isDangerous() {
        return dangerous;
    }

    /**
     * @return the helpful
     */
    public boolean isHelpful() {
        return helpful;
    }

    /**
     * @return the neutral
     */
    public boolean isNeutral() {
        return neutral;
    }

    /**
     * @return the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * @param biome the biome to set
     */
    public void setBiome(String biome) {
        this.biome = biome;
    }

    /**
     * @param charted the charted to set
     */
    public void setCharted(boolean charted) {
        this.charted = charted;
    }

    /**
     * @param dangerous the dangerous to set
     */
    public void setDangerous(boolean dangerous) {
        this.dangerous = dangerous;
    }
}
