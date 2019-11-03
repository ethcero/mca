package views;

import controllers.PlayController;
import models.Coordinate;
import models.Error;
import utils.Console;

public class CommandView {

    private Console console;

    private PlayController playController;

    private static final String[] COLORS = {"blancas", "negras"};

    public CommandView(PlayController playController){
        this.playController = playController;
        this.console = new Console();
    }

    public void interact() {
        String color = CommandView.COLORS[playController.getTurn().ordinal()];
        Error error = null;
        do {
            String command = this.console.readString("Mueven las " + color + ": ");
            int origin = Integer.parseInt(command.substring(0, 2));
            int target = Integer.parseInt(command.substring(3, 5));
            error = playController.move(new Coordinate(origin/10, origin%10), new Coordinate(target/10, target%10));
            if (error != null){
                console.write("Error!!!" + error);
            }
        } while (error != null);  
    }

}