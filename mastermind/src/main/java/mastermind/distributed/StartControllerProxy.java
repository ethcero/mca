package mastermind.distributed;

import mastermind.controllers.StartController;
import mastermind.models.Session;

public class StartControllerProxy extends StartController {
    public StartControllerProxy(Session session) {
        super(session);
    }

    @Override
    public void start() {

    }
}
