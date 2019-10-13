package mastermind.distributed;

import mastermind.controllers.PlayController;
import mastermind.models.Color;
import mastermind.models.Error;
import mastermind.models.ProposedCombination;
import mastermind.models.Session;

import java.util.List;

public class PlayControllerProxy extends PlayController {

    public PlayControllerProxy(Session session) {
        super(session);
    }

    @Override
    public Error addProposal(List<Color> colorList) {
        return null;
    }

    @Override
    public void redo() {

    }

    @Override
    public void undo() {

    }

    @Override
    public boolean isWinner() {
        return false;
    }

    @Override
    public boolean isLooser() {
        return false;
    }

    @Override
    public int getBlacksOf(int index) {
        return 0;
    }

    @Override
    public int getWhitesOf(int index) {
        return 0;
    }

    @Override
    public int getAttempts() {
        return 0;
    }

    @Override
    public ProposedCombination getProposed(int index) {
        return null;
    }

    @Override
    public boolean isRedoable() {
        return false;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
