package project;

import java.util.ArrayList;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class User {
	private String username;
	private String hashedPassword;
	private ArrayList<String>friends;
	private int privacySetting;
	private ArrayList<project.Message> messages;
	private ArrayList<project.Message> recentMessages;
	private long rating;
	
	// can be accessed by admin subclasses
	protected boolean admin;
	protected DatabasePipeline db; 
	private ArrayList<String> achievements; 
	
	// various privacy settings
	private int PUBLIC = 0; 
	private int SEMI_PRIVATE = 0; 
	private int ANON = 0; 
	
	/**
	 * 
	 * @param username - the username of the user being created
	 * @param password - the plaintext password of the user
	 */
	public User(String username, String password){
		this.username = username;
		
		HashCracker hc = new HashCracker();	
		this.hashedPassword = hc.getHexString(password);
		
		admin = false; // default to not admin
		privacySetting = 0; //default to public 
		messages = new ArrayList<project.Message>(); 
		recentMessages = new ArrayList<project.Message>(); 
		friends = new ArrayList<String>(); 
		achievements = new ArrayList<String>();
		this.rating = 1200;
	}
	
	// creating a User Object for a database entry (a user that already exists in the table)
	public User(String username, String hashedPassword, int privacySetting, long rating){
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.admin = false; 
		this.privacySetting = privacySetting;
		this.rating = rating;
		

	}
	
	public void setUserDBPipeline(DatabasePipeline dbpl) {
		this.db = dbpl;
	} 
	
	// updates the user
	public void updateUser(){
		friends = db.getFriends(username); 
		messages = db.getMessages(username); 
		admin = db.checkIfAdmin(username); // check if it is an admin
		privacySetting = db.checkPrivacySettings(username); 
		//achievements = db.getAchievements(username); 
	}
	
	public void addFriend(User friend){
		db.addFriendshipToDB(this, friend); 
	}
	
	public void editPrivacySettings(int newSetting){
		privacySetting = newSetting; 
		db.updatePrivacySetting(username, newSetting); 
	}
	
	// returns username based on privacySetting
	public String getUsername(){
		if( (privacySetting == PUBLIC) || (privacySetting == SEMI_PRIVATE)){
			return username; 	
		}else{
			return "Anonymous"; 
		}
	}
	
	public String getUsernamePrivate() {
		return username;
	}
	
	// TODO ASK FRONT END WHAT THEY NEED HERE
	
	public int numFriends(){
		return friends.size(); 
	}
	public int numMessages(){
		return messages.size(); 
	}
	
	public int numRecentMessages(){
		return recentMessages.size(); 
	}
	
	public boolean isAdmin() {
		return admin; 
	}
	
	public String getPassword() {
		return hashedPassword;
	}
	
	public int getPrivacy() {
		return privacySetting;
	}
	
	public long getRating() {
		return rating;
	}
	
	public void setRating(long newRating) {
		this.rating = newRating;
	}
	
}

