package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.ProposedCombination;
import mastermind.models.State;

/**
 * @author fran
 */
public abstract class Controller{

    protected Game game;
    protected State state;

    public Controller(Game game, State state) {
        this.game = game;
        this.state = state;
    }

    public void next() {
        this.state.next();
    }

    public boolean isWinner(){
        return this.game.isWinner();
    }

    public boolean isLooser(){
        return this.game.isLooser();
    }

    public int getBlacksOf(int index) {
        return this.game.getBlacksOf(index);
    }
    public int getWhitesOf(int index) {
        return this.game.getWhitesOf(index);
    }

    public int getAttempts() {
        return game.getAttempts();
    }

    public ProposedCombination getProposed(int index) {
        return game.getProposed(index);
    }

}
