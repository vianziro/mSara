package ch.vorburger.saraswathi;


public interface AsyncInteractlet {

	// TODO clearer to just return Pair<String, ResponseHandler> (or class AsyncInteractResponse { String prompt; ResponseHandler responseHandler }) 
	void interact(IO io);

}
