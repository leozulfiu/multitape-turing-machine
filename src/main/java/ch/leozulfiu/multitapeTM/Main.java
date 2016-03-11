package ch.leozulfiu.multitapeTM;

import controlP5.*;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * User: Leo
 * Date: 13.11.13
 */
public class Main extends PApplet {
    TuringMachine turingMachine = new TuringMachine();
    ControlP5 cp5;
    Textfield firstNumber;
    Textfield secondNumber;
    Button eingabeSetzen;
    Button berechnenButton;
    boolean stepMode;
    boolean initialized;

    public static void main(String args[]) {
        PApplet.main(new String[]{"ch.leozulfiu.multitapeTM.Main"});
    }

    public void setup() {
        size(885, 300);
        frame.setTitle("Multitape Turing Machine");
        //frameRate(1000000);
        cp5 = new ControlP5(this);
        PFont font = createFont("arial", 14);
        firstNumber = cp5.addTextfield("Erste Zahl")
                .setPosition(560, 180)
                .setSize(70, 30)
                .setFont(font)
                .setFocus(true)
                .setAutoClear(false)
                .setInputFilter(ControlP5.INTEGER);
        secondNumber = cp5.addTextfield("Zweite Zahl")
                .setPosition(660, 180)
                .setSize(70, 30)
                .setFont(font)
                .setAutoClear(false)
                .setInputFilter(ControlP5.INTEGER);
        eingabeSetzen = cp5.addButton("Eingabe setzen")
                .setPosition(560, 235)
                .setSize(80, 50);
        berechnenButton = cp5.addButton("Berechnen")
                .setPosition(670, 250)
                .setSize(60, 30);
        berechnenButton.addListener(new ControlListener() {
            @Override
            public void controlEvent(ControlEvent controlEvent) {
                turingMachine.isRunning = true;
            }
        });
        eingabeSetzen.addListener(new ControlListener() {
            @Override
            public void controlEvent(ControlEvent controlEvent) {
                turingMachine.reset();
                initialize(Integer.valueOf(firstNumber.getText()), Integer.valueOf(secondNumber.getText()));
                initialized = true;
                stepMode = false;
            }
        });
    }

    private void initialize(int firstNumber, int secondNumber) {
        turingMachine.initializeMainTape(firstNumber, secondNumber);
        turingMachine.initializeTapes();
    }

    public void draw() {
        background(135);
        drawTapes();
        if (!turingMachine.isFinished && turingMachine.isRunning && !stepMode && initialized) {
            turingMachine.calculateStep();
            drawTapeContent();
        } else if (!turingMachine.isRunning && turingMachine.isFinished) {
            fill(255);
            textSize(18);
            text("Resultat: " + turingMachine.getCalculatedNumber(), 20, 250);
            drawTapeContent();
        } else if (!turingMachine.isFinished && turingMachine.isRunning && initialized) {
            drawTapeContent();
        }
        if (initialized) {
            drawTapeContent();
        }
        textSize(18);
        fill(255);
        text("Aktueller Zustand: " + turingMachine.getCurrentStateAsText(), 20, 190);
        text("Anzahl Rechenschritte: " + turingMachine.countOfCalculatingSteps, 20, 220);
    }

    private void drawTapeContent() {
        fill(255);
        textSize(18);
        String tape1Content = turingMachine.getTape1Content();
        for (int i = 0; i < tape1Content.length(); i++) {
            text(tape1Content.charAt(i), i * 30 + 15, 44);
        }
        String tape2Content = turingMachine.getTape2Content();
        for (int i = 0; i < tape2Content.length(); i++) {
            text(tape2Content.charAt(i), i * 30 + 15, 94);
        }
        String tape3Content = turingMachine.getTape3Content();
        for (int i = 0; i < tape3Content.length(); i++) {
            text(tape3Content.charAt(i), i * 30 + 15, 140);
        }
    }

    private void drawTapes() {
        noStroke();
        int cellHeight = 4;
        fill(240, 0, 0);
        rect(456, 10, 30, 160);
        fill(0);
        rect(0, 20, width, cellHeight);
        rect(0, 50, width, cellHeight);
        rect(0, 70, width, cellHeight);
        rect(0, 100, width, cellHeight);
        rect(0, 115, width, cellHeight);
        rect(0, 145, width, cellHeight);
        for (int i = 0; i < 30; i++) {
            rect(i * 30 + 5, 20, 3, 30);
        }
        for (int i = 0; i < 30; i++) {
            rect(i * 30 + 5, 70, 3, 30);
        }
        for (int i = 0; i < 30; i++) {
            rect(i * 30 + 5, 115, 3, 30);
        }
    }

    public void keyPressed() {
        if (keyCode == ' ') {
            if (!turingMachine.isFinished && stepMode) {
                turingMachine.calculateStep();
            } else if (!turingMachine.isFinished) {
                stepMode = true;
            }
        }
        if (keyCode == 'R' && turingMachine.isFinished && !turingMachine.isRunning) {
            turingMachine.reset();
            initialize(Integer.valueOf(firstNumber.getText()), Integer.valueOf(secondNumber.getText()));
        }
    }
}
