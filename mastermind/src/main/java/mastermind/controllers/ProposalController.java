package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.ProposedCombination;
import mastermind.models.State;

/**
 * @author fran
 */
public class ProposalController extends Controller{

    public ProposalController(Game game, State state) {
        super(game, state);
    }

    @Override
    public void next() {
        if(game.isFinished()) {
            super.next();
        }
    }

    public void addProposedCombination(ProposedCombination combination) {
        game.addProposedCombination(combination);
    }

    public void calculateResult(ProposedCombination combination) {
        game.calculateResult(combination);
    }


}
