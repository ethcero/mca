/**
 * Copyright (C) 2019 Deveryware S.A. All Rights Reserved.
 */
package models;

/**
 * @author fran
 */
class Pawn extends Piece {

    private static final int MAX_DISTANCE = 2;

    Pawn(Color color) {
        super(color);
    }

    Error customIsCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {

        int distance = origin.diagonalDistance(target);
        if (distance > Pawn.MAX_DISTANCE) {
            return Error.BAD_DISTANCE;
        }
        if (distance == Pawn.MAX_DISTANCE) {
            if (pieceProvider.getPiece(origin.betweenDiagonal(target).get(0)) == null) {
                return Error.EATING_EMPTY;
            }
        }
        return null;
    }

    boolean isAdvanced(Coordinate origin, Coordinate target) {
        assert origin != null;
        assert target != null;
        int difference = origin.getRow() - target.getRow();
        if (color == Color.WHITE) {
            return difference > 0;
        }
        return difference < 0;
    }

}
