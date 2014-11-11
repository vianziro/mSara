package ch.vorburger.saraswathi;

public interface IO {

	void prompt(String prompt, ResponseHandler responseHandler);
	interface ResponseHandler {
		void handleResponse(String response);
	}
	
	// Above is a more suitable API for what I have in mind than something like this:
	//	 String prompt(String prompt);
	// or even this:
	//   void println(String text);
	//   String input();
	
}
