package controllers;


import models.*;
import models.Error;
import views.CommandView;
import views.GameView;

public class PlayController extends Controller{

	private Game game;
	private State state;

    public PlayController(Game game, State state)
    {
		this.game = game;
		this.state = state;
	}

	public Error move(Coordinate origin, Coordinate target){

    	return game.move(origin, target);
    }

	public Piece getPiece(Coordinate origin) {
		return null;
	}

	public Color getTurn() {
		return this.game.getTurn();
	}

	public Game getGame() {
        return this.game;
    }

    private void next(){
        if(game.getWinner() != null) {
            state.next();
        }
    }

    @Override
	public void control() {
        new GameView(this).interact();
        new CommandView(this).interact();
        this.next();
    }
}