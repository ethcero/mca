package mastermind.views.console;

import mastermind.models.Session;
import mastermind.views.interfaces.ResumeView;
import santaTecla.utils.YesNoDialog;

/**
 * @author fran
 */
public class ConsoleResumeView extends ConsoleViewModel implements ResumeView{

    public ConsoleResumeView(Session session) {
        super(session);
    }

    @Override
    public boolean read() {
        return new YesNoDialog().read();
    }

    @Override
    public void writeWinner() {
        console.writeln(Message.WINNER.getMessage());
    }

    @Override
    public void writeLooser() {
        console.writeln(Message.LOOSER.getMessage());
    }

    @Override
    public void writeln() {
        console.write(Message.RESUME.getMessage());
    }
}
