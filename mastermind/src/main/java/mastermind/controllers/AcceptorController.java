package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.Session;
import mastermind.models.State;

/**
 * @author fran
 */
public abstract class AcceptorController extends Controller {

    public AcceptorController(Session session) {
        super(session);
    }


    public abstract void accept(ControllerVisitor visitor);

}
