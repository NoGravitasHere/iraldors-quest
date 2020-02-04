package iraldorsquest.items;

public class Item {

    //***********************
    // Variables
    //***********************
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
                a = "Uncommon";
            break;
            case RARE:
                a = "Rare";
            break;
            case EPIC:
                a = "Epic";
            break;
            case LEGENDARY:
                a = "Legendary";
            break;
            default:
            throw new IllegalStateException("Not a rarity");
        }

        return i + " (" + a + ") " + getStats();
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