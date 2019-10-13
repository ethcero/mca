package mastermind.views.console;

import mastermind.controllers.Controller;
import mastermind.controllers.PlayController;

/**
 * @author fran
 */
public class ResumeView extends ConsoleViewModel{

    public ResumeView(Controller controller) {
        super(controller);
    }

    public void writeln() {
        if(((PlayController)controller).isWinner()) {
            console.writeln(Message.WINNER.getMessage());
        }else if(((PlayController)controller).isLooser()) {
            console.writeln(Message.LOOSER.getMessage());
        }
        console.write(Message.RESUME.getMessage());
    }
}
