package views;

import controllers.StartController;

public class StartView extends SubView {

    private static final String TITTLE = "Draughts";

    public StartView(){
        super();
    }

    public void interact(StartController startController) {
        this.console.writeln(StartView.TITTLE);
        new GameView().write(startController);
        startController.start();
    }
}
