package controllers;

import models.Game;
import models.State;
import views.StartView;

public class StartController extends Controller{

    private Game game;
    private State state;

    public StartController(Game game, State state)
    {
        this.game = game;
        this.state = state;
    }

    public void control() {
        new StartView(this).interact();
        this.state.next();
    }
}
