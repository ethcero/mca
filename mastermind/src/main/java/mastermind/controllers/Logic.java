package mastermind.controllers;

import java.util.HashMap;
import java.util.Map;

import mastermind.models.Game;
import mastermind.models.Session;
import mastermind.models.State;
import mastermind.models.StateValue;

/**
 * @author fran
 */
public class Logic {

    private Session session;
    private Map<StateValue, AcceptorController> controllers;

    public Logic() {
        session = new Session();
        controllers = new HashMap<>();
        controllers.put(StateValue.INITIAL, new StartController(session));
        controllers.put(StateValue.IN_GAME, new PlayController(session));
        controllers.put(StateValue.FINAL, new ResumeController(session));
        controllers.put(StateValue.EXIT, null);
    }

    public AcceptorController getController() {
       return controllers.get(this.session.getState());
    }

}
