package models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        setValidOfOriginTargetTo(false,false);
        assertEquals(Error.OUT_COORDINATE, game.move(origin,target));
        setValidOfOriginTargetTo(true,false);
        assertEquals(Error.OUT_COORDINATE, game.move(origin,target));
        setValidOfOriginTargetTo(false,true);
        assertEquals(Error.OUT_COORDINATE, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenMoveEmptySquareThenEmptySquareError() {
        setValidOfOriginTargetTo(true,true);
        when(board.isEmpty(origin)).thenReturn(true);

       assertEquals(Error.EMPTY_ORIGIN, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenMoveOppositePieceThenError() {
        setValidOfOriginTargetTo(true,true);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(any(Color.class))).thenReturn(false);
        assertEquals(Error.OPPOSITE_PIECE, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenNotDiagonalMovementThenError() {
        setValidOfOriginTargetTo(true,true);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(any(Color.class))).thenReturn(true);
        when(origin.isDiagonal(target)).thenReturn(false);
        assertEquals(Error.NOT_DIAGONAL, this.game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenMoveWithNotAdvancedThenError() {
        setValidOfOriginTargetTo(true,true);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(any(Color.class))).thenReturn(true);
        when(origin.isDiagonal(target)).thenReturn(true);
        Piece piece = mock(Piece.class);
        when(piece.isAdvanced(origin,target)).thenReturn(false);
        when(board.getPiece(origin)).thenReturn(piece);
        assertEquals(Error.NOT_ADVANCED, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenNotEmptyTargetThenError() {

        setValidOfOriginTargetTo(true,true);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(any(Color.class))).thenReturn(true);
        when(origin.isDiagonal(target)).thenReturn(true);
        Piece pieceOrigin = mock(Piece.class);
        when(pieceOrigin.isAdvanced(origin,target)).thenReturn(true);
        when(board.getPiece(origin)).thenReturn(pieceOrigin);
        when(board.isEmpty(target)).thenReturn(false);

        assertEquals(Error.NOT_EMPTY_TARGET, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenBadDistanceThenError() {

        setValidOfOriginTargetTo(true,true);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(turn.isColor(any(Color.class))).thenReturn(true);
        when(origin.isDiagonal(target)).thenReturn(true);
        Piece pieceOrigin = mock(Piece.class);
        when(pieceOrigin.isAdvanced(origin,target)).thenReturn(true);
        when(board.getPiece(origin)).thenReturn(pieceOrigin);
        when(origin.diagonalDistance(target)).thenReturn(3);

        assertEquals(Error.BAD_DISTANCE, game.move(origin,target));
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk() {
        mockValidMovement(Color.WHITE, 1);
        this.game.move(origin, target);
        assertEquals(Color.WHITE, this.game.getColor(target));

        mockValidMovement(Color.BLACK, 1);
        this.game.move(origin, target);
        assertEquals(Color.BLACK, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMovementThenEatPiece() {
        mockValidMovement(Color.WHITE, 2);
        Coordinate between = mock(Coordinate.class);
        when(origin.betweenDiagonal(target)).thenReturn(between);
        when(board.getPiece(between)).thenReturn(new Piece(Color.BLACK));
        this.game.move(origin, target);
        assertEquals(Color.WHITE, this.game.getColor(target));
        verify(board).remove(between);

        mockValidMovement(Color.BLACK, 2);
        Coordinate between2 = mock(Coordinate.class);
        when(origin.betweenDiagonal(target)).thenReturn(between2);
        when(board.getPiece(between2)).thenReturn(new Piece(Color.WHITE));
        this.game.move(origin, target);
        assertEquals(Color.BLACK, this.game.getColor(target));
        verify(board).remove(between2);
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError() {
        mockValidMovement(Color.BLACK, 2);
        when(origin.betweenDiagonal(target)).thenReturn(null);
        assertEquals(Error.EATING_EMPTY, this.game.move(origin, target));
    }


    private void setValidOfOriginTargetTo(boolean origin, boolean target){
        when(this.origin.isValid()).thenReturn(origin);
        when(this.target.isValid()).thenReturn(target);
    }

    private void mockValidMovement(Color turn, int distance) {
        setValidOfOriginTargetTo(true,true);
        when(board.getColor(origin)).thenReturn(turn);
        when(board.getColor(target)).thenReturn(turn);
        when(this.turn.getColor()).thenReturn(turn);
        when(this.turn.isColor(any(Color.class))).thenReturn(true);
        when(origin.isDiagonal(target)).thenReturn(true);
        Piece pieceOrigin = mock(Piece.class);
        when(pieceOrigin.isAdvanced(origin,target)).thenReturn(true);
        when(board.getPiece(origin)).thenReturn(pieceOrigin);
        when(board.isEmpty(target)).thenReturn(true);
        when(origin.diagonalDistance(target)).thenReturn(distance);
    }

}