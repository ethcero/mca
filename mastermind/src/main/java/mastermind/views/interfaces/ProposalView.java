package mastermind.views.interfaces;

import mastermind.models.Color;

import java.util.List;

public interface ProposalView {

    public void writeln();
    public List<Color> read();
}
