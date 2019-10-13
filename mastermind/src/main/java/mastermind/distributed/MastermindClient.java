package mastermind.distributed;

import mastermind.Mastermind;
import mastermind.controllers.Logic;
import mastermind.views.View;
import mastermind.views.console.ConsoleView;

public class MastermindClient extends Mastermind {

    public MastermindClient(View view) {
        super(view);
    }

    @Override
    public Logic getLogic() {
        return new LogicProxy();
    }

    public static void main(String[] args) {
        new MastermindClient(new ConsoleView()).play();
    }
}
