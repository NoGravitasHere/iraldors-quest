import java.util.ArrayList;

public class Inventory extends ArrayList<Item>{

    //***********************
    // Variables
    //***********************
    ArrayList<Item> items;

    //***********************
    // Constructor
    //***********************
    public Inventory() {
        items = new ArrayList<>();
    }

    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }

    public Inventory(Item ... items) {
        this();
        for (Item item : items) {
            this.items.add(item);
        }
    }

    //***********************
    // Main methods
    //***********************
    public void addItems(Item ... items) {
        for (Item item : items) {
            this.items.add(item);
        }
    }

    //***********************
    // Getters & Setters
    //***********************
    /**
     * @return the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}