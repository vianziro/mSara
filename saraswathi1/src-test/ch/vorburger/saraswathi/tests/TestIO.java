package ch.vorburger.saraswathi.tests;

import ch.vorburger.saraswathi.IO;

public class TestIO implements IO {

	private String lastPrompt;
	// private String lastResponse;
	private ResponseHandler responseHandler;

	@Override
	public void prompt(String prompt, ResponseHandler responseHandler) {
		this.lastPrompt = prompt;
		this.responseHandler = responseHandler;
	}

	public String lastPrompt() {
		if (lastPrompt == null)
			throw new IllegalStateException("prompt() either hasn't been called yet, or previous getLastPrompt() invokation already consumed it");
		String returnPrompt = lastPrompt;
		this.lastPrompt = null;
		return returnPrompt;
	}

	public void sendAnswer(String message) {
		responseHandler.handleResponse(message);		
	}

}
