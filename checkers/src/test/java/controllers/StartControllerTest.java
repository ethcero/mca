package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import models.Game;
import models.State;
import models.StateValue;

public class StartControllerTest {

     @Test
    public void givenStartControllerWhenStartGameThenChangeState() {
        Game game = new Game();
        State state = new State();
        StartController startController = new StartController(game, state);
        assertEquals(StateValue.INITIAL, state.getValueState());
        startController.start();
        assertEquals(StateValue.IN_GAME, state.getValueState());
    }

}