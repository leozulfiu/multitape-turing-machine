package ch.leozulfiu.multitapeTM;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * User: Leo
 * Date: 11.11.13
 */
public class FiniteStateMachine {
    private static final HashMap<String, State> states = createStateMachine();

    private static HashMap<String, State> createStateMachine() {
        HashMap<String, State> newStates = new HashMap<String, State>();
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        State q4 = new State("q4");

        Transition q0toq0 = new Transition("0__", "_0_", "RRN", q0);
        Transition q0toq1 = new Transition("1__", "___", "RNN", q1);
        Transition q1toq2 = new Transition("0__", "0__", "NLN", q2);
        Transition q1toq4 = new Transition("___", "___", "NNN", q4);
        Transition q2toq2 = new Transition("00_", "00_", "NLN", q2);
        Transition q2toq3 = new Transition("0__", "0__", "NRN", q3);
        Transition q3toq3 = new Transition("00_", "000", "NRR", q3);
        Transition q3toq1 = new Transition("0__", "___", "RNN", q1);

        q0.setTransitions(new HashSet<Transition>(Arrays.asList(q0toq0, q0toq1)));
        q1.setTransitions(new HashSet<Transition>(Arrays.asList(q1toq2, q1toq4)));
        q2.setTransitions(new HashSet<Transition>(Arrays.asList(q2toq2, q2toq3)));
        q3.setTransitions(new HashSet<Transition>(Arrays.asList(q3toq3, q3toq1)));
        q4.setTransitions(new HashSet<Transition>());

        newStates.put("q0", q0);
        newStates.put("q1", q1);
        newStates.put("q2", q2);
        newStates.put("q3", q3);
        newStates.put("q4", q4);
        return newStates;
    }

    public static State getStartState() {
        return states.get("q0");
    }

    public static State getEndState() {
        return states.get("q4");
    }
}
