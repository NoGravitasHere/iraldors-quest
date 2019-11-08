public class Item {

    //***********************
    // Variables
    //***********************
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";

    private String name;
    private Rarities rarity;
    private Items item;
    private int damage;

    //***********************
    // Constructors
    //***********************
    public Item() {
        this.item = Items.randomItem();
        this.rarity = Rarities.randomRarity();
        this.damage = 1;
        this.name = toString();
    }

    public Item(Items item, Rarities rarity) {
        this.item = item;
        this.rarity = rarity;
        this.damage = 1;
        this.name = toString();
    }

    public Item(Items item, Rarities rarity, int damage) {
        this(item, rarity);
        this.damage = damage;
    }
    //***********************
    // Getters & Setters
    //***********************
    @Override
    public String toString() {
        String c = "";
        switch(rarity) {
            case NORMAL:
                c = ANSI_WHITE;
            break;
            case UNCOMON:
                c = ANSI_GREEN;
            break;
            case RARE:
                c = ANSI_CYAN;
            break;
            case EPIC:
                c = ANSI_YELLOW;
            break;
            case LEGENDARY:
                c = ANSI_BRIGHT_YELLOW;
            break;
            default:
            throw new IllegalStateException("Not a rarity");
        }

        String i = "";
        switch(item) {
            case AXE:
                i = "Axe";
            break;
            case CHICKEN:
                i = "Chicken";
            break;
            case MAGIC_WAND:
                i = "Magic Wand";
            break;
            case SPOON:
                i = "Spoon";
            break;
            case SWORD:
                i = "Sword";
            break;
            default:
            throw new IllegalStateException("Not an item");
        }

        String a = "";
        switch(rarity) {
            case NORMAL:
                a = "Normal";
            break;
            case UNCOMON:
                a = ANSI_GREEN + "Uncommon";
            break;
            case RARE:
                a = ANSI_CYAN + "Rare";
            break;
            case EPIC:
                a = ANSI_YELLOW + "Epic";
            break;
            case LEGENDARY:
                a = ANSI_BRIGHT_YELLOW + "Legendary";
            break;
            default:
            throw new IllegalStateException("Not a rarity");
        }

        return c + i + " (" + a + ") " + getStats() + ANSI_RESET;
    }

    public String getStats() {
        return "[" +"DMG: " + damage +  "]";
    }
    //***********************
    // Getters & Setters
    //***********************
    /**
     * @return the name
     */
    public String getname() {
        return name;
    }

    /**
     * @return the rarity
     */
    public Rarities getRarity() {
        return rarity;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param name the name to set
     */
    public void setname(String name) {
        this.name = name;
    }
}