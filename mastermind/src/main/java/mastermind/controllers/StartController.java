package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.State;

/**
 * @author fran
 */
public class StartController extends AcceptorController {

    public StartController(Game game, State state) {
        super(game, state);
    }

    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }

    public void start() {
        this.next();
    }
}
