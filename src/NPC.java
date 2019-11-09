import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

/**
 * This class is an extension of the player in the game which allows for npcs to
 * have dialogue.
 *
 * @author psoderlu
 * @version 42
 */

public class NPC extends Player {

    private String dialogue;
    public ArrayList<String> possibleNames;

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
        Scanner s = null;
        try {
            sc = new Scanner(new File("npcDialogue.txt"));
            s = new Scanner(new File("npcNames.txt"));
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<String> d = new ArrayList<>();
        while (sc.hasNextLine()) {
            d.add(sc.nextLine());
        }

        possibleNames = new ArrayList<>();
        while (s.hasNextLine()) {
            possibleNames.add(s.nextLine());
        }

        Random r = new Random();
        dialogue = d.get(r.nextInt(d.size()));
        name = possibleNames.get(r.nextInt(possibleNames.size()));
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