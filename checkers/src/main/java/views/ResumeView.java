package views;

import controllers.ResumeController;
import utils.YesNoDialog;

public class ResumeView extends SubView {

    private static final String MESSAGE = "¿Queréis jugar otra";
    
    private YesNoDialog yesNoDialog;

    public ResumeView(){
        super();
        this.yesNoDialog = new YesNoDialog();
    }

    public void interact(ResumeController resumeController) {
        if (this.yesNoDialog.read(ResumeView.MESSAGE)){
            resumeController.reset();
        } else {
            resumeController.next();
        }

    }
}
