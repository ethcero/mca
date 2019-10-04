package mastermind.views.console;

enum Message {
	ATTEMPTS("%d attempt(s): "),
	SECRET("*"), 
	RESUME("Do you want to continue"), 
	RESULT(" --> %s blacks and %s whites"),
	PROPOSED_COMBINATION("Propose a combination: "), 
	TITLE("----- MASTERMIND -----"), 
	WINNER("You've won!!! ;-)"), 
	LOOSER("You've lost!!! :-("),
	NEW_LINE("\n");

	private String message;

	private Message(String message) {
		this.message = message;
	}

	public String getMessage(){
	    return this.message;
    }

}
