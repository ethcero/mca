package mastermind.views.console;

import mastermind.controllers.Controller;

/**
 * @author fran
 */
public class ProposeView extends ConsoleViewModel {

    public ProposeView(Controller controller) {
        super(controller);
    }

    void writeln() {
        console.writeln(String.format(Message.ATTEMPTS.getMessage(), controller.getAttempts()));
        new SecretView(controller).writeln();
        for (int i = 0; i < controller.getAttempts(); i++) {
            console.write(controller.getProposed(i).toString());
            new ResultView(controller, i).writeln();
        }
    }
}
