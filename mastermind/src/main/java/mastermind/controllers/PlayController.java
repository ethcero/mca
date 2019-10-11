package mastermind.controllers;

import mastermind.models.Game;
import mastermind.models.ProposedCombination;
import mastermind.models.State;

/**
 * @author fran
 */
public class PlayController extends AcceptorController{

    private ProposalController proposalController;
    private UndoController undoController;
    private RedoController redoController;

    public PlayController(Game game, State state) {
        super(game, state);
        this.proposalController = new ProposalController(game,state);
        this.undoController = new UndoController(game,state);
        this.redoController = new RedoController(game,state);
    }

    public void addProposedCombination(ProposedCombination combination) {
        this.proposalController.addProposedCombination(combination);
    }

    public void calculateResult(ProposedCombination combination) {
        this.proposalController.calculateResult(combination);
    }

    public void redo(){
        this.redoController.redo();
    }

    public void undo(){
        this.undoController.undo();
    }


    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }
}
