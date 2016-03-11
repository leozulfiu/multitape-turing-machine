package ch.leozulfiu.multitapeTM;

import java.util.ArrayList;

public class Tape {
    private ArrayList<String> left;
    private ArrayList<String> right;

    public Tape() {
        left = new ArrayList<String>();
        right = new ArrayList<String>();
    }

    public void initializeTape(int zahl1, int zahl2) {
        for (int i = 0; i < zahl1; i++) {
            right.add("0");
        }
        right.add("1");
        for (int i = 0; i < zahl2; i++) {
            right.add("0");
        }
        for (int i = 0; i < 15; i++) {
            left.add(TuringMachine.BLANK);
        }
        for (int i = 0; i < 15; i++) {
            right.add(TuringMachine.BLANK);
        }
    }

    public void initializeTape() {
        for (int i = 0; i < 15; i++) {
            left.add(TuringMachine.BLANK);
        }
        for (int i = 0; i < 15; i++) {
            right.add(TuringMachine.BLANK);
        }
    }

    public String readSymbol() {
        return right.get(0);
    }

    public void writeSymbol(String zeichen) {
        right.set(0, zeichen);
    }

    public String gibInhaltAlsText() {
        String linksText = "";
        String rechtsText = "";
        for (int i = 0; i < 15; i++) {
            linksText += left.get(i);
            rechtsText += right.get(i);
        }
        StringBuffer buffer = new StringBuffer(linksText).reverse();
        return buffer + rechtsText;
    }

    public String gibGanzenInhalt() {
        String resultat = "";
        for (String s : left) {
            resultat += s;
        }
        for (String s : right) {
            resultat += s;
        }
        return resultat;
    }

    public void step(String zeichen) {
        if (zeichen.equals("L")) {
            String zwischenspeicher = left.get(0);
            right.add(0, zwischenspeicher);
            left.remove(0);
            left.add(TuringMachine.BLANK);
        } else if (zeichen.equals("R")) {
            String zwischenspeicher = right.get(0);
            left.add(0, zwischenspeicher);
            right.remove(0);
            right.add(TuringMachine.BLANK);
        }
    }

    public void dump() {
        left.clear();
        right.clear();
    }
}
