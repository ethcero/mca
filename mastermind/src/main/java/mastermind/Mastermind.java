package mastermind;

import mastermind.controllers.Controller;
import mastermind.controllers.Logic;
import mastermind.views.View;

public abstract class Mastermind  {


    private View view;

    public Mastermind(View view) {
        this.view = view;
    }

    protected void play() {
        Logic logic = new Logic();
        Controller controller = logic.getController();
        do {
            controller.accept(view);
            controller = logic.getController();
        } while (controller != null);
    }

}
