package mastermind;

import mastermind.models.Session;
import mastermind.views.AbstractFactoryView;
import mastermind.views.console.factory.ConsoleFactoryView;

/**
 * @author fran
 */
public class MastermindConsole extends Mastermind {


    public static void main(String[] args) {
        new MastermindConsole().play();
    }

    @Override
    public AbstractFactoryView createFactoryView(Session session) {
        return new ConsoleFactoryView(session);
    }
}