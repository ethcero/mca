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
}