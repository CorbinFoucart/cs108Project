package project;

import java.util.*;
import java.io.*;



public class QuestionFillinBlank implements Question, Serializable {

	private String question_part1;
	private String question_part2;
	private ArrayList<Answer> acceptedAnswers;
	private Answer userAnswer;
	private double weight;
	private String quiz_id;
	private String id;
	
	public QuestionFillinBlank(String ques_part1, String ques_part2) {
		this.question_part1 = ques_part1;
		this.question_part2 = ques_part2;
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

	public String getUserAnswer() {
		return userAnswer.toString();
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
		return (question_part1 + "_" + question_part2);
	}
	
	public String getQuestionPart1() {
		return question_part1;
	}
	
	public String getQuestionPart2() {
		return question_part2;
	}
	
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

	
	@Override 
	public String getClassName() {
		return "QuestionFillinBlank";
	}
	
	// Mostly implemented, need Front end feedback
	
}
