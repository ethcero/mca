package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.ProposedCombination;
import mastermind.models.Session;
import mastermind.models.State;

/**
 * @author fran
 */
public abstract class Controller{

    protected Session session;

    public Controller(Session session) {
        this.session = session;
    }


}
