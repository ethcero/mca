package views;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import controllers.PlayController;
import models.Color;
import models.Coordinate;
import utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class CommandViewTest {

    @Mock
    private PlayController playController;

    @Mock
    private Console console;

    @InjectMocks
    private CommandView commandView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInteract(){
        when(playController.getTurn()).thenReturn(Color.BLACK);
        when(console.readString(anyString())).thenReturn("21.30\n");
        commandView.interact();
        verify(playController).move(new Coordinate(2,1), new Coordinate(3, 0));
    }

}