package controllers;

import models.Color;
import models.Coordinate;
import models.Game;
import models.State;

public abstract class Controller {

	protected Game game;
	protected State state;

	protected Controller(Game game, State state) {
		assert game != null;
		assert state != null;
		this.game = game;
		this.state = state;
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.game.getColor(coordinate);
	}

	public int getDimension() {
		return this.game.getDimension();
	}

	abstract public void accept(ControllersVisitor controllersVisitor);

}
