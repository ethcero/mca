package mastermind;

import mastermind.controllers.AcceptorController;
import mastermind.controllers.Controller;
import mastermind.controllers.Logic;
import mastermind.controllers.implementantion.LogicImplementation;
import mastermind.views.View;

public abstract class Mastermind  {


    private View view;

    protected Mastermind(View view) {
        this.view = view;
    }

    protected void play() {
        Logic logic = getLogic();
        AcceptorController controller = logic.getController();
        do {
            controller.accept(view);
            controller = logic.getController();
        } while (controller != null);
    }

    protected abstract Logic getLogic();

}
