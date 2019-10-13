package mastermind.controllers;

import mastermind.controllers.implementantion.SessionImplementation;
import mastermind.models.Game;
import mastermind.models.Session;
import mastermind.models.State;

/**
 * @author fran
 */
public class UndoController extends Controller {

    public UndoController(Session session) {
        super(session);
    }

    public void undo() {
        ((SessionImplementation)session).undo();
    }
}
