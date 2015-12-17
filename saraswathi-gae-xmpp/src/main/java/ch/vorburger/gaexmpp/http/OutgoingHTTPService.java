package ch.vorburger.gaexmpp.http;

public interface OutgoingHTTPService {
	
	OutgoingHTTPRequestResponseMessage request(OutgoingHTTPRequestMessage request);
	
}
