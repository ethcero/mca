/**
 * Copyright (C) 2019 Deveryware S.A. All Rights Reserved.
 */
package validator;

import models.Coordinate;
import models.Error;

/**
 * @author fran
 */
public class OutCoordinateValidator implements Validator{

    Coordinate origin;
    Coordinate target;

    public OutCoordinateValidator(Coordinate origin, Coordinate target) {
        this.origin = origin;
        this.target = target;
    }

    public Error validate() {
        if (!origin.isValid() || !target.isValid()) {
            return Error.OUT_COORDINATE;
        }
        return null;
    }
}
