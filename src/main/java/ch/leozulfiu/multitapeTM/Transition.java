package ch.leozulfiu.multitapeTM;

/**
 * User: Leo
 * Date: 11.11.13
 */
public class Transition {
    private String toRead;
    private String toWrite;
    private String toStep;
    private State nextState;

    public Transition(String toRead, String toWrite, String toStep, State nextState) {
        this.toRead = toRead;
        this.toWrite = toWrite;
        this.toStep = toStep;
        this.nextState = nextState;
    }

    public String getToRead() {
        return toRead;
    }

    public String getToWrite() {
        return toWrite;
    }

    public String getToStep() {
        return toStep;
    }

    public State getNextState() {
        return nextState;
    }
}
