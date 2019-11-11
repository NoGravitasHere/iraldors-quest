package parser;

import java.util.ArrayList;
import java.util.Optional;

public class Action {

    private Verbs verb;
    private ArrayList<Nouns> nouns;

    public Action(Verbs verb, ArrayList<Nouns> nouns) {
        this.verb = verb;
        this.nouns = nouns;
    }

    /**
     * @return the verb
     */
    public Verbs getVerb() {
        return verb;
    }

    public Optional<Nouns> getFirstNoun() {
        return Optional.ofNullable(nouns.get(0));
    }

    /**
     * @return the nouns
     */
    public ArrayList<Nouns> getNouns() {
        return nouns;
    }
}