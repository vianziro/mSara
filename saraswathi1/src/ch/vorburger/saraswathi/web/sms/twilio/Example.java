package ch.vorburger.saraswathi.web.sms.twilio;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class Example {

	// TODO remove this before commiting into Git!! How to safely get it into Heroku??
	private static final String ACCOUNT_SID = "";
	private static final String AUTH_TOKEN = "";

	public static void main(String[] args) throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("From", "+16467913552"));
		params.add(new BasicNameValuePair("To",   "+919731951575"));
		params.add(new BasicNameValuePair("Body", "Mobile Saraswathi - Om sai baba"));

		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		Message message = messageFactory.create(params);
		System.out.println(message.getSid());
	}
}