
package mastermind.views.console;

import mastermind.controllers.Controller;

/**
 * @author fran
 */
public class GameView extends ConsoleViewModel {

    GameView(Controller controller) {
        super(controller);
    }

    public void writeln(){
        console.writeln(Message.TITLE.getMessage());
        new SecretView(controller).writeln();
    }
}
