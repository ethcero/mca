package mastermind.views.console;

import mastermind.controllers.Controller;

/**
 * @author fran
 */
public class ProposalView extends ConsoleViewModel {

    public ProposalView(Controller controller) {
        super(controller);
    }

    void writeln() {
        for (int i = 0; i < controller.getAttempts(); i++) {
            console.write(controller.getProposed(i).toString());
            new ResultView(controller, i).writeln();
        }
    }
}
