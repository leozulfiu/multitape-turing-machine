package ch.leozulfiu.multitapeTM;

import java.util.ArrayList;

public class Tape {
    private ArrayList<String> left;
    private ArrayList<String> right;

    public Tape() {
        left = new ArrayList<String>();
        right = new ArrayList<String>();
    }

    public void initializeTape(int number1, int number2) {
        for (int i = 0; i < number1; i++) {
            right.add("0");
        }
        right.add("1");
        for (int i = 0; i < number2; i++) {
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

    public void writeSymbol(String symbol) {
        right.set(0, symbol);
    }

    public String getContentAsString() {
        String leftContent = "";
        String rightContent = "";
        for (int i = 0; i < 15; i++) {
            leftContent += left.get(i);
            rightContent += right.get(i);
        }
        StringBuffer buffer = new StringBuffer(leftContent).reverse();
        return buffer + rightContent;
    }

    public String getFullContent() {
        String result = "";
        for (String s : left) {
            result += s;
        }
        for (String s : right) {
            result += s;
        }
        return result;
    }

    public void step(String symbol) {
        if (symbol.equals("L")) {
            String temp = left.get(0);
            right.add(0, temp);
            left.remove(0);
            left.add(TuringMachine.BLANK);
        } else if (symbol.equals("R")) {
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
