package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.State;

/**
 * @author fran
 */
public class RedoController extends Controller {

    public RedoController(Game game, State state) {
        super(game, state);
    }

    public void redo() {
        this.game.redo();
    }
}
