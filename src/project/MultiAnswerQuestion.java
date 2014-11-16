package project;

import java.util.*;

public class MultiAnswerQuestion implements Question{
	private double weight;
	private String question; 
	private boolean ordered;
	private ArrayList<String> userAnswers;
	private ArrayList<Answer> acceptedAnswers;
	public int numCorrect;
	public int numPossibleCorrect;
	
	/**
	 * Constructor for multi-answer question.
	 * 
	 * @param ordered - user determines if order matters in
	 * answering the questions.
	 */
	public MultiAnswerQuestion(String question, boolean ordered, int numPossibleCorrect) {
		this.ordered = ordered;
		this.question = question;
		this.userAnswers = new ArrayList<String>();
		this.acceptedAnswers = new ArrayList<Answer>();
		this.numCorrect = 0;
		this.numPossibleCorrect = numPossibleCorrect;
		this.weight = 1;
	}
	
	public void addAcceptedAnswer(String ans) {
		Answer a  = new Answer(ans);
		acceptedAnswers.add(a);
	}
	

	public void setUserAnswer(String userAns) {
		userAnswers.add(userAns);
	}
	
	public void clearUserAnswers() {
		userAnswers = new ArrayList<String>();
		for (int i = 0; i < acceptedAnswers.size(); i++) {
			acceptedAnswers.get(i).correct = false;
		}
		numCorrect = 0;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	
	public void tallyAnswers() {
		numCorrect = 0;
		int nUserAnswers = userAnswers.size();
		int nAcceptedAnswers = acceptedAnswers.size();
		for (int i = 0; i < nUserAnswers; i++) {
			String currUserAns = userAnswers.get(i);
			if (ordered) {
				String correctAns = acceptedAnswers.get(i).answer;
				if (currUserAns.equals(correctAns)) {
					numCorrect++;
					acceptedAnswers.get(i).correct = true;
				}
			} else {
				for (int j = 0; j < nAcceptedAnswers; j++) {
					String correctAnswer = acceptedAnswers.get(j).answer;
					boolean alreadyAnswered = acceptedAnswers.get(j).correct;
					if (currUserAns.equals(correctAnswer) && !alreadyAnswered) {
								numCorrect++;
								acceptedAnswers.get(j).correct = true;
							}
				}
			}
		}
		for (int i = 0; i < acceptedAnswers.size(); i++) {
			acceptedAnswers.get(i).correct = false;
		}
	
	}
	
	public double getPercentage() {
		tallyAnswers();
		double correctAnswers = numCorrect;
		return correctAnswers / numPossibleCorrect;		
	}
	
	@Override
	public String getQuestion() {
		return question;
	}
	
	public String getClassName() {
		return "MultiAnswerQuestion";
	}
	
	
	// ---- answer inner array ----- //
	
	private class Answer {
		
		private boolean correct;
		private String answer;
		
		
		public Answer(String ans) {
			answer = ans;
			correct = false;
		}
		
		public boolean equals(Answer other) {
			// LATER need 1 step away comparator etc
			
			// for now, just use string matching
			return (other.answer.equals(answer));
		}
		
		public String toString() {
			return answer;
		}
	}





	
}
