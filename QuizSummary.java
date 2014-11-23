package project;

import java.util.*;

public class QuizSummary {
	private String description;
	private String creator;
	private ArrayList<Performance> usersPastPerformance;
	private ArrayList<Performance> highestUniquePerformances;
	private ArrayList<Performance> todaysHighestUniquePerformances;
	private int numTimesTaken;
	private double avg;
	
	private static final int NUM_RECENT = 5;

	public QuizSummary(String quiz_id, String username) {
		DatabasePipeline pipeline = new DatabasePipeline();
		description = pipeline.getQuizDescription(quiz_id);
		creator = pipeline.getCreator(quiz_id);
		usersPastPerformance = pipeline.getQuizPerformances(username, quiz_id);
		highestUniquePerformances = pipeline.getHighestUniquePerformances(quiz_id, NUM_RECENT);
		todaysHighestUniquePerformances = pipeline.getHighestUniquePerformances(quiz_id, NUM_RECENT);
		numTimesTaken = pipeline.getNumTimesQuizTaken(quiz_id);
		avg = pipeline.getQuizAverage(quiz_id);
	}
	
	public String getQuizDescription() {
		return description;
	}
	
	public String getQuizCreator() {
		return creator;
	}
	
	public ArrayList<Performance> getUsersPastQuizPerformance() {
		return usersPastPerformance;
	}
	
	public ArrayList<Performance> getHighestUniqueQuizPerformances() {
		return highestUniquePerformances;
	}
	
	public ArrayList<Performance> getTodaysHighestUniqueQuizPerformances() {
		return todaysHighestUniquePerformances;
	}
	
	public int getNumTimesQuizTaken() {
		return numTimesTaken;
	}
	
	public double getQuizAverage() {
		return avg;
	}
	
}
