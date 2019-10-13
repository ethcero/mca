package mastermind.controllers.implementantion;

import mastermind.controllers.*;
import mastermind.models.Color;
import mastermind.models.Error;
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


    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }
}
