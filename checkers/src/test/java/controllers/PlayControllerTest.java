package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import models.*;
import org.junit.Test;

public class PlayControllerTest {

    public PlayControllerTest() {

    }

    @Test
    public void givenPlayControllerWhenMovementRequiereCorrectThenNotError() {
        Game game = new Game();
        Coordinate origin = new Coordinate(2, 1);
        Coordinate target = new Coordinate(3, 2);
        PlayController playController = new PlayController(game, new State());
        assertNull(playController.move(origin, target));
        assertNull(playController.getPiece(origin));
        Piece pieceTarget = playController.getPiece(target);
        assertNotNull(pieceTarget);
        assertEquals(pieceTarget.getColor(), Color.WHITE);
    }

    // public void data(){
    //     Coordinate[][] coordinates = new Coordinate[][] {
    //         { new Coordinate(0,0), new Coordinate(0,0) },
    //         { new Coordinate(0,0), new Coordinate(0,0), new Coordinate(0,0), new Coordinate(0,0) },
    //         { new Coordinate(0,0), new Coordinate(0,0) },
    //     };

        // peon mueve una
        // peon come una
        // peon come varias
        // peon se convierte en dama
        // dama mueve una
        // dama mueve varias
        // dama come una
        // dama come varias, atras

    //}


}