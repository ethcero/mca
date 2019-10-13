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

    public void next() {
        this.session.nextState();
    }

    public boolean isWinner(){
        return this.session.isWinner();
    }

    public boolean isLooser(){
        return this.session.isLooser();
    }

    public int getBlacksOf(int index) {
        return this.session.getBlacksOf(index);
    }
    public int getWhitesOf(int index) {
        return this.session.getWhitesOf(index);
    }

    public int getAttempts() {
        return session.getAttempts();
    }

    public ProposedCombination getProposed(int index) {
        return session.getProposed(index);
    }

    public boolean isRedoable() {
        return this.session.isRedoable();
    }

    public boolean isUndoable() {
        return this.session.isUndoable();
    }
}
