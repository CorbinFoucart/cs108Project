package project;

/**
 * Extension of User class with additional ability
 * to clear Quiz histories, get important statistics,
 * promote and demote fellow Administrators, make announcements,
 * etc.
 * @author rebeccadeubler
 *
 */

public class Admin extends User {

	/**
	 * Creates a new Admin from an existing User.
	 * @param username user's username
	 * @param hashedPassword user's hashed password
	 */
	public Admin(String username, String hashedPassword) {
		super(username, hashedPassword);
		admin = true; 
	}
	
	// To reconstruct from the database
	public Admin(String username, String hashedPassword, int privacySetting, long rating){
		super(username, hashedPassword, privacySetting, rating);
		admin = true; 
	}
	
	/**
	 * Clears the quiz history for a given quiz, i.e.
	 * removes all performances for the quiz
	 * @param quiz_id quiz's id
	 */
	public void clearQuizHistory(String quiz_id){
		db.clearQuizHistory(quiz_id); 
	}
	
	/**
	 * Returns the number of quizzes for
	 * site
	 * @return number of quizzes  
	 */
	public int getNumQuizzes(){
		return db.totalNumberOfQuizzes(); 
	}
	

	public int getNumQuizzesTaken() {
		return db.getNumberQuizzesTaken();
	}
	
	
	/**
	 * Returns the number of site users
	 * @return number of site users
	 */
	public int getNumUsers(){
		return db.totalNumberOfUsers(); 
	}
	
	
	/**
	 * Returns number of users and number of quizzes taken
	 * in string status
	 * @return string status
	 */
	public String getSiteStatisticsString(){
		return "There are " + getNumUsers() + " users and " + getNumQuizzesTaken() + " quizzes have been taken."; 
	}
	
	/**
	 * Removes a user from the site
	 * @param username username of person to be removed
	 */
	public void removeUser(String username) {
		db.removeUser(username);
	}
	
	/**
	 * Remove a quiz from the site
	 * @param quiz_id string quiz id
	 */
	public void removeQuiz(String quiz_id) {
		db.removeQuiz(quiz_id);
	}
	
	/**
	 * Promotes the specified User to Admin
	 * @param username username of user to be promoted
	 */
	public void promoteToAdmin(String username) {
		db.promoteToAdmin(username);
	}
	
	/**
	 * Demotes the specified User from Admin
	 * @param username username of user to be demoted
	 */
	public void demoteFromAdmin(String username) {
		db.demoteFromAdmin(username);
	}
	
}
