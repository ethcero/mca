package mastermind.views.console;

import mastermind.controllers.Controller;
import mastermind.controllers.PlayController;

/**
 * @author fran
 */
public class ResultView extends ConsoleViewModel{

    private int index;

    public ResultView(Controller controller, int index) {
        super(controller);
        this.index = index;
    }

    public void writeln(){
        console.writeln(String.format(Message.RESULT.getMessage(),((PlayController)controller).getBlacksOf(index),((PlayController)controller).getWhitesOf(index) ));
    }
}
