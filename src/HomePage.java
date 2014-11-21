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
	private ArrayList<Activity> getRecentFriendActivity;
	
	public HomePage(String username) {
		DatabasePipeline pipeline = new DatabasePipeline();
		newAnnouncements = pipeline.getNewAnnouncements(username);
		// something about popular quizzes
		//recentlyCreatedQuizzes = 
		ownRecentPerformances = pipeline.getRecentPerformances(username);
		// ownRecenlyCreatedQuizzes
		// new achievements
		// all achievements
		recentFriendRequests = pipeline.getRecentMessages(username, "friend_request");
		recentChallenges = pipeline.getRecentMessages(username, "challenge");
		recentNotes = pipeline.getRecentMessages(username, "note");
	}
}
