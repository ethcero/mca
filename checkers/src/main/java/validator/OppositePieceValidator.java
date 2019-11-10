package validator;

import models.Color;
import models.Error;
import models.Turn;

public class OppositePieceValidator implements Validator {

    private Turn turn;
    private Color color;

    public OppositePieceValidator(Turn turn, Color color) {
        this.turn = turn;
        this.color = color;
    }

    public Error validate() {
        if (this.turn.getColor() != color) {
            return Error.OPPOSITE_PIECE;
        }
        return null;
    }
}
