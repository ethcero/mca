package mastermind;

import mastermind.controllers.Controller;
import mastermind.controllers.Logic;
import mastermind.views.View;
import mastermind.views.console.ConsoleView;
import santaTecla.utils.WithConsoleModel;

public class Mastermind extends WithConsoleModel {

    private void play() {

        Logic logic = new Logic();
        View view = new ConsoleView();

        Controller controller = logic.getController();

        do {
            controller.accept(view);
            controller = logic.getController();
        } while (controller != null);
    }

    public static void main(String[] args) {
        new Mastermind().play();
    }

}
