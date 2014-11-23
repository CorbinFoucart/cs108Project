package project;

import java.util.*;
import java.io.*;

public class QuestionMultipleChoice implements Question, Serializable {

	private String question;
	private ArrayList<Choice> choices;
	private double weight;
	private String quiz_id;
	private String id;
	
	
	public QuestionMultipleChoice(String question) {
		this.question = question;
		this.choices = new ArrayList<Choice>();
		this.weight = 1;
		generateID();
	}
	
	public boolean isCorrect() {
		for (int i = 0; i < choices.size(); i++) {
			Choice ch = choices.get(i);
			if (ch.correct != ch.userSelection) {
				return false;
			}
		}
		return true;
	}
	
	public double getPercentage() {
		if (isCorrect()) return 1;
		return 0;
	}

	@Override
	public String getQuestion() {
		return question;
	}
	
	public void addChoice(boolean correct, String option) {
		Choice ch = new Choice(correct, option);
		choices.add(ch);
	}
	
	public ArrayList<String> getOptions() {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < choices.size(); i++) {
			options.add(choices.get(i).option);
		}
		return options;
	}
	
	
	// MUST CHECK WITH FRONT END PEOPLE
	// GIVE US A CHOICE OR INT?
	public void choose(int i) {
		if (i < choices.size() && i >=0) {
			choices.get(i).userSelection = true;
		}
	}
	
	public void unChoose(int i) {
		if (i < choices.size() && i >=0) {
			choices.get(i).userSelection = false;
		}
	} 
	
	public void resetUserChoices() {
		for (int i = 0; i < choices.size(); i++) {
			unChoose(i);
		}
	}
	
	public String getClassName() {
		return "QuestionMultipleChoice";
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
	
	private class Choice implements Serializable {
		
		private boolean correct;
		private String option;
		private boolean userSelection;
		
		public Choice(boolean correct, String option) {
			this.correct = correct;
			this.option = option;
			this.userSelection = false;
		}
		
		// Possibly implement equals
	}
	
	

}
