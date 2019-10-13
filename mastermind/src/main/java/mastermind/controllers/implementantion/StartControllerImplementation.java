package mastermind.controllers.implementantion;

import mastermind.controllers.AcceptorController;
import mastermind.controllers.ControllerVisitor;
import mastermind.controllers.StartController;
import mastermind.models.Session;

/**
 * @author fran
 */
public class StartControllerImplementation extends StartController {

    public StartControllerImplementation(Session session) {
        super(session);
    }

    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void start() {
        this.next();
    }
}
