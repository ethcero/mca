package mastermind.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
public class Game {

    private static final int MAX_LONG = 2;

    private SecretCombination secretCombination;
    private List<ProposedCombination> proposedCombinations;
    private List<Result> results;

    public Game() {
        this.clear();
    }

    public void addProposedCombination(ProposedCombination combination) {

        this.proposedCombinations.add(combination);
        this.calculateResult(combination);
    }

    private void calculateResult(ProposedCombination combination) {
        this.results.add(this.secretCombination.getResult(combination));
    }

    private void clear() {
        secretCombination = new SecretCombination();
        proposedCombinations = new ArrayList<>();
        results = new ArrayList<>();
    }

    public boolean isWinner(){
        return (this.results.get(this.getAttempts() - 1).isWinner());
    }

    public boolean isLooser(){
        return this.getAttempts() == MAX_LONG;
    }

    public void resume(boolean resume) {
        if(resume) {
            this.clear();
        }
    }

    public ProposedCombination getProposed(int index) {
        return this.proposedCombinations.get(index);
    }

    public int getBlacksOf(int index) {
        return this.results.get(index).getBlacks();
    }
    public int getWhitesOf(int index) {
        return this.results.get(index).getWhites();
    }

    public int getAttempts() {
        return this.proposedCombinations.size();
    }

}
