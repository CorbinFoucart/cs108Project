package project;

public class Activity {
	String username;
	String activity_type;
	String relevant_id;
	String date_string;
	long date_long;
	
	public static final String QUIZ_TAKEN = "quiz_taken";
	public static final String QUIZ_CREATED = "quiz_created";
	public static final String ACHIEVEMENT_EARNED = "achievement_earned";

	public Activity(String username, String activity_type, String relevant_id, String date_string, long date_long) {
		this.username = username;
		this.activity_type = activity_type;
		this.relevant_id = relevant_id;
		this.date_string = date_string;
		this.date_long = date_long;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getActivityType() {
		return activity_type;
	}
	
	public String getRelevantID() {
		return relevant_id;
	}
	
	public String getDateAsString() {
		return date_string;
	}
	
	public long getDateAsLong() {
		return date_long;
	}
	
}
