package models;

import java.util.ArrayList;
import java.util.List;

import validator.EmptyOriginValidator;
import validator.NotAdvancedValidator;
import validator.NotDiagonalValidator;
import validator.NotEmptyTargetValidator;
import validator.OppositePieceValidator;
import validator.Validator;

public class Game {

	private Board board;

	private Turn turn;

	public Game() {
		this.turn = new Turn();
		this.board = new Board();
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getInitialPiece(coordinate);
				if (piece != null) {
					this.board.put(coordinate, piece);
				}
			}
		}
	}

	private Piece getInitialPiece(Coordinate coordinate) {
		if (coordinate.isBlack()) {
			final int row = coordinate.getRow();
			Color color = null;
			if (row <= 2) {
				color = Color.BLACK;
			} else if (row >= 5) {
				color = Color.WHITE;
			}
			if (color != null) {
				return new Piece(color);
			}
		}
		return null;
	}

	private Error validateMovement(Coordinate origin, Coordinate target){
        Error error = null;

	    List<Validator> validators = new ArrayList<Validator>();
		validators.add(new EmptyOriginValidator(board, origin));
		validators.add(new OppositePieceValidator(turn,this.board.getColor(origin)));
        validators.add(new NotDiagonalValidator(origin, target));
        validators.add(new NotAdvancedValidator(this.board.getPiece(origin), origin, target));
		validators.add(new NotEmptyTargetValidator(board,target));

		for (Validator validator : validators) {
			error = validator.validate();
			if(error != null){
			    return error;
            }
		}

		error = this.board.getPiece(origin).validateMovement(origin, target);

		return error;
	}

	public Error move(Coordinate origin, Coordinate target) {
		assert origin != null && target != null;

        Error error = validateMovement(origin, target);
        if(error != null){
            return error;
        }

		if (origin.diagonalDistance(target) == 2) {
			Coordinate between = origin.betweenDiagonal(target);
			if (this.board.getPiece(between) == null) {
				return Error.EATING_EMPTY;
			}
			this.board.remove(between);
		}
		this.board.move(origin, target);
		this.turn.change();
		return null;
	}

	public Color getColor(Coordinate coordinate) {
		return this.board.getColor(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.board.getPiece(coordinate);
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}

}