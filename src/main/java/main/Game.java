package main;

import java.util.ArrayList;
import java.util.Optional;
import characters.*;
import map.*;
import parser.*;

/**
 * This class is the main class of the game
 *
 * @author psoderlu
 * @version 42
 */

public class Game {

    // ***********************
    // Variables
    // ***********************
    private Map map;
    private View view;
    private Player player;
    private ArrayList<NPC> npcs;
    private ArrayList<String> printList;
    private Parser parser;
    private boolean finished = false;

    // ***********************
    // Constructor
    // ***********************
    /**
     * Create the game and initialise its internal map.
     */
    public Game(int w, int h, String playerName, int noNPCs) {
        map = new Map(w, h);
        player = new Player(playerName, map.getStartX(), map.getStartY(), map.getStartingPlace());
        npcs = map.getNpcs();
        parser = new Parser();
        view = new View(map, player, npcs, this);
        printList = new ArrayList<>();
        chartAdjacentPlaces(player.getxCoordinate(), player.getyCoordinate());
    }

    // ***********************
    // Main Methods
    // ***********************

    // ***********************
    // Input Methods
    // ***********************
    /**
     * Processes the users input into commands
     */
    public void processInput(String input) {
        Action action = parser.parse(input);
        ArrayList<Nouns> nouns = action.getNouns();
        Optional<Nouns> noun = action.getFirstNoun();

        switch (action.getVerb()) {
        case MOVE:
            noun.ifPresent(this::moveInput);
            break;
        case TALK:
            noun.ifPresent(this::talkInput);
            break;
        case ATTACK:
            noun.ifPresent(this::attackInput);
            break;
        case TAKE:
            break;
        case HELP:
            break;
        case QUIT:
            break;
        case UNKNOWN:
            printList.add("unkown verb");
            break;
        default:
            throw new IllegalStateException("Illegal verb thingamajing");
        }
        view.update();
    }

    /**
     * Moves the player in a direction
     *
     * @param noun the direction to move in.
     */
    private void moveInput(Nouns noun) {
        switch (noun) {
        case NORTH:
        case SOUTH:
        case EAST:
        case WEST:
            if (canMove(noun, player.getxCoordinate(), player.getyCoordinate())) {
                player.move(noun);
                player.setPlace(map.getLocation(player.getxCoordinate(), player.getyCoordinate()));
                player.getPlace().setCharted(true);
                chartAdjacentPlaces(player.getxCoordinate(), player.getyCoordinate());
                if (player.getPlace().isDangerous()) {
                    player.changeHitpoints(-3);
                    printList.add("This place is dangerous, you took 3 damage");
                } else if (player.getPlace().isHelpful()) {
                    player.changeHitpoints(1);
                    printList.add("A strange force rests here, you gain 1 hitpoint");
                }

            } else {
                printList.add("An impassable mountain range stands in your way");
            }
            break;
        default:
            printList.add("Unkown direction");
        }
    }

    /**
     * Talks to a npc
     *
     * @param noun the name of the npc
     */
    private void talkInput(Nouns noun) {
        ArrayList<String> a = parser.getNouns(parser.getInput());
        ArrayList<NPC> npcsInLocation = player.getPlace().getNpcs();
        System.out.println(npcsInLocation);
        System.out.println(a);

        for (NPC npc : npcsInLocation) {
            if(a.contains(npc.getName()) || a.contains("npc")) {
                printList.add("You approach " + npc.getName() + " who turns around and says: ");
                printList.add("\"" + npc.getDialogue() + "\"");
            }
        }
    }

    /**
     * Allows the player to attack an npc
     *
     * @param noun the npc to attack
     */
    private void attackInput(Nouns noun) {
        npcs.stream().filter(npc -> npc.getName().equals(noun)).forEach(npc -> {
            if (npc.getPlace() == player.getPlace()) {
                printList.add("You try to attack the person standing by the river.");
                printList.add("Before you can draw your sword the person spots you and runs away");
                int x = npc.getxCoordinate();
                int y = npc.getyCoordinate();

                npc.getPlace().removeNPC(npc);
                if (canMove(Nouns.SOUTH, x, y)) {
                    npc.move(Nouns.SOUTH);
                    Place p = map.getLocation(x, y + 1);
                    npc.setPlace(p);
                    p.addNPCs(npc);
                } else {
                    npc.move(Nouns.NORTH);
                    Place p = map.getLocation(x, y - 1);
                    npc.setPlace(p);
                    p.addNPCs(npc);
                }
            } else {
                printList.add("You draw your sword and lash out at the imagined threats");
            }
        });
    }

    // ***********************
    // Other
    // ***********************
    public boolean isFinished() {
        if (player.getHitpoints() <= 0) {
            finished = true;
        } else if (map.isCharted()) {
            finished = true;
        }
        return finished;
    }

    /**
     * Reveals the adjacent tiles to the player
     *
     * @param x the x-coordinate to reveal around
     * @param y the y-coordinate to reveal around
     */
    private void chartAdjacentPlaces(int x, int y) {
        if (x < map.getWidth() - 1) {
            map.getLocation(player.getxCoordinate() + 1, player.getyCoordinate()).setCharted(true);
        }

        if (x > 0) {
            map.getLocation(player.getxCoordinate() - 1, player.getyCoordinate()).setCharted(true);
        }

        if (y < map.getHeight() - 1) {
            map.getLocation(player.getxCoordinate(), player.getyCoordinate() + 1).setCharted(true);
        }

        if (y > 0) {
            map.getLocation(player.getxCoordinate(), player.getyCoordinate() - 1).setCharted(true);
        }
    }

    /**
     * Checks if it's possivle to move in a certain direction
     *
     * @param direction the direction to move in
     * @param x         the x coordinate to move from
     * @param y         the y coordinate to move from
     * @return whether it's possible or not to move.
     */
    private boolean canMove(Nouns direction, int x, int y) {
        boolean b = true;
        switch (direction) {
        case NORTH:
            if (y <= 0) {
                b = false;
            }
            break;

        case SOUTH:
            if (y + 1 >= map.getHeight()) {
                b = false;
            }
            break;

        case EAST:
            if (x + 1 >= map.getWidth()) {
                b = false;
            }
            break;

        case WEST:
            if (x <= 0) {
                b = false;
            }
            break;
        default:
            printList.add("unkown direction");
        }
        return b;
    }
}
