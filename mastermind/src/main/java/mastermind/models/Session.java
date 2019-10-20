package mastermind.models;

public class Session {

    private Game game = new Game();
    private State state = new State();

    public Session() {
        reset();
    }

    public void addProposedCombination(ProposedCombination combination) {
        this.game.addProposedCombination(combination);
    }

    public boolean isFinished() {
        return isWinner() || isLooser();
    }

    public boolean isWinner(){

        return this.game.isWinner();
    }

    public boolean isLooser(){
        return this.game.isLooser();
    }

    public void resume(boolean resume) {
        this.game.resume(resume);
    }
    public ProposedCombination getProposed(int index) {
        return this.game.getProposed(index);
    }

    public int getBlacksOf(int index) {
        return this.game.getBlacksOf(index);
    }
    public int getWhitesOf(int index) {
        return this.game.getWhitesOf(index);
    }

    public int getAttempts() {
        return this.game.getAttempts();
    }

    public void reset(){
        this.game.resume(true);
        this.state.reset();
    }

    public StateValue getState() {
        return this.state.getStateValue();
    }
    public void nextState() {
        this.state.next();
    }
}