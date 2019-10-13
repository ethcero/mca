package mastermind.views.console;

import mastermind.controllers.PlayController;
import mastermind.models.Color;
import mastermind.models.Error;
import santaTecla.utils.WithConsoleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
public class ConsolePlayModel extends WithConsoleModel {

    private PlayController controller;

    public ConsolePlayModel(PlayController controller) {
        this.controller = controller;
    }

    public void play() {
        Error error;

            do {
                error = controller.addProposal(read());
                if (error != null) {
                    console.writeln(error.getMessage());
                }
            } while(error != null);
    }

    private List<Color> read() {
        List<Color> colors = new ArrayList<>();
        Error error;
        do {
            console.write(Message.PROPOSED_COMBINATION.getMessage());
            String characters = this.console.readString();
            error = parseColors(colors, characters);
            if (error != null) {
                console.writeln(error.getMessage());
            }
        } while (error != null);

        return colors;
    }

    private Error parseColors(List<Color> list, String characters) {
        for (int i = 0; i < characters.length(); i++) {
            Color color = Color.getInstance(characters.charAt(i));
            if (color == null) {
                return Error.WRONG_CHARACTERS;
            } else {
                list.add(color);
            }
        }
        return null;
    }
}
