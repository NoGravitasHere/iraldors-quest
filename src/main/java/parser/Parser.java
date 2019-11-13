package parser;

import characters.NPC;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    private ArrayList<String> redundantWords;
    private String input;

    public Parser() {
        redundantWords = new ArrayList<>();
        redundantWords.add("the");
        redundantWords.add("at");
        redundantWords.add("to");
    }

    public Action parse(String input) {
        this.input = input;
        String in = input;
        in = input.toLowerCase();
        in = removeRedundantWords(input);

        String v = getVerb(in);
        Verbs verb = parseVerb(v);

        ArrayList<Nouns> nouns = new ArrayList<>();
        var ns = getNouns(in);
        for (String string : ns) {
            nouns.add(parseNoun(string));
        }
        return new Action(verb, nouns);
    }

    private String getVerb(String input) {
        if (input.indexOf(" ") == -1) {
            return input;
        } else {
            return input.substring(0, input.indexOf(" "));
        }
    }

    public ArrayList<String> getNouns(String input) {
        var list = new ArrayList<String>();
        if (input.indexOf(" ") == -1) {
            return list;
        }
        input = input.substring(getVerb(input).length() + 1, input.length());
        input = input.replaceAll(" ", "");
        return new ArrayList<String>(Arrays.asList(input.split(",")));
    }

    private Verbs parseVerb(String verb) {
        switch (verb.toLowerCase()) {
        case "help":
            return Verbs.HELP;

        case "quit":
            return Verbs.QUIT;

        case "move":
        case "go":
        case "m":
        case "mv":
            return Verbs.MOVE;

        case "talk":
        case "t":
            return Verbs.TALK;

        case "attack":
        case "atk":
        case "a":
            return Verbs.ATTACK;

        case "take":
            return Verbs.TAKE;

        default:
            return Verbs.UNKNOWN;
        }
    }

    private Nouns parseNoun(String noun) {
        switch(noun.toLowerCase()) {
            case "north":
            case "w":
            return Nouns.NORTH;

            case "south":
            case "s":
            return Nouns.SOUTH;

            case "east":
            case "d":
            return Nouns.EAST;

            case "west":
            case "a":
            return Nouns.WEST;

            case "items":
            return Nouns.ITEMS;

            default:
            if(noun.matches("(item|i)(\\d+)?")) {
                return Nouns.ITEM;
            } else if(NPC.getPossibleNames().contains(noun) || noun.equals("npc")) {
                return Nouns.NPC;
            }
            return Nouns.UNKOWN;
        }
    }

    private String removeRedundantWords(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (String word : redundantWords) {
            while (0 < sb.indexOf(word)) {
                sb.delete(sb.indexOf(word), sb.indexOf(word) + word.length());
            }
        }
        return sb.toString();
    }

    /**
     * @return the input
     */
    public String getInput() {
        return input;
    }
}