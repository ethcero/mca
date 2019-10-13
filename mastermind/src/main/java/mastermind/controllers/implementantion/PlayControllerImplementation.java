package mastermind.controllers.implementantion;

import mastermind.controllers.*;
import mastermind.models.Color;
import mastermind.models.Error;
import mastermind.models.ProposedCombination;
import mastermind.models.Session;

import java.util.List;

/**
 * @author fran
 */
public class PlayControllerImplementation extends PlayController{

    private ProposalController proposalController;
    private UndoController undoController;
    private RedoController redoController;

    public PlayControllerImplementation(Session session) {
        super(session);
        this.proposalController = new ProposalController(session);
        this.undoController = new UndoController(session);
        this.redoController = new RedoController(session);
    }

    public Error addProposal(List<Color> colorList) {
        return this.proposalController.addProposal(colorList);
    }


    public void redo(){
        this.redoController.redo();
    }

    public void undo(){
        this.undoController.undo();
    }

    public boolean isWinner(){
        return ((SessionImplementation)session).isWinner();
    }

    public boolean isLooser(){
        return ((SessionImplementation)session).isLooser();
    }

    public int getBlacksOf(int index) {
        return ((SessionImplementation)session).getBlacksOf(index);
    }
    public int getWhitesOf(int index) {
        return ((SessionImplementation)session).getWhitesOf(index);
    }

    public int getAttempts() {
        return ((SessionImplementation)session).getAttempts();
    }

    public ProposedCombination getProposed(int index) {
        return ((SessionImplementation)session).getProposed(index);
    }

    public boolean isRedoable() {
        return ((SessionImplementation)session).isRedoable();
    }

    public boolean isUndoable() {
        return ((SessionImplementation)session).isUndoable();
    }


    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }
}
