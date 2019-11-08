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
    private RarityOption rarity;
    private int damage;

    //***********************
    // Constructors
    //***********************
    public Item(String name) {
        this.name = name;
        this.rarity = RarityOption.NORMAL;
        this.damage = 1;
    }

    public Item(String name, RarityOption rarity) {
        this(name);
        this.rarity = rarity;
    }
    
    public Item(String name, RarityOption rarity, int damage) {
        this(name, rarity);
        this.damage = damage;
    }
    //***********************
    // Getters & Setters
    //***********************
    @Override
    public String toString() {
        String s = "";
        switch(rarity) {
            case NORMAL:
                s += "Normal";
            break;
            case UNCOMON:
                s += ANSI_GREEN + "Uncommon";
            break;
            case RARE:
                s += ANSI_CYAN + "Rare";
            break;
            case EPIC:
                s += ANSI_YELLOW + "Epic";
            break;
            case LEGENDARY:
                s += ANSI_BRIGHT_YELLOW + "Legendary";
            break;
            default:
            throw new IlleagalStateException("Not a rarity");
        }
        return s + " " + name;
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
    public RarityOption getRarity() {
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