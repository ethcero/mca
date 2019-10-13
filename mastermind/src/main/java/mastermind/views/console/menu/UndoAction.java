package mastermind.views.console.menu;

import mastermind.controllers.PlayController;

public class UndoAction implements Action {

    private PlayController controller;

    public UndoAction(PlayController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.undo();
    }
}
