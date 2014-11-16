package project;

import java.util.*;

public class QuestionResponse implements Question{
	
	private String question;
	private ArrayList<Answer> acceptedAnswers;
	private Answer userAnswer;
	private double weight;
	
	public QuestionResponse(String question) {
		this.question = question;
		this.acceptedAnswers = new ArrayList<Answer>();
		this.weight = 1;
		this.userAnswer = new Answer("");
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

	public String getQuestion() {
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
	
	public double getPercentage() {
		if (isCorrect()) return 1;
		return 0;
	}
	
	public String getClassName() {
		return "QuestionResponse";
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
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
