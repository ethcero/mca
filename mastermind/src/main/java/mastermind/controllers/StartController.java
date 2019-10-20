package mastermind.controllers;

import mastermind.models.Session;
import mastermind.views.AbstractFactoryView;

/**
 * @author fran
 */
public class StartController extends Controller {
    public StartController(AbstractFactoryView factoryView, Session session) {
        super(factoryView, session);
    }

    @Override
    public void control() {
        this.factoryView.createStartView().writeln();
        this.next();
    }
}
