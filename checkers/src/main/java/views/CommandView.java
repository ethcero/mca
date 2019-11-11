package views;

import controllers.PlayController;
import models.Error;
import models.Coordinate;
import validator.OutCoordinateValidator;

public class CommandView extends SubView {

    private static final String[] COLORS = {"blancas", "negras"};

    private static final String MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";

    public CommandView(){
        super();
    }

    public void interact(PlayController playController) {
        String color = CommandView.COLORS[playController.getColor().ordinal()];
        Error error = null;
         do {
            String command = this.console.readString("Mueven las " + color + ": ");
            int origin = Integer.parseInt(command.substring(0, 2));
            int target = Integer.parseInt(command.substring(3, 5));
            error = this.move(playController,origin,target);
        } while (error != null); 
        if (playController.isBlocked()){
            this.console.write(CommandView.MESSAGE);
        }
    }

    private Error move(PlayController playController, int origin, int target){
        GameView gameView = new GameView();
        Error error = null;
        Coordinate coordinateOrigin =new Coordinate(origin / 10 - 1, origin % 10 - 1);
        Coordinate coordinateTarget = new Coordinate(target / 10 - 1, target % 10 - 1);

        error = new OutCoordinateValidator(coordinateOrigin,  coordinateTarget).validate();
        if(error == null) {
            error = playController.move(coordinateOrigin,coordinateTarget);
        }
        if (error != null){
            console.writeln("Error!!!" + error.name());
            gameView.write(playController);
        }

        return error;
    }
}