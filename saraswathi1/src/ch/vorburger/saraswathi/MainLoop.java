package ch.vorburger.saraswathi;

public class MainLoop {

	// Services
	AsyncInteractlet interactlet;
	IO io;
	
	// NO persistent States here in this class!
	
	public MainLoop(AsyncInteractlet interactlet, IO io) {
		this.interactlet = interactlet;
		this.io = io;
	}

	public void run() {
		if (interactlet instanceof Lifecycle) {
			Lifecycle startable = (Lifecycle) interactlet;
			startable.start();
		}
		
		// No need to loop here
		interactlet.interact(io);
		
		if (interactlet instanceof Lifecycle) {
			Lifecycle stopable = (Lifecycle) interactlet;
			stopable.stop();
		}
	}
	
}
