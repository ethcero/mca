package validator;

import models.Coordinate;
import models.Error;

public class BadDistanceValidator implements Validator {

    private Coordinate origin;
    private Coordinate target;
    private int maxDistanceAllowed;

    public BadDistanceValidator(Coordinate origin, Coordinate target, int maxDistanceAllowed) {
        this.origin = origin;
        this.target = target;
        this.maxDistanceAllowed = maxDistanceAllowed;
    }

    public Error validate() {
        if (origin.diagonalDistance(target) >= maxDistanceAllowed) {
            return Error.BAD_DISTANCE;
        }
        return null;
    }
}
