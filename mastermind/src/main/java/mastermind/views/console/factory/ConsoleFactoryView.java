package mastermind.views.console.factory;

import mastermind.models.Session;
import mastermind.views.AbstractFactoryView;
import mastermind.views.console.ConsoleGameView;
import mastermind.views.console.ConsoleProposalView;
import mastermind.views.console.ConsoleResumeView;
import mastermind.views.console.ConsoleStartView;
import mastermind.views.interfaces.GameView;
import mastermind.views.interfaces.ProposalView;
import mastermind.views.interfaces.ResumeView;
import mastermind.views.interfaces.StartView;

public class ConsoleFactoryView implements AbstractFactoryView {

    private Session session;

    public ConsoleFactoryView(Session session) {
        this.session = session;
    }

    @Override
    public StartView createStartView() {
        return new ConsoleStartView(session);
    }

    @Override
    public GameView createGameView() {
        return new ConsoleGameView(session);
    }

    @Override
    public ProposalView createProposalView() {
        return new ConsoleProposalView(session);
    }

    @Override
    public ResumeView createResumeView() {
        return new ConsoleResumeView(session);
    }
}
