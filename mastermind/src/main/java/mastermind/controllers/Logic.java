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
public abstract class Logic {

    protected Session session;
    protected Map<StateValue, AcceptorController> controllers;

    public Logic() {
        controllers = new HashMap<>();
    }

    public AcceptorController getController() {
       return controllers.get(this.session.getState());
    }

}
