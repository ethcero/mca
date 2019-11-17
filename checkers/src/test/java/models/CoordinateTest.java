package models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoordinateTest {

    @Test
    public void testGivenTwoCoordinatesWhenBettweenDiagonalThenOk() {
        assertEquals(new Coordinate(1, 1), new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 0)));
        assertEquals(new Coordinate(3, 1), new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 0)));
        assertEquals(new Coordinate(3, 3), new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 4)));
        assertEquals(new Coordinate(1, 3), new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 4)));
    }

    @Test
    public void testGivenTwoCoordinatesWhenGetDistanceThenResult() {
        assertEquals(3, new Coordinate(3, 4).diagonalDistance(new Coordinate(0, 7)));
    }

    @Test
    public void testGivenTwoCoordinatesWhenBetweenAllDiagonalThenOk() {
        assertEquals(new Coordinate(1, 1), new Coordinate(2, 2).betweenAllDiagonal(new Coordinate(0, 0)).get(0));
        assertEquals(new Coordinate(3, 1), new Coordinate(2, 2).betweenAllDiagonal(new Coordinate(4, 0)).get(0));
        assertEquals(new Coordinate(3, 3), new Coordinate(2, 2).betweenAllDiagonal(new Coordinate(4, 4)).get(0));
        assertEquals(new Coordinate(1, 3), new Coordinate(2, 2).betweenAllDiagonal(new Coordinate(0, 4)).get(0));

        Coordinate[] array1 = {new Coordinate(1, 1),new Coordinate(2, 2)};
        this.assertArrayContain( array1,
                new Coordinate(3, 3).betweenAllDiagonal(new Coordinate(0, 0)));

        Coordinate[] array2 = {new Coordinate(2, 4),new Coordinate(1, 5)};
        this.assertArrayContain( array2,
                new Coordinate(3, 3).betweenAllDiagonal(new Coordinate(0, 6)));

        Coordinate[] array3 = {new Coordinate(4, 4),new Coordinate(5, 5)};
        this.assertArrayContain( array3,
                new Coordinate(3, 3).betweenAllDiagonal(new Coordinate(6, 6)));

        Coordinate[] array4 = {new Coordinate(4, 2),new Coordinate(5, 1)};
        this.assertArrayContain( array4,
                new Coordinate(3, 3).betweenAllDiagonal(new Coordinate(6, 0)));



    }

    private void assertArrayContain(Coordinate[] expected, List<Coordinate> actual){

        for(Coordinate coord: Arrays.asList(expected)){
            assertTrue(actual.contains(coord));
        }
    }

}