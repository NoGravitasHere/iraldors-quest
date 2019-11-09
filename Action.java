import java.util.ArrayList;

public class Action {

    Verbs verb;
    ArrayList<Nouns> nouns;

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

    /**
     * @return the nouns
     */
    public ArrayList<Nouns> getNouns() {
        return nouns;
    }
}