package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DraughtTest {


    Board board;

    @Before
    public void mockBoard(){
        board = mock(Board.class);
        when(board.isEmpty(any(Coordinate.class))).thenReturn(true);
    }

    @Test
    public void testGivenWhiteDraughtWhenMoveForwardThenNoError(){
        Coordinate origin = new Coordinate(7,0);
        Coordinate target = new Coordinate(6,1);

        assertNull(new Draught(Color.WHITE).isCorrect(origin, target, board));
    }

    @Test
    public void testGivenWhiteDraughtWhenMoveBackwardThenNoError(){
        Coordinate origin = new Coordinate(6,1);
        Coordinate target = new Coordinate(7,0);

        assertNull(new Draught(Color.WHITE).isCorrect(origin, target, board));
    }

    @Test
    public void testGivenDraughtWhenLongDistanceMovementThenNoError(){
        Coordinate origin = new Coordinate(7,0);
        Coordinate target = new Coordinate(0,7);

        assertNull(new Draught(Color.WHITE).isCorrect(origin, target, board));
    }
}
