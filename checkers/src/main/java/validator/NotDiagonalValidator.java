package validator;

import models.Coordinate;
import models.Error;

public class NotDiagonalValidator implements Validator {

    private Coordinate origin;
    private Coordinate target;

    public NotDiagonalValidator(Coordinate origin, Coordinate target) {
        this.origin = origin;
        this.target = target;
    }

    public Error validate() {
        if (!origin.isDiagonal(target)) {
            return Error.NOT_DIAGONAL;
        }
        return null;
    }
}
