package mastermind.views;

import mastermind.views.interfaces.GameView;
import mastermind.views.interfaces.ProposalView;
import mastermind.views.interfaces.ResumeView;
import mastermind.views.interfaces.StartView;

public interface AbstractFactoryView {

    public StartView createStartView();
    public GameView createGameView();
    public ProposalView createProposalView();
    public ResumeView createResumeView();
}
