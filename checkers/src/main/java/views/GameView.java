package views;

import controllers.PlayController;
import utils.Console;

public class GameView {

    private Console console;

    private PlayController playController;

    private static final String[] COLORS = {"blancas", "negras"};

    public GameView(PlayController playController){
        this.playController = playController;
        this.console = new Console();
    }

    public void interact(){
        console.writeln(playController.getGame().toString());
    }

}
