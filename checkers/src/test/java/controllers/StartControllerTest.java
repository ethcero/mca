package controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

import models.Game;
import models.State;
import views.StartView;

@RunWith(MockitoJUnitRunner.class)
public class StartControllerTest {

    @Mock
    private StartView startView;
    @Mock
    private Game game;
    @Mock
    private State state;

    @InjectMocks
    private StartController startController;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void givenPlayControllerWhenNextStateThenCallNext() {
        startController.control();
        verify(startView).interact();
        verify(state).next();
    }
}