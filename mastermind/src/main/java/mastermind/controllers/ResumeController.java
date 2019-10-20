package mastermind.controllers;

import mastermind.models.Session;
import mastermind.views.AbstractFactoryView;
import mastermind.views.interfaces.ResumeView;

/**
 * @author fran
 */
public class ResumeController extends Controller {

    public ResumeController(AbstractFactoryView factoryView, Session session) {
        super(factoryView, session);
    }

    private void resume(boolean resume) {
        if(resume) {
            this.session.resume(resume);
            this.session.reset();
        }else{
            this.next();
        }
    }

    @Override
    public void control() {
        ResumeView resumeView = this.factoryView.createResumeView();
        if(this.session.isWinner()){
            resumeView.writeWinner();
        }else {
            resumeView.writeLooser();
        }
        resumeView.writeln();
        this.resume(resumeView.read());
    }
}
