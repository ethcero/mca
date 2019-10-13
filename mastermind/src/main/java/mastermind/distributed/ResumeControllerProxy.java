package mastermind.distributed;

import mastermind.controllers.ResumeController;
import mastermind.models.Session;

public class ResumeControllerProxy  extends ResumeController{

    public ResumeControllerProxy(Session session) {
        super(session);
    }

    @Override
    public void resume(boolean resume) {

    }
}
