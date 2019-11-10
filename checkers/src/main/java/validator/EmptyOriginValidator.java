package validator;

import models.Board;
import models.Coordinate;
import models.Error;

public class EmptyOriginValidator implements Validator {

    private Board board;
    private Coordinate coordinate;

    public EmptyOriginValidator(Board board, Coordinate coordinate) {
        this.board = board;
        this.coordinate = coordinate;
    }

    public Error validate() {
        if (board.isEmpty(coordinate)) {
            return Error.EMPTY_ORIGIN;
        }
        return null;
    }
}
