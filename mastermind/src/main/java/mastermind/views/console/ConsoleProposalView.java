package mastermind.views.console;

import mastermind.models.Color;
import mastermind.models.Error;
import mastermind.models.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
public class ConsoleProposalView extends ConsoleViewModel implements mastermind.views.interfaces.ProposalView {

    public ConsoleProposalView(Session session) {
        super(session);
    }

    @Override
    public List<Color> read() {
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
    @Override
    public void writeln() {
          for (int i = 0; i < session.getAttempts(); i++) {
            console.write(session.getProposed(i).toString());
            new ResultView(session, i).writeln();
        }
    }
}
