package ch.vorburger.gaexmpp.http;

public interface OutgoingHTTPRequestResponseMessage {

	String getMIMEType(); // TODO name?
	
	String getBodyAsText();
	
}
