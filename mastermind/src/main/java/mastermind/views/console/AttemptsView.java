package mastermind.views.console;

import mastermind.models.Session;

class AttemptsView extends ConsoleViewModel{

    public AttemptsView(Session session) {
        super(session);
    }

    void writeln() {
        console.writeln(String.format(Message.ATTEMPTS.getMessage(), session.getAttempts()));
    }

}