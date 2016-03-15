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

    public void setTransitions(Set<Transition> transitions) {
        this.transitions = transitions;
    }

    public boolean isProcessible(String input) {
        for (Transition transition : transitions) {
            if (transition.getToRead().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }
}
