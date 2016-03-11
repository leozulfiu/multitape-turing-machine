package ch.leozulfiu.multitapeTM;

import java.util.Set;

public class TuringMachine {
    public static String BLANK = "_";
    public boolean isFinished = false;
    public boolean isRunning;
    public int countOfCalculatingSteps;
    private Tape tape1;
    private Tape tape2;
    private Tape tape3;
    private State currentState;

    public TuringMachine() {
        tape1 = new Tape();
        tape2 = new Tape();
        tape3 = new Tape();
        currentState = EndlicherAutomat.gibStartZustand();
    }

    public void calculateStep() {
        String input = tape1.readSymbol() + tape2.readSymbol() + tape3.readSymbol();
        Set<Transition> allTransitions = currentState.gibUebergaenge();
        boolean isProcessible = currentState.isProcessible(input);
        if (isProcessible) {
            for (Transition tempTransition : allTransitions) {
                if (tempTransition.getToRead().equals(input)) {
                    String write = tempTransition.getToWrite();
                    tape1.writeSymbol(write.substring(0, 1));
                    tape2.writeSymbol(write.substring(1, 2));
                    tape3.writeSymbol(write.substring(2, 3));
                    String walk = tempTransition.getToStep();
                    tape1.step(walk.substring(0, 1));
                    tape2.step(walk.substring(1, 2));
                    tape3.step(walk.substring(2, 3));
                    currentState = tempTransition.getNextState();
                    countOfCalculatingSteps++;
                    isRunning = true;
                }
            }
        } else {
            if (currentState.equals(EndlicherAutomat.gibEndZustand())) {
                getCalculatedNumber();
            }
            isFinished = true;
            isRunning = false;
        }
    }

    public String getCalculatedNumber() {
        String content = tape3.gibGanzenInhalt();
        int number = 0;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '0') {
                number++;
            }
        }
        return String.valueOf(number);
    }

    public String getTape1Content() {
        return tape1.gibInhaltAlsText();
    }

    public String getTape2Content() {
        return tape2.gibInhaltAlsText();
    }

    public String getTape3Content() {
        return tape3.gibInhaltAlsText();
    }

    public void reset() {
        tape1.dump();
        tape2.dump();
        tape3.dump();
        currentState = EndlicherAutomat.gibStartZustand();
        countOfCalculatingSteps = 0;
        isFinished = false;
        isRunning = false;
    }

    public void initializeMainTape(int zahl1, int zahl2) {
        tape1.initializeTape(zahl1, zahl2);
    }

    public void initializeTapes() {
        tape2.initializeTape();
        tape3.initializeTape();
    }

    public String getCurrentStateAsText() {
        return currentState.description;
    }
}
