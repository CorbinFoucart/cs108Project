package project;

import java.util.*;

public class QuestionResponse implements Question{
	
	public String question;
	public ArrayList<Answer> acceptedAnswers;
	public Answer userAnswer;
	
	public QuestionResponse(String question) {
		this.question = question;
		acceptedAnswers = new ArrayList<Answer>();
	}
	
	public void addAcceptedAnswer(String str) {
		Answer ans = new Answer(str);
		acceptedAnswers.add(ans);
	}
	
	public void setUserAnswer(String userAns) {
		userAnswer = new Answer(userAns);
	}

	
	public boolean isCorrect() {
		
		// need more error stuff later
		if (userAnswer == null) System.out.println("Need an answer");
		
		for (int i = 0; i < acceptedAnswers.size(); i++) {
			if (acceptedAnswers.get(i).equals(userAnswer)) return true;
		}
		return false;
	}

	public Object getQuestion() {
		return question;
	}
	
	
	// db
	
	public void printPossible() {
		for (int i = 0; i < acceptedAnswers.size(); i++) {
			System.out.println(acceptedAnswers.get(i));
		}
	}
	
	public void clearAcceptedAnswers() {
		acceptedAnswers = new ArrayList<Answer>();
	}
	
	
	// ----------------------- Answer Inner Class ----------------- //
	
	private class Answer {
		
		private String answer;
		
		public Answer(String ans) {
			answer = ans;
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
