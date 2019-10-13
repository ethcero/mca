package mastermind.controllers;

import mastermind.models.Session;

public abstract class LoadController extends AcceptorController {

    public LoadController(Session session) {
        super(session);
    }

    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }

    public abstract void load();
}
