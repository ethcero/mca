package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.State;

/**
 * @author fran
 */
public class ResumeController extends AcceptorController {

    public ResumeController(Game game, State state) {
        super(game, state);
    }

    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }

    public void resume(boolean resume) {
        if(resume) {
            game.resume(resume);
            state.reset();
        }else{
            this.next();
        }
    }
}
