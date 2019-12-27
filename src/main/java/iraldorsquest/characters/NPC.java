package iraldorsquest.characters;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import iraldorsquest.map.*;

/**
 * This class is an extension of the player in the game which allows for npcs to
 * have dialogue.
 *
 * @author psoderlu
 * @version 42
 */

public class NPC extends Player {

    private String dialogue;

    /**
     * Creates a new npc with dialogue from a text file
     *
     * @param name  name of the npc
     * @param x     x positonn
     * @param y     y position
     * @param place location
     */
    public NPC(String name, int x, int y, Place place) {
        super(name, x, y, place);

        Scanner sc = null;
        try {
            sc = new Scanner(new File("npcDialogue.txt"));
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<String> d = new ArrayList<>();
        while (sc.hasNextLine()) {
            d.add(sc.nextLine());
        }


        Random r = new Random();
        dialogue = d.get(r.nextInt(d.size()));
    }

    public static ArrayList<String> getPossibleNames() {
        Scanner s = null;
        ArrayList<String> possibleNames = new ArrayList<>();
        try {
            s = new Scanner(new File("npcNames.txt"));
        } catch (Exception e) {
            System.out.println(e);
        }

        possibleNames = new ArrayList<>();
        while (s.hasNextLine()) {
            possibleNames.add(s.nextLine());
        }

        return possibleNames;
    }

    public static String getRandomName() {
        Random r = new Random();
        return getPossibleNames().get(r.nextInt(getPossibleNames().size()));
    }

    /**
     * @return the dialogue
     */
    public String getDialogue() {
        return dialogue;
    }

    @Override
    public String toString() {
        return super.getName();
    }
}