package views;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import models.Error;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import controllers.PlayController;
import models.Color;
import models.Coordinate;
import utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class CommandViewTest {

    @Mock
    PlayController playController;

    @Mock
    Console console;

    @InjectMocks
    CommandView commandView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testInteract(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("32.41\n");
        commandView.interact(playController);
        verify(playController).move(new Coordinate(2,1), new Coordinate(3, 0));
    }

    @Test()
    public void testGivenCommandViewWhenMoveWithOuterCoordinateThenNoMovement() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("47.38\n");
        commandView.interact(playController);

        verify(playController, never()).move(new Coordinate(4,7), new Coordinate(3, 8));

    }

}