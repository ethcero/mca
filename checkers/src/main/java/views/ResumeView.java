package views;

import controllers.ResumeController;
import utils.Console;

public class ResumeView {

    private Console console;
    private ResumeController controller;

    public ResumeView(ResumeController controller) {
        this.controller = controller;
        this.console = new Console();
    }

    public void interact(){
        this.console.writeln("FIN!!");
    }
}
