package mastermind.views.console;

import mastermind.controllers.Controller;
import santaTecla.utils.WithConsoleModel;

/**
 * @author fran
 */
public class ConsoleViewModel extends WithConsoleModel {

    protected Controller controller;

    public ConsoleViewModel(Controller controller) {
        this.controller = controller;
    }
}
