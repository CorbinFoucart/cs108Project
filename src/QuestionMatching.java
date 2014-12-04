package project;

import java.io.Serializable;
import java.util.*;

public class QuestionMatching implements Question, Serializable {
	private String question;
	private double weight;
	private ArrayList<Matching> matchings;
	private String quiz_id;
	private String id;
	private ArrayList<String> matchOptions;
	
	public QuestionMatching(String question) {
		this.question = question;
		matchOptions = new ArrayList<String>();
		matchings = new ArrayList<Matching>();
		generateID();
	}
	
	public void addMatching(String leftOption, String rightOption) {
		Matching match = new Matching(leftOption, rightOption);
		matchOptions.add(rightOption);
		matchings.add(match);
	}
	
	
	public ArrayList<String> getLeftOptions() {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < matchings.size(); i++) {
			options.add(matchings.get(i).getLeftOption());
		}
		return options;
	}
	
	
	public ArrayList<String> getRightOptions() {
		Collections.shuffle(matchOptions);
		return matchOptions;
	}
	
	public void addRightOption(String option2) {
		matchOptions.add(option2);
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getClassName() {
		return "QuestionMatching";
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
	
	// FROM 0
	public void setUserMatch(int leftOption, int rightOption) {
		Matching match = matchings.get(leftOption);
		String userMatch = matchOptions.get(rightOption);
		match.setUserMatch(userMatch);
	}
	
	public void clearUserMatches() {
		for (int i = 0; i < matchings.size(); i++) {
			matchings.get(i).setUserMatch(null);
		}
	}
	
	public ArrayList<String> getUserAnswers() {
		ArrayList<String> userAnswers = new ArrayList<String>();
		for (int i = 0; i < matchings.size(); i++) {
			userAnswers.add(matchings.get(i).getUserMatch());
		}
		return userAnswers;
	}
	
	public ArrayList<String> getCorrectAnswers() {
		ArrayList<String> correctAnswers = new ArrayList<String>();
		for (int i = 0; i < matchings.size(); i++) {
			correctAnswers.add(matchings.get(i).getRightOption());
		}
		return correctAnswers;
	}
	
	
	public double getPercentage() {
		double total = 0;
		for (int i = 0; i < matchings.size(); i++) {
			Matching match = matchings.get(i);
			if (match.getRightOption().equals(match.getUserMatch())) total++;
		}
		return total/(matchings.size());
	}
	
	
// ------------------------------------------------------------	
	
	private class Matching implements Serializable {
		private String leftOption;
		private String rightOption;
		private String userMatch;
		
		public Matching(String leftOption, String rightOption) {
			this.rightOption = rightOption;
			this.leftOption = leftOption;
		}
		
		public String getLeftOption() {
			return leftOption;
		}
		
		public String getRightOption() {
			return rightOption;
		}
		
		public void setUserMatch(String userMatch) {
			this.userMatch = userMatch;
		}
		
		public String getUserMatch() {
			return userMatch;
		}

	}
	
}
