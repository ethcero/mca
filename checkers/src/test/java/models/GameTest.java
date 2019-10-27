package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameTest {


    @Test
    public void givenManPieceWhenDiagonalMovementAndAdjacentUnoccupiedSquareThenNotError() {
        assertNull(createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(3,3)
        ));
    }

    @Test
    public void givenManPieceWhenBackwardMovementThenError() {

        assertEquals(Error.BACKWARD_MOVEMENT, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(1,1)
        ));
    }

    @Test
    public void givenMAnPieceWhenDiagonalMovementAndAdjacentOccupiedSquareThenError() {
        assertEquals(Error.NOT_EMPTY_SQUARE, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(3,3)
        ));
    }

    @Test
    public void givenManPieceWhenLateralMovementThenError() {
        assertEquals(Error.NOT_DIAGONAL_MOVEMENT, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(2,1)
        ));
    }

    @Test
    public void givenManPieceWhenFrontalMovementThenError() {
       assertEquals(Error.NOT_DIAGONAL_MOVEMENT, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(3,2)
        ));
    }
    @Test
    public void givenKingPieceWhenLateralMovementThenError() {
        assertEquals(Error.NOT_DIAGONAL_MOVEMENT, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(2,1)
        ));
    }

    @Test
    public void givenKingPieceWhenFrontalMovementThenError() {
        assertEquals(Error.NOT_DIAGONAL_MOVEMENT, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(3,2)
        ));
    }


    @Test
    public void givenManPieceWhenCaptureMovementAndCapturePieceDistinctColorThenNotError() {

        assertNull(createSimpleBlackWhiteGame().move(
                new Coordinate(2,2),
                new Coordinate(4,4)
        ));
    }

    @Test
    public void givenManPieceWhenCaptureMovementAndCapturePieceSameColorThenError() {
        assertEquals(Error.CAPTURE_NOT_ALLOWED, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(4,4)
        ));
    }

    @Test
    public void givenManPieceWhenCaptureMovementAndEmptySquareThenError() {
        assertEquals(Error.CAPTURE_NOT_ALLOWED, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(4,4)
        ));
    }

    @Test
    public void givenManPieceWhenCaptureMovementThenDecreaseOpponentPieces() {
        Game game = createSimpleBlackWhiteGame();

        Color opponentColor = game.getTurn();
        int opponentPieces = game.getPieces(opponentColor).size();
        game.nextTurn();
        game.move(
                new Coordinate(2,2),
                new Coordinate(4,4)
        );
        assertEquals(opponentPieces-1, game.getPieces(opponentColor).size());
    }

    @Test
    public void givenNoMovementThenError() {
        assertEquals(Error.NOT_MOVEMENT, createSimpleGame().move(
                new Coordinate(2,2),
                new Coordinate(2,2)
        ));
    }
    @Test
    public void givenMovementWhenEmptySquareThenError() {
        assertEquals(Error.EMPTY_SQUARE, createSimpleGame().move(
                new Coordinate(1,1),
                new Coordinate(3,3)
        ));
    }

    @Test
    public void givenMultipleCaptureMovementWhenTooMuchCaptureThenError() {


        assertEquals(Error.TOO_MUCH_CAPTURED_PIECES, createComplexGame().move(
                new Coordinate(2,2),
                new Coordinate(4,4),
                new Coordinate(6,6),
                new Coordinate(8,8)
        ));
    }

    @Test
    public void givenManPieceWhenMultipleCaptureMovementThenNotError() {
        assertNull( createComplexGame().move(
                new Coordinate(2,2),
                new Coordinate(4,4),
                new Coordinate(6,6),
                new Coordinate(8,8)
        ));
    }

    @Test
    public void givenKingPieceWhenMoveToAdjacentOfAdjacentThenNotError() {

        Board board = new Board();
        board.setPiece(new KingPiece(Color.WHITE), new Coordinate(2,2));
        Game game = new Game(board);
        assertNull( game.move(
                new Coordinate(2,2),
                new Coordinate(4,4)
        ));
    }

    private Game createSimpleGame() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(3,3));

        return new Game(board);
    }

    private Game createSimpleBlackWhiteGame() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(3,3));
        return new Game(board);

    }

    private Game createComplexGame() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(3,3));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(5,5));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(7,7));
        return new Game(board);
    }

}


