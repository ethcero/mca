package models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StateTest {

    State state;

    @Before
    public void before(){
        state = new State();
    }

    @Test
    public void givenStartStateThenChangeToInGameState() {
        state.next();
        assertEquals(StateValue.IN_GAME, state.getStateValue());
    }

    @Test
    public void givenInGameStateThenChangeToResumeState() {
        state.next();
        state.next();
        assertEquals(StateValue.RESUME, state.getStateValue());
    }
}
