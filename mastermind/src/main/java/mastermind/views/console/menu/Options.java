package mastermind.views.console.menu;

public enum Options {
    PROPOSE("Propose Combination"),
    REDO("Redo previous Proposal"),
    UNDO("Undo previous Proposal");

    private String message;

    private Options(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
