package controllers;

import models.Game;
import models.State;
import views.ResumeView;

public class ResumeController extends Controller {

    private Game game;
    private State state;
    private ResumeView resumeView;

    public ResumeController(Game game, State state)
    {
        this.game = game;
        this.state = state;
        resumeView = new ResumeView(this);
    }

    public void control() {
        resumeView.interact();
        this.state.next();
    }
}
