package ch.vorburger.gaexmpp.http;

import java.net.URI;

import ch.vorburger.gaexmpp.XMPPIncomingMessageHandler;
import ch.vorburger.gaexmpp.XMPPMessage;
import ch.vorburger.gaexmpp.http.OutgoingHTTPRequestMessage.Verb;

public class HTTPForwardingXMPPIncomingMessageHandler implements XMPPIncomingMessageHandler {

	private OutgoingHTTPService httpService = new GAEOutgoingHTTPService();
	
	@Override
	public String handleIncoming(XMPPMessage message) {
		OutgoingHTTPRequestMessage httpRequest = new OutgoingHTTPRequestMessage();
		
		// TODO remove hard-coding, read from a configurable Repository instead
		httpRequest.uri = URI.create("http://localhost:5000/sms/twilio");
		httpRequest.verb = Verb.POST;
		httpRequest.body = null; // TODO transform XMPPMessage message to JSON, hard-coded via GSON for now, via TBD DiStructConversionService later
		
		OutgoingHTTPRequestResponseMessage response = httpService.request(httpRequest);
		// TODO check for HTTP error status, and throw Exception if any
		return response.getBodyAsText();
	}

}
