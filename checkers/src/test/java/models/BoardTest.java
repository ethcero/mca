package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BoardTest {

    Board board;

    @Before
    public void before(){
        board = new Board();
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(2,2));
    }

    @Test
    public void givenCoordinateWhenOccupiedSquareThenReturnPiece() {
        assertNotNull(board.getPiece(new Coordinate(2,2)));
    }

    @Test
    public void givenCoordinateWhenUnoccupiedSquareThenReturnNull() {
        assertNull(board.getPiece(new Coordinate(3,3)));
    }
}
