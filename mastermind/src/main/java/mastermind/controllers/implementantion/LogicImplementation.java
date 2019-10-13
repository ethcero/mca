package mastermind.controllers.implementantion;

import mastermind.controllers.*;
import mastermind.models.Session;
import mastermind.models.StateValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fran
 */
public class LogicImplementation extends Logic {

    public LogicImplementation() {
        super();
        session = new SessionImplementation();
        controllers.put(StateValue.INITIAL, new StartControllerImplementation(session));
        controllers.put(StateValue.IN_GAME, new PlayControllerImplementation(session));
        controllers.put(StateValue.FINAL, new ResumeControllerImplementation(session));
        controllers.put(StateValue.EXIT, null);
    }
}
