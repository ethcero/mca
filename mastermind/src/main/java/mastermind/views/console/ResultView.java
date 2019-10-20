package mastermind.views.console;

import mastermind.models.Session;

/**
 * @author fran
 */
public class ResultView extends ConsoleViewModel{

    private int index;

    public ResultView(Session session, int index) {
        super(session);
        this.index = index;
    }

    public void writeln(){
        console.writeln(String.format(Message.RESULT.getMessage(),session.getBlacksOf(index),session.getWhitesOf(index) ));
    }
}
