package controllers;

import models.Game;
import models.State;
import views.StartView;

public class StartController extends Controller{

    private Game game;
    private State state;
    private StartView startView;

    public StartController(Game game, State state)
    {
        this.game = game;
        this.state = state;
        startView = new StartView(this);
    }

    public void control() {
        startView.interact();
        this.state.next();
    }
}
