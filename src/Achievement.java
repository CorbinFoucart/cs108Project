package project;

/**
 * Achievement class.  Contains username,
 * type of achievement, date as a String and long, 
 * whether or not the Achievement has been announced,
 * and an Achievement ID.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class Achievement {
	private String username;
	private String achievement_type;
	private String date_string;
	private long date_long;
	private boolean announced;
	private String id;
	
	
	// Names of achievements
	public static final String AMATEUR_AUTHOR = "amateur_author";
	public static final String PROLIFIC_AUTHOR = "prolific_author";
	public static final String PRODIGIOUS_AUTHOR = "prodigious_author";
	public static final String ACTIVE_CONTRIBUTOR = "active_contributor";
	public static final String CREATION_LEGEND = "creation_legend";
	public static final String QUIZ_MASTER = "quiz_master";
	public static final String PROGENITOR_DEITY = "progenitor_deity";
	public static final String SHARPSHOOTER = "sharpshooter";
	public static final String DEAD_EYE = "dead_eye";
	public static final String BLACK_KNIGHT = "black_knight";
	public static final String WHITE_KNIGHT = "white_knight";
	public static final String QUIZ_MACHINE = "quiz_machine";
	public static final String I_AM_THE_GREATEST = "i_am_the_greatest";
	public static final String PRACTICE_MAKES_PERFECT = "practice_makes_perfect";
	public static final String BEAT_THE_MONKEY = "beat_the_monkey";
	public static final String BEAT_THE_RAVEN = "beat_the_raven";
	public static final String BEAT_THE_ELEPHANT = "beat_the_elephant";
	
	
	// Relevant numbers for achievements
	public static final int AMATEUR_AUTHOR_NUM = 1;
	public static final int PROLIFIC_AUTHOR_NUM = 5;
	public static final int PRODIGIOUS_AUTHOR_NUM = 10;
	public static final int ACTIVE_CONTRIBUTOR_NUM = 20;
	public static final int CREATION_LEGEND_NUM = 50;
	public static final int QUIZ_MASTER_NUM = 100;
	public static final int PROGENITOR_DEITY_NUM = 500;
	public static final int SHARPSHOOTER_NUM = 20;
	public static final int DEAD_EYE_NUM = 50;
	public static final int BLACK_KNIGHT_NUM = 20;
	public static final int WHITE_KNIGHT_NUM = 20;
	public static final int QUIZ_MACHINE_NUM = 10;
	public static final double BEAT_THE_MONKEY_NUM = 0.5;
	public static final double BEAT_THE_RAVEN_NUM = 0.75;
	public static final double BEAT_THE_ELEPHANT_NUM = 0.9;
	public static final int NUM_THRESHOLD_BEAT_THE = 5;
	
	
	/**
	 * Creates a new Achievement for the given user, of the 
	 * specified type.  Auto-generates an ID and date.
	 * @param username user's username
	 * @param achievement_type name of achievement
	 */
	public Achievement(String username, String achievement_type) {
		this.username = username;
		this.achievement_type = achievement_type;
		generateID();
		Date dateObj = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss");
		date_string = dateFormat.format(dateObj);
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		date_long = Long.parseLong(dateFormat.format(dateObj));
		announced = false;
	}
	
	/**
	 * Creates a new achievement by filling in all fields -
	 * for use in reconstruction from databases
	 * @param username user's username
	 * @param achievement_type name of achievement
	 * @param date_string date as a string
	 * @param date_long date as a long
	 * @param announced false if not yet announced
	 * @param id Achievement id
	 */
	public Achievement(String username, String achievement_type, 
			String date_string, long date_long, boolean announced, String id) {
		this.username = username;
		this.achievement_type = achievement_type;
		this.date_string = date_string;
		this.announced = announced;
		this.id = id;
	}
	
	/**
	 * Generates an ID for Achievement
	 */
	public void generateID() {
		IDGenerator generator = new IDGenerator();
		id = generator.generateID();
	}
	
	/**
	 * Returns ID for Achievement
	 * @return Achievement ID
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Returns username from Achievement
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Returns name of Achievement (type)
	 * @return Achievement type
	 */
	public String getAchievementType() {
		return achievement_type;
	}
	
	
	/**
	 * Returns the date as a string
	 * @return date as string
	 */
	public String getDateAsString() {
		return date_string;
	}
	
	
	/**
	 * Returns the date as a long
	 * @return date as long
	 */
	public long getDateAsLong() {
		return date_long;
	}
	
	/**
	 * Returns false if Achievement has not
	 * been "announced" (displayed), else
	 * true.
	 * @return false if not yet displayed
	 */
	public boolean isAnnounced() {
		return announced;
	}
	
}
