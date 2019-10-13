package mastermind.views.console;

import mastermind.controllers.*;
import mastermind.views.View;
import mastermind.views.console.menu.MenuView;
import santaTecla.utils.YesNoDialog;

/**
 * @author fran
 */
public class ConsoleView extends View {

    @Override
    public void visit(StartController controller) {

        new GameView(controller).writeStartln();
        controller.start();
    }

    @Override
    public void visit(PlayController controller) {

        new MenuView(controller).execute();
        new GameView(controller).writeln();
    }

    @Override
    public void visit(ResumeController controller) {
        new ResumeView(controller).writeln();
        controller.resume(new YesNoDialog().read());
    }

    @Override
    public void visit(LoadController controller) {

    }

    @Override
    public void visit(SaveController controller) {

    }
}
