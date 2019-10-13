package mastermind.models;

import java.util.ArrayList;
import java.util.List;

public class GameSnapshot {

    private Game game;
    private List<ProposedCombination> proposedCombinations = new ArrayList<>();
    private List<Result> results = new ArrayList<>();

    public GameSnapshot(Game game, List<ProposedCombination> combinations, List<Result> result) {
        this.game = game;
        this.proposedCombinations.addAll(combinations);
        this.results.addAll(result);
    }

    public List<ProposedCombination> getProposedCombinations() {
        return proposedCombinations;
    }

    public List<Result> getResults() {
        return results;
    }

    public void restore() {
        this.game.restore(this);
    }
}
