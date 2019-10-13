package mastermind.controllers;

import mastermind.controllers.implementantion.SessionImplementation;
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

    public abstract boolean isWinner();

    public abstract boolean isLooser();

    public abstract int getBlacksOf(int index);
    public abstract int getWhitesOf(int index);

    public abstract int getAttempts();

    public abstract ProposedCombination getProposed(int index);

    public abstract boolean isRedoable();

    public abstract boolean isUndoable();


    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }
}
