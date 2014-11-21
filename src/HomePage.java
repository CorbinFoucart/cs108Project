package project;

import java.util.*;

public class HomePage {
	private ArrayList<Message> newAnnouncements;
	private ArrayList<String> popularQuizzes;
	private ArrayList<String> recentlyCreatedQuizzes;
	private ArrayList<Performance> ownRecentPerformances;
	private ArrayList<String> ownRecentlyCreatedQuizzes;
	private ArrayList<Achievement> newAchievements;
	private ArrayList<Achievement> allAchievements;
	private ArrayList<Message> recentFriendRequests;
	private ArrayList<Message> recentChallenges;
	private ArrayList<Message> recentNotes;
	//private ArrayList<Activity> getRecentFriendActivity;
	
	private static final int NUM_RECENT = 5;
	
	public HomePage(String username) {
		DatabasePipeline pipeline = new DatabasePipeline();
		newAnnouncements = pipeline.getNewAnnouncements(username);
		// something about popular quizzes
		//recentlyCreatedQuizzes = 
		ownRecentPerformances = pipeline.getNRecentPerformances(username, NUM_RECENT);
		// ownRecenlyCreatedQuizzes
		// new achievements
		// all achievements
		recentFriendRequests = pipeline.getNRecentMessages(username, "friend_request", NUM_RECENT);
		recentChallenges = pipeline.getNRecentMessages(username, "challenge", NUM_RECENT);
		recentNotes = pipeline.getNRecentMessages(username, "note", NUM_RECENT);
	}
}
