package project;

public class Admin extends User {

	// To create new object as the user creates it
	public Admin(String username, String hashedPassword) {
		super(username, hashedPassword);
		admin = true; 
	}
	
	// To reconstruct from the database
	public Admin(String username, String hashedPassword, int privacySetting){
		super(username, hashedPassword, privacySetting);
		admin = true; 
	}
	
	public void clearQuizHistory(String quiz_id){
		db.clearQuizHistory(quiz_id); 
	}
	
	public int getNumQuizzesTaken(){
		return db.totalNumberOfQuizzes(); 
	}
	
	public int getNumUsers(){
		return db.totalNumberOfUsers(); 
	}
	
	public String getSiteStatisticsString(){
		return "There are " + getNumUsers() + " users and " + getNumQuizzesTaken() + " quizzes have been taken."; 
	}
	
}
