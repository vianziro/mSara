package ch.vorburger.saraswathi.examples;

import ch.vorburger.saraswathi.CLIIO;
import ch.vorburger.saraswathi.MainLoop;

public class EchoMain {
	public static void main(String[] args) {
		new MainLoop(new EchoInteractlet(), new CLIIO()).run();
	}
}
