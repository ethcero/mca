package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.Session;
import mastermind.models.State;

/**
 * @author fran
 */
public abstract class ResumeController extends AcceptorController {

    public ResumeController(Session session) {
        super(session);
    }

    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }

    public abstract void resume(boolean resume);
}
