package controllers;

import models.Color;
import models.Coordinate;
import models.Piece;
import models.State;
import models.Error;
import models.Game;

public class PlayController extends Controller {

	public PlayController(Game game, State state) {
		super(game, state);
	}

	public void move(Coordinate origin, Coordinate target) {
		assert this.isCorrect(origin, target) == null;
		this.game.move(origin, target);
		if (this.game.isBlocked()) {
			this.state.next();
		}
	}

	public Error isCorrect(Coordinate origin, Coordinate target){
		assert origin != null;
		assert target != null;
		return this.game.isCorrect(origin, target);
	}	

	public Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.game.getPiece(coordinate);
	}

	public Color getColor() {
		return this.game.getColor();
	}

	public boolean isBlocked() {
		return this.game.isBlocked();
	}

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		assert controllersVisitor != null;
		controllersVisitor.visit(this);
	}

}