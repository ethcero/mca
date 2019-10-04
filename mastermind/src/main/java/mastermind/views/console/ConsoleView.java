package mastermind.views.console;

import mastermind.controllers.ProposeController;
import mastermind.controllers.ResumeController;
import mastermind.controllers.StartController;
import mastermind.views.View;
import santaTecla.utils.YesNoDialog;

/**
 * @author fran
 */
public class ConsoleView extends View {

    @Override
    public void visit(StartController controller) {

        new GameView(controller).writeln();
        controller.start();
    }

    @Override
    public void visit(ProposeController controller) {

        new ConsolePlayModel(controller).play();
        new ProposeView(controller).writeln();

    }

    @Override
    public void visit(ResumeController controller) {
        new ResumeView(controller).writeln();
        controller.resume(new YesNoDialog().read());
    }

}
