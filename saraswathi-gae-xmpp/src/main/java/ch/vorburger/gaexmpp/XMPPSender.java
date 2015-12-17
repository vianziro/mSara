package ch.vorburger.gaexmpp;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.SendResponse.Status;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public class XMPPSender {

	private static final XMPPService xmpp = XMPPServiceFactory.getXMPPService();

	public static void sendXMPP(String toJID, String body) throws XMPPException {
		JID jid = new JID(toJID);
		sendXMPP(jid, body);
	}

	public static void sendXMPP(JID toJID, String body) throws XMPPException {
		Message msg = new MessageBuilder().withRecipientJids(toJID).withBody(body).build();

		SendResponse statusResponse = xmpp.sendMessage(msg);
		Status status = statusResponse.getStatusMap().get(toJID);
		boolean messageSent = (status == SendResponse.Status.SUCCESS);
		
		if (!messageSent) {
			throw new XMPPException("Failed to send XMPP to " + toJID.toString() + "; status=" + status.toString());
		}
	}
}
