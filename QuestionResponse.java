package project;

import java.util.*;
import java.io.*;

public class QuestionResponse implements Question, Serializable {
	
	private String question;
	private ArrayList<Answer> acceptedAnswers;
	private Answer userAnswer;
	private double weight;
	private String quiz_id;
	private String id;
	
	public QuestionResponse(String question) {
		this.question = question;
		this.acceptedAnswers = new ArrayList<Answer>();
		this.weight = 1;
		this.userAnswer = new Answer("");
		generateID();
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
	
		/*
		 * These two methods are so that the PictureQuestion
		 * can access the question that is initialized to ""
		 * and set it as an optional text question.
		 */
		protected String getSuperQuestion() {
			return question;
		}
		
		protected void setSuperQuestion(String childQuestion) {
			this.question = childQuestion;
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
	
	public void setQuizID(String id) {
		quiz_id = id;
	}
	
	public String getQuizID() {
		return quiz_id;
	}
	
	public void generateID() {
		IDGenerator generator = new IDGenerator();
		id = generator.generateID();
	}
	
	public String getID() {
		return id;
	}
	
	// ----------------------- Answer Inner Class ----------------- //
	
	private class Answer implements Serializable {
		
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
