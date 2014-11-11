package ch.vorburger.saraswathi.web.sms.twilio;

import ch.vorburger.saraswathi.web.sms.SMS;

import com.twilio.sdk.verbs.Message;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

public class TwilioUtil {

	private TwilioUtil() {
	}
	
	public static String toTwiML(SMS sms) {
		TwiMLResponse twiml = new TwiMLResponse();
		Message say = new Message(sms.getText());
		try {
			twiml.append(say);
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + twiml.toXML();
		} catch (TwiMLException e) {
			throw new IllegalArgumentException("Could not transform SMS to TwiML: " + sms.toString(), e);
		}
	}
	
	public static SMS toSMS(String from, String to, String text) {
		return new SMS(from, to, text);
	}
}
