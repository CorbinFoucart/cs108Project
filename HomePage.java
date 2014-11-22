package project;

import java.util.*;

public class HomePage {
	private ArrayList<Message> newAnnouncements;
	private ArrayList<String> popularQuizzes;
	private ArrayList<String> recentlyCreatedQuizzes;
	private ArrayList<Performance> ownRecentPerformances;
	private ArrayList<String> ownRecentlyCreatedQuizzes;
	private ArrayList<Achievement> newAchievements;
	private ArrayList<Message> recentFriendRequests;
	private ArrayList<Message> recentChallenges;
	private ArrayList<Message> recentNotes;
	private ArrayList<Activity> recentFriendActivity;
	
	private static final int NUM_RECENT = 5;
	
	public HomePage(String username) {
		DatabasePipeline pipeline = new DatabasePipeline();
		newAnnouncements = pipeline.getNNewAnnouncements(username, NUM_RECENT);
		popularQuizzes = pipeline.getNMostPopularQuizzes(NUM_RECENT);
		recentlyCreatedQuizzes = pipeline.getNRecentlyCreatedQuizzes(NUM_RECENT);
		ownRecentlyCreatedQuizzes = pipeline.getNOwnRecentlyCreatedQuizzes(username, NUM_RECENT);
		ownRecentPerformances = pipeline.getNOwnRecentPerformances(username, NUM_RECENT);
		newAchievements = pipeline.getNewAchievements(username);
		recentFriendRequests = pipeline.getNRecentMessages(username, "friend_request", NUM_RECENT);
		recentChallenges = pipeline.getNRecentMessages(username, "challenge", NUM_RECENT);
		recentNotes = pipeline.getNRecentMessages(username, "note", NUM_RECENT);
		recentFriendActivity = pipeline.getRecentFriendActivity(username, NUM_RECENT);
		pipeline.closePipeline();
	}
	
	public ArrayList<Message> getNewAnnouncements() {
		return newAnnouncements;
	}
	
	public ArrayList<String> getPopularQuizzes() {
		return popularQuizzes;
	}
	
	public ArrayList<String> getRecentlyCreatedQuizzes() {
		return recentlyCreatedQuizzes;
	}
	
	public ArrayList<Performance> getUserRecentPerformances() {
		return ownRecentPerformances;
	}
	
	public ArrayList<String> getUserRecentlyCreatedQuizzes() {
		return ownRecentlyCreatedQuizzes;
	}
	
	public ArrayList<Achievement> getNewAchievements() {
		return newAchievements;
	}
	
	public ArrayList<Message> getRecentFriendRequests() {
		return recentFriendRequests;
	}
	
	public ArrayList<Message> getRecentChallenges() {
		return recentChallenges;
	}
	
	public ArrayList<Message> getRecentNotes() {
		return recentNotes;
	}
	
	public ArrayList<Activity> getRecentFriendActivity() {
		return recentFriendActivity;
	}
}
