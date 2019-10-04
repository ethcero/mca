package mastermind.views.console;

import mastermind.controllers.Controller;

/**
 * @author fran
 */
public class ResumeView extends ConsoleViewModel{

    public ResumeView(Controller controller) {
        super(controller);
    }

    public void writeln() {
        if(controller.isWinner()) {
            console.writeln(Message.WINNER.getMessage());
        }else if(controller.isLooser()) {
            console.writeln(Message.LOOSER.getMessage());
        }
        console.write(Message.RESUME.getMessage());
    }
}
