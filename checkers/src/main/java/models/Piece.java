package models;

import validator.BadDistanceValidator;

public class Piece {

	private Color color;

	Piece(Color color){
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public boolean isAdvanced(Coordinate origin, Coordinate target) {
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE){
			return difference>0;
		}
		return difference<0;
	}

	public Error validateMovement(Coordinate origin, Coordinate target) {
		return new BadDistanceValidator(origin, target, 3).validate();
	}

}