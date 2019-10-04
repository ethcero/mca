package mastermind.controllers;

import java.util.HashMap;
import java.util.Map;

import mastermind.models.Game;
import mastermind.models.State;
import mastermind.models.StateValue;

/**
 * @author fran
 */
public class Logic {

    private Game game;
    private State state;
    private Map<StateValue, Controller> controllers;

    public Logic() {
        game = new Game();
        state = new State();
        controllers = new HashMap<>();
        controllers.put(StateValue.INITIAL, new StartController(game, state));
        controllers.put(StateValue.IN_GAME, new ProposeController(game, state));
        controllers.put(StateValue.FINAL, new ResumeController(game, state));
        controllers.put(StateValue.EXIT, null);
    }

    public Controller getController() {
       return controllers.get(this.state.getStateValue());
    }

}
