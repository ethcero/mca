package mastermind.controllers.implementantion;

import mastermind.controllers.AcceptorController;
import mastermind.controllers.ControllerVisitor;
import mastermind.controllers.ResumeController;
import mastermind.models.Session;

/**
 * @author fran
 */
public class ResumeControllerImplementation extends ResumeController {

    public ResumeControllerImplementation(Session session) {
        super(session);
    }

    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void resume(boolean resume) {
        if(resume) {
            session.resume(resume);
            session.reset();
        }else{
            this.next();
        }
    }
}
