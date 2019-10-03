package mastermind;

import santaTecla.utils.Console;

enum Message {
	ATTEMPTS("#attempts attempt(s): "), 
	SECRET("*"), 
	RESUME("Do you want to continue"), 
	RESULT(" --> #blacks blacks and #whites whites"), 
	PROPOSED_COMBINATION("Propose a combination: "), 
	TITLE("----- MASTERMIND -----"), 
	WINNER("You've won!!! ;-)"), 
	LOOSER("You've lost!!! :-("),
	NEW_LINE("\n");

	private String message;
	
	static private Console console; 
	static {
		Message.console = new Console();
	}

	private Message(String message) {
		this.message = message;
	}

	void write() {
		Message.console.write(this.message);
	}

	void writeln() {
		Message.console.writeln(this.message);
	}

	void writeln(int attempts) {
		Message.console.writeln(this.message.replaceAll("#attempts", "" + attempts));
	}

	void writeln(int blacks, int whites) {
		Message.console.writeln(this.message.replaceFirst("#blacks", "" + blacks).replaceFirst("#whites", "" + whites));
	}

}
