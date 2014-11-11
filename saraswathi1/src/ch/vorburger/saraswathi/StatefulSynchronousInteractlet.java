package ch.vorburger.saraswathi;

import ch.vorburger.saraswathi.IO.ResponseHandler;

public abstract class StatefulSynchronousInteractlet implements Interactlet, AsyncInteractlet {
	// TODO rename this, when the dust settles (if this isn't removed by then)

	abstract protected String initialPrompt();

	/**
	 * @param message incoming text request.  If null, then initialPrompt() should be returned. 
	 * Returning null means that this (instance of) Interactlet is done and returns control to it's "parent".
	 */
	// TODO intro. more advanced canHandle(..) type chains support, if needed?
	@Override
	public abstract String interact(String message);

	@Override
	public void interact(IO io) {
		io.prompt(initialPrompt(), new ResponseHandler() {
			@Override
			public void handleResponse(String response) {
				final String nextPrompt = interact(response);
				io.prompt(nextPrompt, this);
			}
		});
	}

}
