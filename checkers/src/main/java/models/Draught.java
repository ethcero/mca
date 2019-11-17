package models;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    @Override
    boolean isAdvanced(Coordinate origin, Coordinate target) {
        assert origin != null;
        assert target != null;
        int difference = origin.getRow() - target.getRow();

        return Math.abs(difference) > 0;
    }
}