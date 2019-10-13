package mastermind.controllers;

import mastermind.models.*;
import mastermind.models.Error;

import java.util.List;

/**
 * @author fran
 */
public abstract class PlayController extends AcceptorController{

    public PlayController(Session session) {
        super(session);
    }

    public abstract Error addProposal(List<Color> colorList);


    public abstract void redo();

    public abstract void undo();


    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }
}
