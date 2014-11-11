package ch.vorburger.saraswathi.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.vorburger.saraswathi.web.sms.SMS;
import ch.vorburger.saraswathi.web.sms.twilio.TwilioUtil;

public class TwilioUtilTest {

	@Test
	public void testToTwiML() {
		SMS sms = new SMS("+16467913552", "+919731951575", "hello, world");
		String xml = TwilioUtil.toTwiML(sms);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Message>hello, world</Message></Response>", xml);
	}

}
