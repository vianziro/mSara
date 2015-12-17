package ch.vorburger.gaexmpp;

/**
 * XMPP "simple" Message.
 * 
 * This class should not depend on any XMPP implementation framework specific classes.
 */
public class XMPPMessage {

	public String fromJID;
	public String toJID;
	public String body;

}
