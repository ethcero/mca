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
import views.ResumeView;

@RunWith(MockitoJUnitRunner.class)
public class ResumeControllerTest {

    @Mock
    private ResumeView resumeView;
    @Mock
    private Game game;
    @Mock
    private State state;

    @InjectMocks
    private ResumeController resumeController;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void givenPlayControllerWhenNextStateThenCallNext() {
        resumeController.control();
        verify(resumeView).interact();
        verify(state).next();
    }
}