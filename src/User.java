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
	
	// can be accessed by admin subclasses
	protected boolean admin;
	protected DatabasePipeline db; 
	private ArrayList<String> achievements; 
	
	// various privacy settings
	private int PUBLIC = 0; 
	private int SEMI_PRIVATE = 0; 
	private int ANON = 0; 
	
	// creating a brand new user upon account creation etc. 
	public User(String username, String hashedPassword){
		this.username = username;
		this.hashedPassword = hashedPassword;
		admin = false; // default to not admin
		privacySetting = 0; //default to public 
		messages = new ArrayList<project.Message>(); 
		recentMessages = new ArrayList<project.Message>(); 
		friends = new ArrayList<String>(); 
		achievements = new ArrayList<String>();
	}
	
	// creating a User Object for a database entry (a user that already exists in the table)
	public User(String username, String hashedPassword, int privacySetting){
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.admin = false; 
		this.privacySetting = privacySetting; 
		this.friends = db.getFriends(username); 
	}
	
	public void setUserDBPipeline(DatabasePipeline dbpl) {
		this.db = dbpl;
	}
	
	// updates the user
	public void updateUser(){
		friends = db.getFriends(username); 
		recentMessages = db.getRecentMessages(username); 	
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
	
}

