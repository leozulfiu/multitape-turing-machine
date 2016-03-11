package ch.leozulfiu.multitapeTM;

import java.util.Set;

/**
 * User: Leo
 * Date: 11.11.13
 */
public class State {
    String description;
    Set<Transition> transitions;

    public State(String description) {
        this.description = description;
    }

    public void setzeUebergange(Set<Transition> transitions) {
        this.transitions = transitions;
    }

    public boolean isProcessible(String eingabe) {
        for (Transition transition : transitions) {
            if (transition.getToRead().equals(eingabe)) {
                return true;
            }
        }
        return false;
    }

    public Set<Transition> gibUebergaenge() {
        return transitions;
    }
}
