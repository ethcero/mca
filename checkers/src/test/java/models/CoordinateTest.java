package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

    @Test
    public void testGivenTwoCoordinatesWhenBetweenDiagonalThenOk() {
        assertEquals(new Coordinate(row(1), column(1)), new Coordinate(row(2), column(2)).betweenDiagonal(new Coordinate(row(0), column(0))));
        assertEquals(new Coordinate(row(3), column(1)), new Coordinate(row(2), column(2)).betweenDiagonal(new Coordinate(row(4), column(0))));
        assertEquals(new Coordinate(row(3), column(3)), new Coordinate(row(2), column(2)).betweenDiagonal(new Coordinate(row(4), column(4))));
        assertEquals(new Coordinate(row(1), column(3)), new Coordinate(row(2), column(2)).betweenDiagonal(new Coordinate(row(0), column(4))));
    }

    @Test
    public void testGivenCoordinateWhenValidThenOk() {
        assertTrue(new Coordinate(row(0),column(0)).isValid());
        assertTrue(new Coordinate(row(3),column(3)).isValid());
        assertTrue(new Coordinate(row(7),column(7)).isValid());
    }

    @Test
    public void testGivenCoordinateWhenInvalidThenOk() {
        assertFalse(new Coordinate(row(-1), column(-1)).isValid());
        assertFalse(new Coordinate(row(Integer.MIN_VALUE),column(Integer.MAX_VALUE)).isValid());
    }

    @Test
    public void testGivenCoordinateWhenValidDiagonalDistanceThenOk(){

        assertEquals(3,new Coordinate(row(3), column(4)).diagonalDistance(new Coordinate(row(0), column(7))));
        assertEquals(1,new Coordinate(row(3), column(4)).diagonalDistance(new Coordinate(row(4), column(5))));
    }

    private int row(int row){
        return row;
    }

    private int column(int column){
        return column;
    }
}