package models;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    @Override
    boolean isAdvanced(Coordinate origin, Coordinate target) {
        assert origin != null;
        assert target != null;
        return Math.abs(origin.getRow() - target.getRow()) > 0;
    }

    @Override
    Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        if (!origin.isDiagonal(target)) {
            return Error.NOT_DIAGONAL;
        }
        if (!pieceProvider.isEmpty(target)) {
            return Error.NOT_EMPTY_TARGET;
        }
        if (!this.isAdvanced(origin, target)) {
            return Error.NOT_ADVANCED;
        }
        return null;
    }
}