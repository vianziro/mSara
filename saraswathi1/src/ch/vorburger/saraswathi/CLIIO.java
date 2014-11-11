package ch.vorburger.saraswathi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIIO implements IO {

	// TODO LOW write a non-blocking alternative implementation of this - just to make sure users don't rely on the blocking implementation detail
	
	private BufferedReader d = new BufferedReader(new InputStreamReader(System.in));

	private void println(String text) {
		System.out.println(text);
	}

	private String input() {
		try {
			return d.readLine();
		} catch (IOException e) {
			throw new IllegalStateException("readLine() failed", e);
		}
	}

	// TODO HIGH remove recursion here .. this impl. COULD cause a Stack Overflow after many interactions
	
	@Override
	public void prompt(String prompt, ResponseHandler responseHandler) {
		if (prompt != null)
			println(prompt);
		String response = input();
		responseHandler.handleResponse(response);
	}

}
