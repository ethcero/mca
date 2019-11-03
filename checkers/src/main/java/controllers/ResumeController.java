package controllers;

import models.Game;
import models.State;
import views.ResumeView;

public class ResumeController extends Controller {

    private Game game;
    private State state;

    public ResumeController(Game game, State state)
    {
        this.game = game;
        this.state = state;
    }

    public void control() {
        new ResumeView(this).interact();
        this.state.next();
    }
}
