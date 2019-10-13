package mastermind.views.console.menu;

public class MenuOption {

    private Options option;
    private Action action;

    public MenuOption(Options option, Action action) {
        this.option = option;
        this.action = action;
    }

    public void execute() {
        action.execute();
    }

    @Override
    public String toString() {
        return option.getMessage();
    }
}
