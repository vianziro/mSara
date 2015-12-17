package ch.vorburger.gaexmpp;

public interface XMPPIncomingMessageHandler {

	/**
	 * Handle incoming XMPP message.
	 * 
	 * @param message incoming XMPP message
	 * @return response text message, if any (may be null if no response to be sent in reply) 
	 */
	String handleIncoming(XMPPMessage message);
	
}
