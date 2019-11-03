package models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private Coordinate origin;
    @Mock
    private Coordinate target;
    @Mock
    private Board board;
    @Mock
    private Turn turn;

    @InjectMocks
    private Game game;


    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    public void testGivenGameWhenMoveWithOuterCoordinateThenOutCoordinateError() {

        setValidOfOriginTargetTo(false);
        assertEquals(Error.OUT_COORDINATE, game.move(origin,target));
        when(origin.isValid()).thenReturn(true);
        when(target.isValid()).thenReturn(false);
        assertEquals(Error.OUT_COORDINATE, game.move(origin,target));
        when(origin.isValid()).thenReturn(false);
        when(target.isValid()).thenReturn(true);
        assertEquals(Error.OUT_COORDINATE, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenMoveEmptySquareThenEmptySquareError() {
        setValidOfOriginTargetTo(true);
        when(board.isEmpty(origin)).thenReturn(true);

       assertEquals(Error.EMPTY_ORIGIN, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenMoveOppositePieceThenError() {
        setValidOfOriginTargetTo(true);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(any(Color.class))).thenReturn(false);
        assertEquals(Error.OPPOSITE_PIECE, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenNotDiagonalMovementThenError() {
        setValidOfOriginTargetTo(true);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(any(Color.class))).thenReturn(true);
        when(origin.isDiagonal(target)).thenReturn(false);
        assertEquals(Error.NOT_DIAGONAL, this.game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenMoveWithNotAdvancedThenError() {
        setValidOfOriginTargetTo(true);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(any(Color.class))).thenReturn(true);
        when(origin.isDiagonal(target)).thenReturn(true);
        Piece piece = mock(Piece.class);
        when(piece.isAdvanced(origin,target)).thenReturn(false);
        when(board.getPiece(origin)).thenReturn(piece);
        assertEquals(Error.NOT_ADVANCED, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenNotEmptyTargeThenError() {
        Coordinate[][] coordinates = new Coordinate[][] { { new Coordinate(5, 6), new Coordinate(4, 7) },
                { new Coordinate(2, 7), new Coordinate(3, 6) }, { new Coordinate(4, 7), new Coordinate(3, 6) }, };
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = game.move(coordinates[i][0], coordinates[i][1]);
        }
        assertEquals(Error.NOT_EMPTY_TARGET, error);
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        origin = new Coordinate(2, 3);
        target = new Coordinate(3, 4);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMovementThenEatPiece() {
        Coordinate[][] coordinates = new Coordinate[][] { { new Coordinate(5, 0), new Coordinate(4, 1) },
                { new Coordinate(2, 1), new Coordinate(3, 0) }, { new Coordinate(5, 2), new Coordinate(4, 3) },
                { new Coordinate(3, 0), new Coordinate(5, 2) }, };
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = game.move(coordinates[i][0], coordinates[i][1]);
        }
        assertNull(error);
        assertNull(game.getColor(new Coordinate(3, 0)));
        assertNull(game.getColor(new Coordinate(4, 1)));
        assertEquals(Color.BLACK, game.getColor(new Coordinate(5, 2)));
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError() {
        assertEquals(Error.EATING_EMPTY, this.game.move(new Coordinate(5, 4), new Coordinate(3, 2)));
    }

    @Test
    public void testGivenGameWhenMoveBadDistanceThenError() {
        assertEquals(Error.BAD_DISTANCE, this.game.move(new Coordinate(5, 0), new Coordinate(2, 3)));
    }


    private void setValidOfOriginTargetTo(boolean valid){
        when(origin.isValid()).thenReturn(valid);
        when(target.isValid()).thenReturn(valid);

    }

}