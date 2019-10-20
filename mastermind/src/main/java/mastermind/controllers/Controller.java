package mastermind.controllers;

import mastermind.models.Session;
import mastermind.views.AbstractFactoryView;

/**
 * @author fran
 */
public abstract class Controller{

    protected Session session;
    protected AbstractFactoryView factoryView;

    public Controller(AbstractFactoryView factoryView, Session session) {
        this.factoryView = factoryView;
        this.session = session;
    }
    public void next() {
        this.session.nextState();
    }

    public abstract void control();
}
