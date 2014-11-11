package ch.vorburger.saraswathi.math;

public class LessonPlan {

	// TODO intro. per exercise and total global counts

	// TODO auto repeat weak lessons & maybe flexible navigation commands, skip lessons, choice to redo lessons! For now, just one hard-coded path.
	
	// TODO store progress speed, how many right and wrong ones
	
	MathAdditionsLearnlet currentLearnlet;
	
	LessonPlan() {
		currentLearnlet = new MathAdditionsLearnlet(10);
	}

	public String nextQuestion() {
		return currentLearnlet.nextQuestion();
	}

	public boolean isCorrectAnswer(Integer answerNumber) {
		return currentLearnlet.isCorrectAnswer(answerNumber);
	}

	public boolean hasMore() {
		return currentLearnlet.hasMore();
	}
	
	// TODO LastLessonLearnlet;
	
}
