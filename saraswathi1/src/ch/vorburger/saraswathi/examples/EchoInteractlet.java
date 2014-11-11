package ch.vorburger.saraswathi.examples;

import ch.vorburger.saraswathi.StatefulSynchronousInteractlet;


public class EchoInteractlet extends StatefulSynchronousInteractlet {
	
	@Override
	public String initialPrompt() {
		return "hi - Echo will send everything back to you. Write 'quit' to return.";
	}

	@Override
	public String interact(String message) {
		// TODO refactor into some final super method 
		if (message == null)
			return initialPrompt();
		if (message.trim().equalsIgnoreCase("QUIT")) {
			return null;
		}
		return "yo " + message;
	}
}
