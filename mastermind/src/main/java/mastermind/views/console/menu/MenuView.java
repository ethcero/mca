package mastermind.views.console.menu;

import mastermind.controllers.PlayController;
import santaTecla.utils.WithConsoleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
public class MenuView extends WithConsoleModel {

    private PlayController playController;

    private List<MenuOption> options = new ArrayList<>();

    public MenuView(PlayController playController) {
        this.playController = playController;
        options.add(new MenuOption(Options.PROPOSE, new ProposeAction(playController)));
        if(playController.isUndoable()) {
            options.add(new MenuOption(Options.UNDO, new UndoAction(playController)));
        }
        if(playController.isRedoable()) {
            options.add(new MenuOption(Options.REDO, new RedoAction(playController)));
        }

    }

    public void execute() {
        MenuOption command;
        do {
            printMenu();
            command = readOption();
        }while(command == null);

        command.execute();
    }

    private MenuOption readOption(){
        int option = console.readInt("");
        try {
            return options.get(option-1);
        }catch (IndexOutOfBoundsException ex){
            return null;
        }
    }

    private void printMenu(){
        console.writeln("----- Choose one option ----");
        for(int i=1; i <= options.size();i++) {
            console.writeln(String.format("%d) %s", i, options.get(i-1).toString()));
        }
    }
}
