package mastermind.controllers;

import mastermind.controllers.implementantion.SessionImplementation;
import mastermind.models.Game;
import mastermind.models.Session;
import mastermind.models.State;

/**
 * @author fran
 */
public class RedoController extends Controller {

    public RedoController(Session session) {
        super(session);
    }

    public void redo() {
        ((SessionImplementation)session).redo();
    }
}
