package mastermind.views.console.menu;

import mastermind.controllers.PlayController;
import mastermind.views.console.ConsolePlayModel;

public class ProposeAction implements Action {

    private PlayController controller;

    public ProposeAction(PlayController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        new ConsolePlayModel(controller).play();
    }
}
