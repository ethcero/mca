
package mastermind.views.console;

import mastermind.controllers.Controller;
import mastermind.controllers.PlayController;

/**
 * @author fran
 */
public class GameView extends ConsoleViewModel {

    GameView(Controller controller) {
        super(controller);
    }

    public void writeStartln(){
        console.writeln(Message.TITLE.getMessage());
        this.writeln();
    }

    public void writeln(){
        this.writeAttemptsln();
        new SecretView(controller).writeln();
        new ProposalView(controller).writeln();
    }

    public void writeAttemptsln() {
        console.writeln(String.format(Message.ATTEMPTS.getMessage(), ((PlayController)controller).getAttempts()));
    }
}
