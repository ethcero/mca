package validator;

import models.Coordinate;
import models.Error;
import models.Piece;

public class NotAdvancedValidator implements Validator {

    private Piece piece;
    private Coordinate origin;
    private Coordinate target;

    public NotAdvancedValidator(Piece piece, Coordinate origin, Coordinate target) {
        this.piece = piece;
        this.origin = origin;
        this.target = target;
    }

    public Error validate() {
        if (!piece.isAdvanced(origin, target)) {
            return Error.NOT_ADVANCED;
        }
        return null;
    }
}
