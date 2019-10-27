package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameTest {

    @Test
    public void givenManPieceWhenDiagonalMovementAndAdjacentUnoccupiedSquareThenNotError() {

        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        Game game = new Game(board);

        assertNull(game.move(
                new Coordinate(2,2),
                new Coordinate(3,3)
        ));
    }

    @Test
    public void givenManPieceWhenBackwardMovementThenError() {

        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(3,3));
        Game game = new Game(board);

        assertEquals(Error.BACKWARD_MOVEMENT, game.move(
                new Coordinate(3,3),
                new Coordinate(2,2)
        ));
    }

    @Test
    public void givenMAnPieceWhenDiagonalMovementAndAdjacentOccupiedSquareThenError() {

        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(3,3));
        Game game = new Game(board);

        assertEquals(Error.NOT_EMPTY_SQUARE, game.move(
                new Coordinate(2,2),
                new Coordinate(3,3)
        ));
    }

    @Test
    public void givenManPieceWhenLateralMovementThenError() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        Game game = new Game(board);

        assertEquals(Error.NOT_DIAGONAL_MOVEMENT, game.move(
                new Coordinate(2,2),
                new Coordinate(2,1)
        ));
    }

    @Test
    public void givenManPieceWhenFrontalMovementThenError() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        Game game = new Game(board);

        assertEquals(Error.NOT_DIAGONAL_MOVEMENT, game.move(
                new Coordinate(2,2),
                new Coordinate(3,2)
        ));
    }
    @Test
    public void givenKingPieceWhenLateralMovementThenError() {
        Board board = new Board();
        board.setPiece(new KingPiece(Color.WHITE), new Coordinate(2,2));
        Game game = new Game(board);

        assertEquals(Error.NOT_DIAGONAL_MOVEMENT, game.move(
                new Coordinate(2,2),
                new Coordinate(2,1)
        ));
    }

    @Test
    public void givenKingPieceWhenFrontalMovementThenError() {
        Board board = new Board();
        board.setPiece(new KingPiece(Color.WHITE), new Coordinate(2,2));
        Game game = new Game(board);

        assertEquals(Error.NOT_DIAGONAL_MOVEMENT, game.move(
                new Coordinate(2,2),
                new Coordinate(3,2)
        ));
    }


    @Test
    public void givenManPieceWhenCaptureMovementAndCapturePieceDistinctColorThenNotError() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(3,3));
        Game game = new Game(board);

        assertNull(game.move(
                new Coordinate(2,2),
                new Coordinate(4,4)
        ));
    }

    @Test
    public void givenManPieceWhenCaptureMovementAndCapturePieceSameColorThenError() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(3,3));
        Game game = new Game(board);

        assertEquals(Error.CAPTURE_NOT_ALLOWED, game.move(
                new Coordinate(2,2),
                new Coordinate(4,4)
        ));
    }

    @Test
    public void givenManPieceWhenCaptureMovementAndEmptySquareThenError() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        Game game = new Game(board);

        assertEquals(Error.CAPTURE_NOT_ALLOWED, game.move(
                new Coordinate(2,2),
                new Coordinate(4,4)
        ));
    }

    @Test
    public void givenManPieceWhenCaptureMovementThenDecreaseOpponentPieces() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(3,3));
        Game game = new Game(board);

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
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        Game game = new Game(board);

        assertEquals(Error.NOT_MOVEMENT, game.move(
                new Coordinate(2,2),
                new Coordinate(2,2)
        ));
    }
    @Test
    public void givenMovementWhenEmptySquareThenError() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(3,3));
        Game game = new Game(board);

        assertEquals(Error.EMPTY_SQUARE, game.move(
                new Coordinate(2,2),
                new Coordinate(3,3)
        ));
    }

    @Test
    public void givenMultipleCaptureMovementWhenTooMuchCaptureThenError() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(3,3));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(5,5));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(7,7));
        Game game = new Game(board);

        assertEquals(Error.TOO_MUCH_CAPTURED_PIECES, game.move(
                new Coordinate(2,2),
                new Coordinate(4,4),
                new Coordinate(6,6),
                new Coordinate(8,8)
        ));
    }

    @Test
    public void givenManPieceWhenMultipleCaptureMovementThenNotError() {
        Board board = new Board();
        board.setPiece(new ManPiece(Color.WHITE), new Coordinate(2,2));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(3,3));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(5,5));
        board.setPiece(new ManPiece(Color.BLACK), new Coordinate(7,7));
        Game game = new Game(board);

        assertNull( game.move(
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
}


