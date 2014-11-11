package ch.vorburger.saraswathi.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ch.vorburger.saraswathi.AsyncInteractlet;
import ch.vorburger.saraswathi.Interactlet;
import ch.vorburger.saraswathi.examples.EchoInteractlet;

public class EchoInteractletTest {

	@Test
	public void testEchoInteractletSync() {
		Interactlet echoLet = new EchoInteractlet();
		assertThat(echoLet.interact("ho"), is("yo ho"));
		assertThat(echoLet.interact("bo"), is("yo bo"));
	}
	
	@Test
	public void testEchoInteractletAsyncIO() {
		// TODO extract lines below into a test util fixture
		TestIO testIO = new TestIO();
		AsyncInteractlet echoLet = new EchoInteractlet();
		echoLet.interact(testIO);
		
		assertThat(testIO.lastPrompt(), is(new EchoInteractlet().initialPrompt()));
		testIO.sendAnswer("ho");
		assertThat(testIO.lastPrompt(), is("yo ho"));
		testIO.sendAnswer("bo");
		assertThat(testIO.lastPrompt(), is("yo bo"));
	}

}
