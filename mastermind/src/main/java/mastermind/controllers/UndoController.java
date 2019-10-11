package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.State;

/**
 * @author fran
 */
public class UndoController extends Controller {

    public UndoController(Game game, State state) {
        super(game, state);
    }

    public void undo() {
        this.game.undo();
    }
}
