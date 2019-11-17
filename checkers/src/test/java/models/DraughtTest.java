package models;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DraughtTest {

    @Test
    public void testGivenWhiteDraughtWhenMoveForwardThenNoError(){
        Coordinate origin = new Coordinate(7,0);
        Coordinate target = new Coordinate(6,1);

        Board board = mock(Board.class);
        when(board.isEmpty(target)).thenReturn(true);

        Draught draught = new Draught(Color.WHITE);
        assertNull(draught.isCorrect(origin, target, board));
    }

    @Test
    public void testGivenWhiteDraughtWhenMoveBackwardThenNoError(){
        Coordinate origin = new Coordinate(6,1);
        Coordinate target = new Coordinate(7,0);

        Board board = mock(Board.class);
        when(board.isEmpty(target)).thenReturn(true);

        Draught draught = new Draught(Color.WHITE);
        assertNull(draught.isCorrect(origin, target, board));
    }
}
