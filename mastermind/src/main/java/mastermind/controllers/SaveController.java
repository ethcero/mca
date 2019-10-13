package mastermind.controllers;

import mastermind.models.Session;

public abstract class SaveController extends AcceptorController {

    public SaveController(Session session) {
        super(session);
    }

    @Override
    public void accept(ControllerVisitor visitor) {
        visitor.visit(this);
    }

    public abstract void save();



}
