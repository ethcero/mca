package mastermind;

import mastermind.controllers.Controller;
import mastermind.controllers.ProposalController;
import mastermind.controllers.ResumeController;
import mastermind.controllers.StartController;
import mastermind.models.Session;
import mastermind.models.StateValue;
import mastermind.views.AbstractFactoryView;

import java.util.HashMap;
import java.util.Map;

public abstract class Mastermind  {

    private Map<StateValue,Controller> controllers;
    private Session session;
    public Mastermind() {
         session = new Session();
        AbstractFactoryView factoryView = createFactoryView(session);
        controllers = new HashMap<>();
        controllers.put(StateValue.INITIAL, new StartController(factoryView,session));
        controllers.put(StateValue.IN_GAME, new ProposalController(factoryView,session));
        controllers.put(StateValue.FINAL, new ResumeController(factoryView,session));
        controllers.put(StateValue.EXIT, null);
    }

    protected void play() {
        Controller controller = this.controllers.get(this.session.getState());
        do {
            controller.control();
            controller = this.controllers.get(this.session.getState());
        } while (controller != null);
    }

    public abstract AbstractFactoryView createFactoryView(Session session);

}