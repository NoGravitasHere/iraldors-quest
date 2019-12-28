package iraldorsquest.parser;

import iraldorsquest.characters.NPC;
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
        Verbs v;
        switch (verb.toLowerCase()) {
            case "help":
                v = Verbs.HELP;
                break;
            case "quit":
                v = Verbs.QUIT;
                break;
            case "move":
            case "go":
            case "m":
            case "mv":
                v = Verbs.MOVE;
                break;
            case "talk":
            case "t":
                v = Verbs.TALK;
                break;
            case "attack":
            case "atk":
            case "a":
                v = Verbs.ATTACK;
                break;
            case "take":
                v = Verbs.TAKE;
                break;
            default:
                v = Verbs.UNKNOWN;
        }
        return v;
    }

    private Nouns parseNoun(String noun) {
        Nouns n;
        switch(noun.toLowerCase()) {
            case "north":
            case "w":
                n = Nouns.NORTH;
                break;
            case "south":
            case "s":
                n = Nouns.SOUTH;
                break;
            case "east":
            case "d":
                n = Nouns.EAST;
                break;
            case "west":
            case "a":
                n = Nouns.WEST;
                break;
            case "items":
                n = Nouns.ITEMS;
                break;
            default:
                if(noun.matches("(item|i)(\\d+)?")) {
                    return Nouns.ITEM;
                } else if(NPC.getPossibleNames().contains(noun) || noun.equals("npc")) {
                    return Nouns.NPC;
                }
                n =  Nouns.UNKOWN;
        }
        return n;
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