package mastermind.views.console.menu;

import mastermind.controllers.PlayController;

public class RedoAction implements Action {

    private PlayController controller;

    public RedoAction(PlayController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.redo();
    }
}
