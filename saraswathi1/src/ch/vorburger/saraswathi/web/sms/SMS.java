package ch.vorburger.saraswathi.web.sms;


public class SMS {
	// TODO Xtend active annotation to gen. Builder for this?
	
	// TODO intro. phone number datatype?  read http://en.wikipedia.org/wiki/E.164
	private final String from;
	private final String to;
	private final String text;
	
	// TODO use Joda time instead?
//	private final Date createdOn;
	
	// TODO use Builder instead?
	public SMS(String from, String to, String text) {
		this.from = from;
		this.to = to;
		this.text = text;
	}
	
	public String getFrom() {
		return from;
	}
	
	public String getTo() {
		return to;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "SMS [from=" + from + ", to=" + to + ", text=" + text + "]";
	}
}
