package mastermind.distributed;

import mastermind.controllers.Logic;
import mastermind.models.StateValue;

public class LogicProxy extends Logic {

    public LogicProxy() {
        super();
        session = new SessionProxy();
        controllers.put(StateValue.INITIAL, new StartControllerProxy(session));
        controllers.put(StateValue.IN_GAME, new PlayControllerProxy(session));
        controllers.put(StateValue.FINAL, new ResumeControllerProxy(session));
        controllers.put(StateValue.EXIT, null);
    }
}
