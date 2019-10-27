package models;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BoardTest {

    @Test
    public void givenCoordinateWhenOccupiedSquareThenReturnPiece() {
        assertNotNull(new Board().getPiece(new Coordinate(2,2)));
    }

    @Test
    public void givenCoordinateWhenUnoccupiedSquareThenReturnNull() {
        assertNull(new Board().getPiece(new Coordinate(2,2)));
    }
}
