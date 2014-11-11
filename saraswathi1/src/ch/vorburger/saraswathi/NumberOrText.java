package ch.vorburger.saraswathi;

import java.util.NoSuchElementException;

public class NumberOrText {

	private final int number;
	private final String text;

	public static NumberOrText create(final String string) {
		try {
			int numberResponse = Integer.parseInt(string);
			return new NumberOrText(numberResponse);
		} catch (NumberFormatException e) {
			return new NumberOrText(string);
		}
	}
	
	protected NumberOrText(int number) {
		super();
		this.number = number;
		this.text = null;
	}

	protected NumberOrText(String text) {
		super();
		if (text == null)
			throw new IllegalArgumentException("text == null");
		if (text.trim().isEmpty())
			throw new IllegalArgumentException("text.trim().isEmpty()");
		this.text = text.trim();
		this.number = 123; // doesn't matter, see below.
	}

	public boolean isNumber() {
		return text == null;
	}

	public int getNumber() {
		if (isNumber())
			return number;
		else
            throw new NoSuchElementException("No number value present - it's a text: " + text);
	}

	public boolean isText() {
		return !isNumber();
	}
	
	public String getText() {
		if (isText())
			return text;
		else
            throw new NoSuchElementException("No text value present - it's a number: " + number);
	}

	// TODO equals(), hashCode(), toString() à la Optional & OptionalInt
}
