package models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TurnTest {

    @Test
    public void givenTurnWhitesThenChangeToBlacks() {
        Turn turn = new Turn(Color.WHITE);
        turn.next();
        assertEquals(Color.BLACK,turn.getTurn());
    }

    @Test
    public void givenTurnBlacksThenChangeToWhites() {
        Turn turn = new Turn(Color.BLACK);
        turn.next();
        assertEquals(Color.WHITE,turn.getTurn());
    }
}
