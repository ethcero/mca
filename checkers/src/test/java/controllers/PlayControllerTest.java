package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import models.Color;
import models.Game;
import models.State;
import views.CommandView;
import views.GameView;

@RunWith(MockitoJUnitRunner.class)
public class PlayControllerTest {

    @Mock
    private GameView gameView;
    @Mock
    private CommandView commandView;
    @Mock
    private Game game;
    @Mock
    private State state;

    @InjectMocks
    PlayController playController;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void givenPlayControllerWhenNextStateThenCallNext() {

        when(game.getWinner()).thenReturn(Color.WHITE);
        playController.control();
        verify(gameView).interact();
        verify(commandView).interact();
        verify(state).next();
    }
}