package views;

import controllers.StartController;
import utils.Console;

public class StartView {

    private Console console;
    private StartController startController;

    public StartView(StartController controller) {
        startController = controller;
        console = new Console();
    }

    public void interact() {
        console.writeln("Las Damas!!!");
    }
}
