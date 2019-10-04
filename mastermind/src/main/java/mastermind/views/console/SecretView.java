package mastermind.views.console;

import mastermind.controllers.Controller;
import mastermind.models.SecretCombination;

/**
 * @author fran
 */
public class SecretView extends ConsoleViewModel {

    public SecretView(Controller controller) {
        super(controller);
    }

    void writeln() {
        for (int i = 0; i < SecretCombination.getWidth(); i++) {
            console.write(Message.SECRET.getMessage());
        }
        console.write(Message.NEW_LINE.getMessage());
    }
}
