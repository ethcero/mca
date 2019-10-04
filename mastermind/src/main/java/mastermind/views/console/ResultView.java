package mastermind.views.console;

import mastermind.controllers.Controller;

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
        console.writeln(String.format(Message.RESULT.getMessage(),controller.getBlacksOf(index),controller.getWhitesOf(index) ));
    }
}
