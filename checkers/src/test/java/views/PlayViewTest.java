package views;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import controllers.PlayController;
import models.Color;
import models.Coordinate;
import utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class PlayViewTest {

    @Mock
    PlayController playController;

    @Mock
    Console console;

    @Mock
    GameView gameView;

    @InjectMocks
    PlayView playView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenPlayViewWhenCorrectFormatThenOk() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithEmptyThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithBadFormatThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("a3.42").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithBadRangeThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("93.49").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }
}