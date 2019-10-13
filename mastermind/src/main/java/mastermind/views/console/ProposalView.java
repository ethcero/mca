package mastermind.views.console;

import mastermind.controllers.Controller;
import mastermind.controllers.PlayController;

/**
 * @author fran
 */
public class ProposalView extends ConsoleViewModel {

    public ProposalView(Controller controller) {
        super(controller);
    }

    void writeln() {
        for (int i = 0; i < ((PlayController)controller).getAttempts(); i++) {
            console.write(((PlayController)controller).getProposed(i).toString());
            new ResultView(controller, i).writeln();
        }
    }
}
