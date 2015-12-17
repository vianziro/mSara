package ch.vorburger.gaexmpp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

@SuppressWarnings("serial")
public class XMPPReceiverServlet extends HttpServlet {
	
	private static final Logger log = null; // TODO how-to use JUL? Use slf4j bridge?
	private static final XMPPService xmpp = XMPPServiceFactory.getXMPPService();

	// Just echo, for now. TODO replace with HTTPForwardingXMPPIncomingMessageHandler
	private XMPPIncomingMessageHandler handler = new XMPPIncomingMessageHandler() {
		@Override
		public String handleIncoming(XMPPMessage message) {
			return "ECHO " + message.body;
		}
	}; 
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Message message = xmpp.parseMessage(req);

		JID fromJid = message.getFromJid();
		
		XMPPMessage simpleMsg = new XMPPMessage();
		simpleMsg.body = message.getBody();
		simpleMsg.fromJID = fromJid.getId();
		simpleMsg.toJID = message.getRecipientJids()[0].getId(); 
		
		log.fine("Incoming XMPP from " + simpleMsg.fromJID + ":" + simpleMsg.body); // TODO Use slf4j {} syntax instead concat
		String reply = handler.handleIncoming(simpleMsg);
		if (reply == null || reply.trim().isEmpty())
			return;

		log.fine("Reply about to be sent back: " + reply); // TODO Use slf4j {} syntax instead concat
		try {
			XMPPSender.sendXMPP(fromJid, reply);
		} catch (XMPPException e) {
			final String msg = "Failed to echo incoming message back to " + fromJid.toString();
			log.log(Level.SEVERE, msg, e);
			e.printStackTrace(); // TODO REMOVE
			throw new IOException(msg, e);
		}
		
		// TODO do something more interesting, using https://cloud.google.com/appengine/docs/java/urlfetch/ & https://code.google.com/p/google-http-java-client/wiki/Samples
	}
	
}