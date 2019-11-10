package controllers;

import models.Session;

public class StartController extends Controller {

	public StartController(Session session) {
        super(session);
	}

    @Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
    }

	public void start() {
        this.session.next();
	}
    
}
