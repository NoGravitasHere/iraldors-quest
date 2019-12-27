package iraldorsquest.items;

import java.util.ArrayList;

public class Inventory extends ArrayList<Item>{

    //***********************
    // Variables
    //***********************
    private static final long serialVersionUID = 1L;

    // ***********************
    // Constructor
    //***********************
    public Inventory(Item ... items) {
        super();
        for (Item item : items) {
            super.add(item);
        }
    }

    //***********************
    // Main methods
    //***********************
    public void addItems(Item ... items) {
        for (Item item : items) {
            super.add(item);
        }
    }

    @Override
    public String toString() {
        if(isEmpty()) {
            return "";
        }

        String s = "";
        for (Item item : this) {
            s += item.toString() + "\n";
        }
        return s.substring(0, s.length() - 2);
    }
}