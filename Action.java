import java.util.ArrayList;

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
        Optional<Nouns> o = new Optional<Nouns>();
        if(nouns.isEmpty()) {
            return o;
        } else {
            o = nouns.get(0);
            return o;
        }
    }
    
    /**
     * @return the nouns
     */
    public ArrayList<Nouns> getNouns() {
        return nouns;
    }
}