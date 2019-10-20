package mastermind.views.console;

import mastermind.models.SecretCombination;
import mastermind.models.Session;

/**
 * @author fran
 */
public class SecretView extends ConsoleViewModel {

    public SecretView(Session session) {
        super(session);
    }

    void writeln() {
        for (int i = 0; i < SecretCombination.getWidth(); i++) {
            console.write(Message.SECRET.getMessage());
        }
        console.write(Message.NEW_LINE.getMessage());
    }
}
