/**
 * Copyright (C) 2019 Deveryware S.A. All Rights Reserved.
 */
package mastermind;

import mastermind.controllers.Logic;
import mastermind.controllers.implementantion.LogicImplementation;
import mastermind.views.View;
import mastermind.views.console.ConsoleView;

/**
 * @author fran
 */
public class MastermindConsole extends Mastermind {

    public MastermindConsole(View view) {
        super(view);
    }

    @Override
    public Logic getLogic() {
        return new LogicImplementation();
    }

    public static void main(String[] args) {
        new MastermindConsole(new ConsoleView()).play();
    }
}
