package ch.leozulfiu.multitapeTM;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * User: Leo
 * Date: 11.11.13
 */
public class EndlicherAutomat {
    private static final HashMap<String, State> zustaende = erstelleAutomat();

    private static HashMap<String, State> erstelleAutomat() {
        HashMap<String, State> neueZustaende = new HashMap<String, State>();
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        State q4 = new State("q4");

        Transition q0zuq0 = new Transition("0__", "_0_", "RRN", q0);
        Transition q0zuq1 = new Transition("1__", "___", "RNN", q1);
        Transition q1zuq2 = new Transition("0__", "0__", "NLN", q2);
        Transition q1zuq4 = new Transition("___", "___", "NNN", q4);
        Transition q2zuq2 = new Transition("00_", "00_", "NLN", q2);
        Transition q2zuq3 = new Transition("0__", "0__", "NRN", q3);
        Transition q3zuq3 = new Transition("00_", "000", "NRR", q3);
        Transition q3zuq1 = new Transition("0__", "___", "RNN", q1);

        q0.setzeUebergange(new HashSet<Transition>(Arrays.asList(q0zuq0, q0zuq1)));
        q1.setzeUebergange(new HashSet<Transition>(Arrays.asList(q1zuq2, q1zuq4)));
        q2.setzeUebergange(new HashSet<Transition>(Arrays.asList(q2zuq2, q2zuq3)));
        q3.setzeUebergange(new HashSet<Transition>(Arrays.asList(q3zuq3, q3zuq1)));
        q4.setzeUebergange(new HashSet<Transition>());

        neueZustaende.put("q0", q0);
        neueZustaende.put("q1", q1);
        neueZustaende.put("q2", q2);
        neueZustaende.put("q3", q3);
        neueZustaende.put("q4", q4);
        return neueZustaende;
    }

    public static State gibStartZustand() {
        return zustaende.get("q0");
    }

    public static State gibEndZustand() {
        return zustaende.get("q4");
    }
}
