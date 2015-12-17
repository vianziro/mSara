package ch.vorburger.gaexmpp.http;

import java.net.URI;

/**
 * Outgoing HTTP Request.
 * 
 * Its "queueable", meaning that it may not run immediately synchronously, or in
 * case of a failure may be persistently stored to be retried say 30' later.
 */
public class OutgoingHTTPRequestMessage {
	
	enum Verb {
		GET, POST
	}
	
	URI uri;
	Verb verb;
	String body;

}
