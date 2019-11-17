package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import models.Game;
import models.State;
import models.StateValue;

public class ResumeControllerTest {

    @Test
    public void givenResumeControllerWhenResumeGameMoveToInitialStateRequiereCorrectThenNotError() {
        Game game = new Game();
        State state = new State();
        ResumeController resumeController = new ResumeController(game, state);
        assertEquals(StateValue.INITIAL, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.IN_GAME, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.FINAL, state.getValueState());
        resumeController.reset();
        assertEquals(StateValue.INITIAL, state.getValueState());
    }

    @Test(expected = AssertionError.class)
    public void givenResumeControllerWhenResumeGameMoveOutThenError() {
        Game game = new Game();
        State state = new State();
        ResumeController resumeController = new ResumeController(game, state);
        assertEquals(StateValue.INITIAL, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.IN_GAME, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.FINAL, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.EXIT, state.getValueState());
        resumeController.next();
    }
}