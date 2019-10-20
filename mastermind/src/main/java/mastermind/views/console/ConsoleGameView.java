
package mastermind.views.console;

import mastermind.models.Session;
import mastermind.views.interfaces.GameView;

/**
 * @author fran
 */
public class ConsoleGameView extends ConsoleViewModel implements GameView {

    public ConsoleGameView(Session session) {
        super(session);
    }

    @Override
    public void writeln(){
        new AttemptsView(session).writeln();
        new SecretView(session).writeln();
        new ConsoleProposalView(session).writeln();
    }
}
