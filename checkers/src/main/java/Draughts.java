import controllers.Controller;
import controllers.PlayController;
import controllers.ResumeController;
import controllers.StartController;
import models.Game;
import models.State;
import models.StateValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Draughts {

    State state;
    Map<StateValue,Controller> controllers;

    Draughts(){

        Game game = new Game();
        state  = new State();
        controllers = new HashMap<StateValue, Controller>();
        controllers.put(StateValue.INITIAL, new StartController(game,state));
        controllers.put(StateValue.IN_GAME, new PlayController(game,state));
        controllers.put(StateValue.FINAL, new ResumeController(game,state));
        controllers.put(StateValue.EXIT, null);
    }

    private Controller getController() {
        return this.controllers.get(state.getStateValue());
    }

    public static void main(String args[]) {

        Draughts draughts = new Draughts();
        Controller controller = draughts.getController();

        do {
            controller.control();
            controller = draughts.getController();
        }while (controller != null);
    }
}
