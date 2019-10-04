package mastermind.models;

/**
 * @author fran
 */
public class State {

    StateValue stateValue;

    public State() {
        stateValue = StateValue.INITIAL;
    }

    public void next() {
        this.stateValue = StateValue.values()[this.stateValue.ordinal() + 1];
    }

    public void reset() {

        this.stateValue = StateValue.INITIAL;
    }

    public StateValue getStateValue() {
        return stateValue;
    }
}
