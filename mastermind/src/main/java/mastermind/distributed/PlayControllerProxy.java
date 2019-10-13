package mastermind.distributed;

import mastermind.controllers.PlayController;
import mastermind.models.Color;
import mastermind.models.Error;
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
}
