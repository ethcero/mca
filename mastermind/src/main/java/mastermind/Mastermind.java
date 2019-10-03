package mastermind;

import java.util.List;
import java.util.ArrayList;
import santaTecla.utils.WithConsoleModel;
import santaTecla.utils.YesNoDialog;

public class Mastermind extends WithConsoleModel {

	private static final int MAX_LONG = 10;
	private SecretCombination secretCombination;
	private List<ProposedCombination> proposedCombinations;
	private List<Result> results;

	private Mastermind() {
		this.clear();
	}

	private void clear() {
		this.secretCombination = new SecretCombination();
		this.proposedCombinations = new ArrayList<ProposedCombination>();
		this.results = new ArrayList<Result>();
	}

	private void play() {
		Message.TITLE.writeln();
		this.secretCombination.writeln();
		do {
			do {
				ProposedCombination proposedCombination = new ProposedCombination();
				proposedCombination.read();
				this.proposedCombinations.add(proposedCombination);
				this.results.add(this.secretCombination.getResult(proposedCombination));
				this.writeln();
			} while (!isFinished());
		} while (this.isResumed());
	}

	private void writeln() {
		this.console.writeln();
		Message.ATTEMPTS.writeln(this.getAttemps());
		this.secretCombination.writeln();
		for (int i = 0; i < this.getAttemps(); i++) {
			this.proposedCombinations.get(i).write();
			this.results.get(i).writeln();
		}
	}

	private boolean isFinished() {
		if (this.results.get(this.getAttemps() - 1).isWinner()) {
			Message.WINNER.writeln();
			return true;
		}
		if (this.getAttemps() == Mastermind.MAX_LONG) {
			Message.LOOSER.writeln();
			return true;
		}
		return false;
	}

	private int getAttemps() {
		return this.proposedCombinations.size();
	}

	private boolean isResumed() {
		boolean resumed;
		Message.RESUME.write();
		resumed = new YesNoDialog().read();
		if (resumed) {
			this.clear();
		}
		return resumed;
	}

	public static void main(String[] args) {
		new Mastermind().play();
	}

}
