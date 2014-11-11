package ch.vorburger.saraswathi.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.vorburger.saraswathi.Interactlet;
import ch.vorburger.saraswathi.web.sms.SMS;
import ch.vorburger.saraswathi.web.sms.sync.UshahidiSMSSyncResponse;
import ch.vorburger.saraswathi.web.sms.twilio.TwilioUtil;

@RestController
public class IncomingRestController {
	private static final Logger log = LoggerFactory.getLogger(IncomingRestController.class);

	// private static final String rfc2822Datepattern = "EEE, dd MMM yyyy HH:mm:ss Z";
	// SimpleDateFormat is not thread safe, so we need to have a new instance for each request, so below has to be in each method, NOT here: 
	// SimpleDateFormat dateFormat = new SimpleDateFormat(rfc2822Datepattern, Locale.US);
	
	public static final String UshahidiPath = "/smsInFromUSync";
	public static final String TwilioPath = "/sms/twilio";
	
	@Autowired Interactlet interactlet; 
	
	@ResponseBody
	@RequestMapping(value=TwilioPath, method=RequestMethod.POST, produces="application/xml;charset=UTF-8")
	String smsInFromTwilio(
			// https://www.twilio.com/docs/api/twiml/sms/twilio_request
			@RequestParam(value="From",           required=true)  String from,
			@RequestParam(value="To",             required=true)  String to,
			@RequestParam(value="Body",           required=true)  String body
//			@RequestParam(value="AccountSid",     required=true)  String twilioAccountID,
//			@RequestParam(value="MessageSid",     required=true)  String twilioMessageSID,
			// optional
//			@RequestParam(value="FromCountry",    required=false) String twilioFromCountry,
//			@RequestParam(value="FromCity",       required=false) String twilioFromCity,
//			@RequestParam(value="FromState",      required=false) String twilioFromState,
//			@RequestParam(value="FromZip",        required=false) String twilioFromZIP
			// ToCountry/State/City/ZIP are not interesting
	) {		
		log.info("{}: from={}, message={}", TwilioPath, from, body);
		SMS incomingSMS = TwilioUtil.toSMS(from, to, body);
		String response = interactlet.interact(incomingSMS.getText());
		return TwilioUtil.toTwiML(new SMS(to, from, response));
	}
	
	// TODO I could never get Ushahidi SMS Sync to work completely.. :-(
	@RequestMapping(value=UshahidiPath, method=RequestMethod.POST)
	UshahidiSMSSyncResponse smsInFromUSync(
			@RequestParam(value="from",           required=true)  String from,
			@RequestParam(value="message",        required=true)  String message,
			@RequestParam(value="message_id",     required=true)  String messageID,
			@RequestParam(value="sent_to",        required=true)  String to,
			@RequestParam(value="secret",         required=false) String secret,
			@RequestParam(value="device_id",      required=false) String deviceID,
			@RequestParam(value="sent_timestamp", required=true)  String sentTimestamp
	) {
		log.info("{}: from={}, messageID={}, sentTo={}, sentTimestamp={}, message={}", UshahidiPath, from, messageID, to, sentTimestamp, message);
		// TODO check secret against some configuration, return if not matched. This end-point should be disabled if no secret is set. And log it all! ;)
		// TODO convert sentTimestamp to Date? but which format does Ushahidi SMS Sync use - RFC 2822 ?
		// TODO needed? SMS incomingSMS = new SMS(from, message);
		String response = interactlet.interact(message /*incomingSMS.getText()*/);
		if (response != null) {
			SMS outgoingSMS = new SMS(to, from, response);
			return new UshahidiSMSSyncResponse(outgoingSMS /*, secret*/);
		} else {
			return new UshahidiSMSSyncResponse();
		}
	}

}