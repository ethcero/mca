package mastermind.views.console;

import mastermind.models.Session;
import santaTecla.utils.WithConsoleModel;

/**
 * @author fran
 */
public class ConsoleViewModel extends WithConsoleModel {

    protected Session session;

    public ConsoleViewModel(Session session) {
        this.session = session;
    }
}
