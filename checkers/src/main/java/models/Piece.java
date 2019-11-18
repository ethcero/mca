package models;

public abstract class Piece {

	Color color;


	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

    abstract Error customIsCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider);

    abstract boolean isAdvanced(Coordinate origin, Coordinate target);

    abstract boolean isDraught();

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

		return this.customIsCorrect(origin,target,pieceProvider);
	}

	boolean isLimit(Coordinate coordinate){
		return coordinate.getRow()== 0 && this.getColor() == Color.WHITE ||
		coordinate.getRow()== 7 && this.getColor() == Color.BLACK;
	}

	Color getColor() {
		return this.color;
	}

}