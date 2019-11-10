package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import models.Session;
import models.StateValue;

public class StartControllerTest {

     @Test
    public void givenStartControllerWhenStartGameThenChangeState() {
        Session session = new Session();
        StartController startController = new StartController(session);
        assertEquals(StateValue.INITIAL, session.getValueState());
        startController.start();
        assertEquals(StateValue.IN_GAME, session.getValueState());
    }

}