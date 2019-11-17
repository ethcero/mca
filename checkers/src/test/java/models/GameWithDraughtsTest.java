package models;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameWithDraughtsTest {

    @Mock
    Turn turn;

    @Mock
    Piece piece;
    
    @Mock
    Board board;

    @InjectMocks
    Game game;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraughts(){
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);

        this.mockValidMovement(origin, target, Color.WHITE );

        game.move(origin, target);
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraughts(){
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);

        this.mockValidMovement(origin, target, Color.BLACK );

        game.move(origin, target);
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenPawnAtLimitAndEatingThenNewDraughts(){
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(0,3);

        this.mockValidMovement(origin, target, Color.WHITE );

        game.move(origin, target);
        verify(board).remove(origin.betweenDiagonal(target));
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitAndEatingThenNewDraughts(){
        Coordinate origin = new Coordinate(5,4);
        Coordinate target = new Coordinate(7,2);

        this.mockValidMovement(origin, target, Color.BLACK );

        game.move(origin, target);
        verify(board).remove(origin.betweenDiagonal(target));
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenDraughtMovementThenEatPiece() {
        Coordinate origin = mock(Coordinate.class);
        Coordinate target =  mock(Coordinate.class);
        mockValidMovement(origin, target, Color.WHITE);

        Coordinate between = mock(Coordinate.class);
        when(origin.betweenDiagonal(target)).thenReturn(between);
        when(board.getPiece(between)).thenReturn(new Piece(Color.BLACK));

        this.game.move(origin, target);
        verify(board).remove(between);
    }

    private void mockValidMovement(Coordinate origin, Coordinate target, Color pieceColor){
        when (turn.getColor()).thenReturn(pieceColor);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(pieceColor);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Piece(pieceColor));
        when(board.getPiece(target)).thenReturn(new Piece(pieceColor));
    }
}