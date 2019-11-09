import java.util.ArrayList;

/**
 * This class is a model of a place on the map in the game
 * @author psoderlu
 * @version 42
 */

public class Player {

    //***********************
    // Variables
    //***********************
    private int xCoordinate;
    private int yCoordinate;
    private int hitpoints;
    private String name;
    private Place place;
    private Inventory inventory;

    //***********************
    // Constructors
    //***********************
    /**
     * Creates a new player
     */
    public Player() {
        xCoordinate = 0;
        yCoordinate = 0;
        name = "";
        place = null;
        hitpoints = 10;
        inventory = new Inventory(); 
    }

    /***
     * Creates a new player 
     * @param name the name
     */
    public Player(String name) {
        this();
        this.name = name;
    }

     /**
      * Creates a new player
      * @param name the name
      * @param x the x position
      * @param y the y position
      * @param place the place
      */
    public Player(String name, int x, int y, Place place) {
        this(name);
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.place = place;
    }

    //***********************
    // Main methods
    //***********************
    /**
     * Moves the player based on the input direction
     * @param direction the direction to move in
     */
    public void move(Nouns direction){
        switch(direction) {
            case NORTH:
            yCoordinate--;
            break;
            case SOUTH:
            yCoordinate++;
            break;
            case EAST:
            xCoordinate++;
            break;
            case WEST:
            xCoordinate--;
            break;
            default:
        }
    }

    /**
     * Moves the player
     * @param x the x to move to
     * @param y the y to move to
     */
    public void move(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public int changeHitpoints(int amount) {
        hitpoints += amount;
        return hitpoints;
    }

    public String getStats() {
        return "HP: " + hitpoints;
    }

    public void addItems(Item ... items) {
        for (Item item : items) {
            inventory.add(item);
        }
    }

    //***********************
    // Getters & Setters
    //***********************
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the xCoordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * @return the yCoordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * @return the place
     */
    public Place getPlace() {
        return place;
    }

    /**
     * @return the hitpoints
     */
    public int getHitpoints() {
        return hitpoints;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(Place place) {
        this.place = place;
    }
}