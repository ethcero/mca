package mastermind.controllers;

import mastermind.models.*;
import mastermind.models.Error;

import java.util.List;

/**
 * @author fran
 */
public class PlayController extends AcceptorController{

    private ProposalController proposalController;
    private UndoController undoController;
    private RedoController redoController;

    public PlayController(Session session) {
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


    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }
}
