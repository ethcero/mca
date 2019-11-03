package models;

public class Game {

	private Board board;
	private Turn turn;

	private int capturedByWhites = 0;
	private int capturedByBlacks = 0;

	public Game() {
		this.board = new Board();
		this.turn = new Turn();
	}

	public Error move(Coordinate origin, Coordinate target) {
		if (!origin.isValid() || !target.isValid()) {
			return Error.OUT_COORDINATE;
		}
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		Color color = this.board.getColor(origin);
		if (!this.turn.isColor(color)) {
			return Error.OPPOSITE_PIECE;
		}
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		Piece piece = this.board.getPiece(origin);
		if (!piece.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}
		if (origin.diagonalDistance(target) >= 3) {
			return Error.BAD_DISTANCE;
		}
		if (!this.board.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		if (origin.diagonalDistance(target) == 2) {
			Coordinate between = origin.betweenDiagonal(target);
			if (this.board.getPiece(between) == null) {
				return Error.EATING_EMPTY;
			}
			this.board.remove(between);
            switch (getTurn()) {
                case BLACK:
                    capturedByBlacks++;
                    break;
                case WHITE:
                    capturedByWhites++;
                    break;
            }
		}
		this.board.move(origin, target);
		this.turn.change();
		return null;
	}

	public Color getWinner(){
	    if(capturedByWhites == 12){
	        return Color.WHITE;
        }else if(capturedByBlacks == 12){
	        return Color.BLACK;
        }
        return null;
    }

	public Color getColor(Coordinate coordinate) {
		return this.board.getColor(coordinate);
	}

	public Color getTurn() {
	    return this.turn.getColor();
    }

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}
}