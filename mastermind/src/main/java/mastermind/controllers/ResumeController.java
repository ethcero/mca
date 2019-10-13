package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.Session;
import mastermind.models.State;

/**
 * @author fran
 */
public class ResumeController extends AcceptorController {

    public ResumeController(Session session) {
        super(session);
    }

    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }

    public void resume(boolean resume) {
        if(resume) {
            session.resume(resume);
            session.reset();
        }else{
            this.next();
        }
    }
}
