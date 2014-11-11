package ch.vorburger.saraswathi.math;

import java.util.Random;

import ch.vorburger.saraswathi.NumberOrText;
import ch.vorburger.saraswathi.StatefulSynchronousInteractlet;

public class MathAdditionsLearnlet extends StatefulSynchronousInteractlet {

	// TODO optionally support negative numbers
	
	// Configurations
	final int max;
	int correctAnswersNeededToPass = Integer.MAX_VALUE; // 3; // 15;
	
	// Services
	Random random = new Random();
	
	// State
	int lastA;
	int lastB;
	// TODO move into Learnlet parent class?  class ProgressStats?
	int questionsAsked;
	int right;
	int wrong;

	public MathAdditionsLearnlet(int max) {
		super();
		this.max = max;
	}

	@Override
	protected String initialPrompt() {
		return nextQuestion();
	}

	@Override
	public String interact(String message) {
		// TODO refactor into some final super method 
		if (message == null) {
			return initialPrompt();
		}

		NumberOrText response = NumberOrText.create(message);
		if (!response.isNumber()) {
			return currentQuestion() + " - NUMBER? ";
		}
		if (isCorrectAnswer(response.getNumber())) {
			return "Yes! " + nextQuestion();
		} else {
			return "No.. " + currentQuestion();			
		}
		// TODO if (!hasMore()) return null;
	}

	// TODO mv changeStateToNextQuestion()
	String nextQuestion() {
		++questionsAsked;
		lastA = random.nextInt(max);
		lastB = random.nextInt(max);
		return currentQuestion();
	}
	
	private String currentQuestion() {
		String q = lastA + " + " + lastB + " = ?";
		if (right > correctAnswersNeededToPass / 2)
			q = "(" + right + " OK, need " + correctAnswersNeededToPass + ") " + q;
		return q;
	}

	boolean isCorrectAnswer(int i) {
		boolean r = i == lastA + lastB;
		// TODO move into Learnlet parent class?
		if (r) {
			++right;
		}
		else {
			++wrong;
			if (right > 0)
				--right;
		}
		return r;
	}
	
	boolean hasMore() {
		return !(right == correctAnswersNeededToPass);
	}
	
//	String getDescription() {
//		return "Addition up to " + max;
//	}

}
