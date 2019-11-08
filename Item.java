public class Item {

    //***********************
    // Variables
    //***********************
    private String name;
    private int rarity;

    //***********************
    // Constructors
    //***********************
    public Item(String name) {
        this.name = name;
        this.rarity = 0;
    }

    public Item(String name, int rarity) {
        this(name);
        this.rarity = rarity;
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
    public int getRarity() {
        return rarity;
    }

    /**
     * @param name the name to set
     */
    public void setname(String name) {
        this.name = name;
    }
}