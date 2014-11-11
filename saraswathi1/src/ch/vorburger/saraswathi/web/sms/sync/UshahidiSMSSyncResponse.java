package ch.vorburger.saraswathi.web.sms.sync;

import java.util.Arrays;
import java.util.List;

import ch.vorburger.saraswathi.web.sms.SMS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UshahidiSMSSyncResponse {
	// TODO Xtend active annotation to gen. classes like this?
	
	private final Payload payload;

	public UshahidiSMSSyncResponse() {
		super();
		this.payload = new Payload();
	}
	
	public UshahidiSMSSyncResponse(SMS outgoingSMS /*, String secret*/) {
		super();
		this.payload = new Payload(/*secret, */new Payload.Message(outgoingSMS.getTo(), outgoingSMS.getText()));
	}

	public UshahidiSMSSyncResponse(String errorMessage) {
		super();
		this.payload = new Payload(errorMessage);
	}

	public Payload getPayload() {
		return this.payload;
	}
	
	@JsonInclude(Include.NON_NULL)
	public static class Payload {
		private final boolean success;
		private final String error;
		private final String task = "send";
		private final String secret;
		private final List<Message> messages;
			
		public Payload() {
			super();
			this.success = true;
			this.error = null;
			this.secret = null;
			this.messages = null;
		}
		
		public Payload(/*String secret, */Message... messages) {
			super();
			this.success = true;
			this.error = null;
			this.secret = null; // Do NOT send secret in response WITH messages (apparently)
			this.messages = Arrays.asList(messages);
			// TODO freeze "messages" so add(), remove() etc. is not possible?
		}

		public Payload(String errorMessage) {
			super();
			this.success = false;
			this.error = errorMessage;
			this.secret = null;
			this.messages = null;
		}
		
		public String getTask() {
			return task;
		}

		public boolean isSuccess() {
			return success;
		}		
		
		public String getError() {
			return error;
		}
		
		public String getSecret() {
			return secret;
		}

		public List<Message> getMessages() {
			return messages;
		}
		
		public static class Message {
			private final String to;
			private final String message;
			
			public Message(String to, String message) {
				super();
				this.to = to;
				this.message = message;
			}
			
			public String getTo() {
				return to;
			}
			
			public String getMessage() {
				return message;
			}
		}

	}

	// TODO toString(), hashCode(), equals() for all of above..
	
}
