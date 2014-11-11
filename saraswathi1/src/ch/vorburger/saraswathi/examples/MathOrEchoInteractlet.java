package ch.vorburger.saraswathi.examples;

import ch.vorburger.saraswathi.Interactlet;
import ch.vorburger.saraswathi.StatefulSynchronousInteractlet;
import ch.vorburger.saraswathi.math.MathAdditionsLearnlet;

public class MathOrEchoInteractlet extends StatefulSynchronousInteractlet {

	// TODO enable "quitting" menus to go "back up"
	
	// TODO move to parent class
	private Interactlet currentInteractlet;
	
	@Override
	public String interact(String message) {
		// TODO move to parent class
		if (currentInteractlet != null) {
			String response = currentInteractlet.interact(message);
			if (response != null)
				return response;
			else {
				currentInteractlet = null;
				return initialPrompt();
			}
		}

		if ("ECHO".equalsIgnoreCase(message)) {
			currentInteractlet = new EchoInteractlet();
			return currentInteractlet.interact(null);
		} else if ("MATH".equalsIgnoreCase(message)) {
			currentInteractlet = new MathAdditionsLearnlet(10); // TODO use LessonPlan
			return currentInteractlet.interact(null);
		} else {
			return initialPrompt();
		}
	}

	@Override
	protected String initialPrompt() {
		// TODO build-in proper OOB multi-choice support helper util
		return "write 1) 'ECHO' or 2) 'MATH'";
	}

}
