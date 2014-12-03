package project;

/**
 * Activity class stores info about quiz taking or making
 * activities, as well as Achievements, for ease in news
 * feed of friend activity
 * @author rebeccadeubler
 *
 */

public class Activity {
	private String username;
	private String activity_type;
	private String relevant_id;
	private String date_string;
	private long date_long;
	
	public static final String QUIZ_TAKEN = "quiz_taken";
	public static final String QUIZ_CREATED = "quiz_created";
	public static final String ACHIEVEMENT_EARNED = "achievement_earned";

	/**
	 * Creates a new activity given the username, activity type,
	 * relevant ID (Quiz or Achievement), date as string, date
	 * as long.
	 * @param username
	 * @param activity_type
	 * @param relevant_id
	 * @param date_string
	 * @param date_long
	 */
	public Activity(String username, String activity_type, String relevant_id, String date_string, long date_long) {
		this.username = username;
		this.activity_type = activity_type;
		this.relevant_id = relevant_id;
		this.date_string = date_string;
		this.date_long = date_long;
	}
	
	/**
	 * Returns the username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Returns the type of activity (quiz making, quiz
	 * taking, achievement earned)
	 * @return type of activity
	 */
	public String getActivityType() {
		return activity_type;
	}
	
	/**
	 * Returns relevant ID (Quiz or Achievement)
	 * @return relevant ID
	 */
	public String getRelevantID() {
		return relevant_id;
	}
	
	/**
	 * Returns date as String
	 * @return date as String
	 */
	public String getDateAsString() {
		return date_string;
	}
	
	/**
	 * Returns date as long
	 * @return date as long
	 */
	public long getDateAsLong() {
		return date_long;
	}
	
}
