package project;

import java.util.*;

public class QuestionMultipleChoice implements Question{

	private String question;
	private ArrayList<Choice> choices;
	
	
	public QuestionMultipleChoice(String question) {
		this.question = question;
		choices = new ArrayList<Choice>();
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
	
	public double getScore() {
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
	

	
	private class Choice {
		
		private boolean correct;
		private String option;
		private boolean userSelection;
		
		public Choice(boolean correct, String option) {
			this.correct = correct;
			this.option = option;
			userSelection = false;
		}
		
		// Possibly implement equals
	}
	
	

}
