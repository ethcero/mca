package mastermind.views.console;

import mastermind.controllers.PlayController;
import mastermind.models.Color;
import mastermind.models.Combination;
import mastermind.models.ProposedCombination;
import santaTecla.utils.WithConsoleModel;

/**
 * @author fran
 */
public class ConsolePlayModel extends WithConsoleModel {

    private PlayController controller;

    public ConsolePlayModel(PlayController controller) {
        this.controller = controller;
    }

    public void play() {
            ProposedCombination comb = read();
            controller.addProposedCombination(comb);
            controller.calculateResult(comb);
            controller.next();
    }

    private ProposedCombination read() {
        mastermind.models.Error error;

        ProposedCombination combination = new ProposedCombination();
        do {
            error = null;
            console.write(Message.PROPOSED_COMBINATION.getMessage());
            String characters = this.console.readString();
            if (characters.length() != Combination.getWidth()) {
                error = mastermind.models.Error.WRONG_LENGTH;
            } else {
                for (int i = 0; i < characters.length(); i++) {
                    Color color = Color.getInstance(characters.charAt(i));
                    if (color == null) {
                        error = mastermind.models.Error.WRONG_CHARACTERS;
                    } else {
                        for (int j = 0; j < combination.getColors().size(); j++) {
                            if (color == combination.getColors().get(j)) {
                                error = mastermind.models.Error.DUPLICATED;
                            }
                        }
                        combination.getColors().add(color);
                    }
                }
            }
            if (error != null) {
                console.writeln(error.getMessage());
                while (!combination.getColors().isEmpty()) {
                    combination.getColors().remove(0);
                }
            }
        } while (error != null);

        return combination;
    }
}
