package ch.vorburger.saraswathi.math;

import ch.vorburger.saraswathi.CLIIO;
import ch.vorburger.saraswathi.MainLoop;


public class MathMain {

	public static void main(String[] args) throws Exception {
		new MainLoop(new MathAdditionsLearnlet(10), new CLIIO()).run();
	}
	
}
