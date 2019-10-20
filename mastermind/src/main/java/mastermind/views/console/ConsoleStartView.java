package mastermind.views.console;

import mastermind.models.Session;
import mastermind.views.interfaces.StartView;

public class ConsoleStartView extends ConsoleViewModel implements StartView {

    public ConsoleStartView(Session session) {
        super(session);
    }

    @Override
    public void writeln() {
        console.writeln(Message.TITLE.getMessage());
        new SecretView(session).writeln();
    }
}
