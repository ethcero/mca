package mastermind.controllers;

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
        this.session.undo();
    }
}
