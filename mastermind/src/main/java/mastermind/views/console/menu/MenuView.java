package mastermind.views.console.menu;

import mastermind.controllers.PlayController;
import santaTecla.utils.WithConsoleModel;

/**
 * @author fran
 */
public class MenuView extends WithConsoleModel {

    PlayController playController;

    public MenuView(PlayController playController) {
        this.playController = playController;
    }

    public void execute() {
        //TODO print menu and execute options
    }
}
