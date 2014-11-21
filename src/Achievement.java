package project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Achievement {
	private String username;
	private String achievement_type;
	private String date_string;
	private long date_long;
	private boolean announced;
	private String id;
	
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
	
	public Achievement(String username, String achievement_type, 
			String date_string, long date_long, boolean announced, String id) {
		this.username = username;
		this.achievement_type = achievement_type;
		this.date_string = date_string;
		this.announced = announced;
		this.id = id;
	}
	
	public void generateID() {
		IDGenerator generator = new IDGenerator();
		id = generator.generateID();
	}
	
	public String getID() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getAchievementType() {
		return achievement_type;
	}
	
	public String getDateAsString() {
		return date_string;
	}
	
	public long getDateAsLong() {
		return date_long;
	}
	
	public boolean isAnnounced() {
		return announced;
	}
	
}
