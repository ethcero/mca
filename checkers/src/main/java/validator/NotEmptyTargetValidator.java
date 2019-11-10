package validator;

import models.Board;
import models.Coordinate;
import models.Error;

public class NotEmptyTargetValidator implements Validator {

    private Board board;
    private Coordinate target;

    public NotEmptyTargetValidator(Board board, Coordinate target) {
        this.board = board;
        this.target = target;
    }

    public Error validate() {
        if (!this.board.isEmpty(target)) {
            return Error.NOT_EMPTY_TARGET;
        }
        return null;
    }
}
