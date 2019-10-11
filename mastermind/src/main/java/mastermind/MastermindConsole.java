/**
 * Copyright (C) 2019 Deveryware S.A. All Rights Reserved.
 */
package mastermind;

import mastermind.views.View;
import mastermind.views.console.ConsoleView;

/**
 * @author fran
 */
public class MastermindConsole extends Mastermind {

    public MastermindConsole(View view) {
        super(view);
    }

    public static void main(String[] args) {
        new MastermindConsole(new ConsoleView()).play();
    }
}
