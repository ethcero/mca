package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.State;

/**
 * @author fran
 */
public abstract class AcceptorController extends Controller {

    public AcceptorController(Game game, State state) {
        super(game, state);
    }


    public abstract void accept(ControllerVisitor visitor);

}
