package project;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;
import project.*;

 // testing
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;



public class DatabasePipeline {
	private DBConnection db_con;
	private Connection con;
	private Statement stmt;
	


	/**
	 * Constructor; creates a new DatabasePipeline Object
	 * Uses DBConnection inner class
	 */
	public DatabasePipeline() {
		db_con = new DBConnection();
		con = db_con.getConnection();
		stmt = db_con.getStatement();
	}
	
    // -------------------------------------------------   ADDING TO THE DATABASE  ------------------------------------------- //
	
    // ------------------------------ User Data Methods --------------------------------- //
	
	/**
	 * Adds a user's information into the user_table of the database.
	 * @param user- user object to be added to the quiz database
	 */
	public void addUser(User user) {
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO user_table VALUES(?, ?, ?, ?);");
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getPrivacy());
			pstmt.setBoolean(4, user.isAdmin());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes a user's admin boolean to 1 in the database; 
	 * @param user - string username of the user to be promoted to admin
	 */
	public void promoteToAdmin(String user) {
		try {
			stmt.executeUpdate("UPDATE user_table SET admin_status=1 WHERE username=\"" + user + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes a user's admin boolean to 0 in the database
	 * @param user - string username of user to have admin status removed
	 */
	public void demoteFromAdmin(String user) {
		try {
			stmt.executeUpdate("UPDATE user_table SET admin_status=0 WHERE username=\"" + user + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a single friend pairing to the database in the form of a link
	 * from friend1 to friend2 and from friend2 to friend1
	 * @param friend1 - user object of first friend
	 * @param friend2 - user object of second friend
	 */
	public void addFriendshipToDB(User friend1, User friend2) {
		try {
			stmt.executeUpdate("INSERT INTO friends_table VALUES(\"" + friend1.getUsername() + "\", \"" + friend2.getUsername() + "\")");
			stmt.executeUpdate("INSERT INTO friends_table VALUES(\"" + friend2.getUsername() + "\", \"" + friend1.getUsername() + "\")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes the integer that deontes a privacy setting in the database
	 * for a specified user.
	 * 
	 * @param user - user whose privacy setting is to be updated
	 * @param privacy - the integer specifying privacy level
	 */
	public void updatePrivacySetting(String user, int privacy) {
		try {
			stmt.executeUpdate("UPDATE user_table SET privacy=\"" 
								+ privacy + "\" WHERE username=\"" + user + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------- Quiz / Performance / Question Data Methods ----------------------- // 
	
	/**
	 * Adds a quiz object to the quizzes database. Enters in both
	 * a bit dump SQL blob as well as quiz table parameters.
	 * 
	 * Note that this method also adds every question in the 
	 * quiz to the datbase as well.
	 * 
	 * @param quiz - quiz object to be entered into the database 
	 */
	public void addQuizToDB(Quiz quiz) {
		Blob quizBlob = blobify(quiz);
		try {
			while (true) {
				String id = quiz.getQuizID();
				ResultSet rs = stmt.executeQuery("SELECT * FROM quiz_table WHERE quiz_id=\"" + id + "\"");
				if (!rs.next()) break;
				quiz.generateID();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < quiz.getNumQuestions(); i++) {
			Question q = quiz.getQuestion(i);
			q.setQuizID(quiz.getQuizID());
			addQuestionToDB(q);
		}
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO quiz_table VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, quiz.getName());
			pstmt.setString(2, quiz.getQuizID());
			pstmt.setString(3, quiz.getDateAsString());
			pstmt.setLong(4, quiz.getDateAsLong());
			pstmt.setString(5, quiz.getCreator());
			pstmt.setBlob(6, quizBlob);
			pstmt.setString(7, quiz.getCategory());
			pstmt.setString(8, quiz.getTagsString());
			pstmt.setString(9, quiz.getDescription());
			pstmt.setLong(10, 0);
			pstmt.executeUpdate();
			pstmt.close();
			Activity act = new Activity(quiz.getCreator(), Activity.QUIZ_CREATED, 
					quiz.getQuizID(), quiz.getDateAsString(), quiz.getDateAsLong());
			addActivity(act);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			/**
			 * Helper method to addQuizToDB
			 * Adds a question to the questions table of database
			 * 
			 * can be called by itself; perhaps for edit mode
			 * @param ques - question object to be added to database
			 */
			public void addQuestionToDB(Question ques) {
				Blob quesBlob = blobify(ques);
				try {
					
					while (true) {
						String id = ques.getID();
						ResultSet rs = stmt.executeQuery("SELECT * FROM question_table WHERE question_id=\"" + id + "\"");
						if (!rs.next()) break;
						ques.generateID();
					} 
					
					PreparedStatement pstmt = 
						con.prepareStatement("INSERT INTO question_table VALUES(?, ?, ?, ?)");
					pstmt.setBlob(1, quesBlob);
					pstmt.setString(2, ques.getQuestion());
					pstmt.setString(3, ques.getQuizID());
					pstmt.setString(4, ques.getID());
					pstmt.executeUpdate();
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
	/**
	 * Retrieves the number of times a quiz has been taken and increments
	 * the value by 1, saving the result in the database.
	 * @param quiz_id - the unique quiz id of the quiz taken
	 */
	public void incrementQuizTaken(String quiz_id) {
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM quiz_table WHERE quiz_id= \"" + quiz_id + "\"");
			if (rs.next()) {
			long nTimesTaken = rs.getLong("n_times_taken");
			nTimesTaken++;
			stmt.executeUpdate("UPDATE quiz_table SET n_times_taken = \"" + nTimesTaken + 
								"\" WHERE quiz_id = \"" + quiz_id + "\";" );
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Sets the number of times a quiz has been taken to 0 in the database.
	 */
	public void resetTimesQuizTaken(String quiz_id) {
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM quiz_table WHERE quiz_id= \"" + quiz_id + "\"");
			if (rs.next()) {
			long nTimesTaken = rs.getLong("n_times_taken");
			nTimesTaken = 0;
			stmt.executeUpdate("UPDATE quiz_table SET n_times_taken = \"" + nTimesTaken + 
								"\" WHERE quiz_id = \"" + quiz_id + "\";" );
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Just totally nukes the quiz that the admin wants. Wipes all performances,
	 * questions, the quiz itself, everything.
	 * @param quiz_id - id of the quiz that should be wiped from the site
	 */
	public void clearQuizHistory(String quiz_id) {
		try {
			stmt.executeUpdate("DELETE FROM performance_table WHERE quiz_id=\"" + quiz_id + "\"");
			stmt.executeUpdate("DELETE FROM quiz_table WHERE quiz_id=\"" + quiz_id + "\"");
			stmt.executeUpdate("DELETE FROM question_table WHERE quiz_id=\"" + quiz_id + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to add a performance object to the database
	 * checks to make sure that the ID is not already in use
	 * 
	 * 
	 * 
	 * @param perf - performance object to be added
	 */
	public void addPerformanceToDB(Performance perf) {
		try {
			
			// generate a unique performance ID
			while (true) {
				String id = perf.getID();
				ResultSet rs = stmt.executeQuery("SELECT * FROM performance_table WHERE performance_id=\"" + id + "\"");
				if (!rs.next()) break;
				perf.generateID();
			}
			
			// add the performance into the performances table
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO performance_table VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, perf.getQuizName());
			pstmt.setString(2, perf.getQuizID());
			pstmt.setString(3, perf.getUser());
			pstmt.setDouble(4, perf.getScore());
			pstmt.setString(5, perf.getDateAsString());
			pstmt.setLong(6, perf.getDateAsLong());
			pstmt.setString(7, perf.getID());
			pstmt.setLong(8, perf.getTimeTaken());
			pstmt.executeUpdate();
			pstmt.close();
			Activity act = new Activity(perf.getUser(), Activity.QUIZ_TAKEN, 
					perf.getID(), perf.getDateAsString(), perf.getDateAsLong());
			addActivity(act);
			incrementQuizTaken(perf.getQuizID());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------------- Message Data Methods ------------------------------- //
	
	/**
	 * Adds a message object to the messages table of the database
	 * @param msg - the message object to be added to the database
	 */
	public void addMessage(Message msg) {
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO message_table VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, msg.getTo());
			pstmt.setString(2, msg.getFrom());
			pstmt.setString(3, msg.getMessage());
			pstmt.setString(4, msg.getDateAsString());
			pstmt.setLong(5, msg.getDateAsLong());
			pstmt.setBoolean(6, false);
			pstmt.setString(7, msg.getQuizID());
			pstmt.setString(8, msg.getType());
			pstmt.setString(9, msg.getID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
				/**
				 * Adds a note to the database by creating a 
				 * note object and pass in the message
				 * @param cnote
				 */
				public void addNote(Note note) {
					addMessage(note);
				}
				
				/**
				 * Adds an announcement message to the messages database
				 * @param announcement - announcement message type
				 */
				public void addAnnouncement(Announcement announcement) {
					ArrayList<String> usernames = getAllUsernames();
					for (int i = 0; i < usernames.size(); i++){
						String currUsername = usernames.get(i);
						announcement.generateID();
						announcement.setRecipient(currUsername);
						addMessage(announcement);
					}
				}
				
				/**
				 * Adds a friend request to the database
				 * @param request
				 */
				public void addFriendRequest(FriendRequest request) {
					addMessage(request);
				}
				
	
	/**
	 * Removes any type of message from the database by using its 
	 * message_id, which is unique to each message.
	 * 
	 * @param message_id - the unique message id of the message to 
	 * be removed
	 */
	public void removeMessage(String message_id) {
		try {
			stmt.executeUpdate("DELETE FROM message_table WHERE message_id =\"" + message_id + "\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	//-------------------------------------------------   RETRIEVING FROM THE DATABASE  --------------------------------------------- //
	
		// ---------------------------------- User Data Retrieval ------------------------------------ //
		
		// ----------------------- Quiz / Performance / Question Retrieval --------------------------- //
		
		/**
		 * Returns the quiz object of a quiz stored in the database
		 * @param quiz_id - quiz identity string 
		 * @return
		 */
		public Quiz retrieveQuizFromDB(String quiz_id) {
			Quiz retrieved = null;
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM quiz_table WHERE quiz_id=\"" + quiz_id + "\"");
				retrieved = (Quiz) deBlob(rs, 6);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return retrieved;
		}
		
		/**
		 * Retrieves a question from the database by using its question ID as a 
		 * search parameter. 
		 * 
		 * @param question
		 * @return
		 */
		public Question retrieveQuestionFromDB(String question_ID) {
			Question retrieved = null;
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM question_table WHERE question_id=\"" + question_ID + "\"");
				retrieved = (Question) deBlob(rs, 1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return retrieved;
		}
		
	// ------------------------------- Message Data Retrieval ------------------------------------ //
		
		/**
		 * Removes any type of message from the database by using its 
		 * message_id, which is unique to each message.
		 * 
		 * @param message_id - the unique message id of the message to 
		 * be removed
		 */
		public Message getMessage(String message_id) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE message_id =\"" + message_id + "\"");
				Message retrieved = new Message(rs.getString("recipient"), rs.getString("sender"), 
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"), 
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"),
						rs.getString("message_id"));
				return retrieved;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
	
	//-----------------------------------------------------   UNTESTED  ------------------------------------------------ //
	
		
		// stuff for front end to mull over; things we need
		// TODO inform front end that usernames are case insensitive
		// TODO Challenges can be made between non existent users; need fix?
		// TODO case where both have friended each other, and one accepts, need request remove
		// TODO general message check where checks if both users exist before message is sent
		// TODO do friend requests need a read boolean?
		// TODO user can only send an announcement if admin
		// TODO add announcement method 
		// TODO ask front end to never pass in empty string for username; password.
		// TODO inform front end of name change quiz
		// TODO CHECK USER ASK FRONT END
		
		
		// write 
		// TODO ifUserExists boolean

		
		
		// do we need more specificity here? Assumptions
		
		


	
	private void addAchievementToDB(Achievement achievement) {
		try {
			while (true) {
				String id = achievement.getID();
				ResultSet rs = stmt.executeQuery("SELECT * FROM achievement_table WHERE achievement_id=\"" + id + "\"");
				if (!rs.next()) break;
				achievement.generateID();
			} 
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO achievement_table VALUES(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, achievement.getUsername());
			pstmt.setString(2, achievement.getAchievementType());
			pstmt.setString(3, achievement.getDateAsString());
			pstmt.setLong(4, achievement.getDateAsLong());
			pstmt.setBoolean(5, achievement.isAnnounced());
			pstmt.setString(6, achievement.getID());
			pstmt.executeUpdate();
			Activity act = new Activity(achievement.getUsername(), Activity.ACHIEVEMENT_EARNED, 
					achievement.getID(), achievement.getDateAsString(), achievement.getDateAsLong());
			addActivity(act);
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ArrayList<String> getFriends(String user) {
		ArrayList<String> friends = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM friends_table WHERE friend_one=\"" + user + "\"");
			while (rs.next()) {
				String friend = rs.getString("friend_two");
				if (friend != null) friends.add(friend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friends;
	}
	
	public ArrayList<Message> getMessages(String user) {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE recipient=\"" 
											 + user + "\" AND message_type=\"note\"");
			while (rs.next()) {
				Message msg = new Message(rs.getString("recipient"), rs.getString("sender"), 
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"), 
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"),
						rs.getString("message_id"));
				messages.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	/**
	 * Reads the quiz_ids in from the databse and returns
	 * the entire list as a string array.
	 * 
	 * @return - returns an array list of String quiz_id
	 */
	public ArrayList<String> getAllQuizzes() {
		ArrayList<String> quizzesList = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT quiz_id FROM quiz_table ");
			while (rs.next()) {
				String qID = rs.getString("quiz_id");
				quizzesList.add(qID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizzesList;
		
		
	}
	
	public void markAsRead(String message_id) {
		try {
			stmt.executeUpdate("UPDATE message_table SET was_read=1 WHERE message_id=\"" + message_id + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Message> getFriendRequests(String user) {
		ArrayList<Message> requests = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE recipient=\""
											+ user + "\" AND message_type=\"friend_request\"");
			while (rs.next()) {
				Message msg = new Message(rs.getString("recipient"), rs.getString("sender"), 
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"), 
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"),
						rs.getString("message_id"));
				requests.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}
	
	public ArrayList<Message> getChallenges(String user) {
		ArrayList<Message> challenges = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE recipient=\"" 
											  + user + "\" AND message_type=\"challenge\"");
			while (rs.next()) {
				Message msg = new Message(rs.getString("recipient"), rs.getString("sender"), 
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"), 
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"),
						rs.getString("message_id"));
				challenges.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return challenges;
	}
	

	
	public ArrayList<Message> getAnnouncements(String user) {
		ArrayList<Message> announcements = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE recipient=\"" 
											 + user + "\" AND message_type =\"announcement\"");
			while (rs.next()) {
				Message announcement = new Message(rs.getString("recipient"), rs.getString("sender"),
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"),
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"),
						rs.getString("message_id"));
				announcements.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return announcements;
	}
	
	public ArrayList<Message> getNNewAnnouncements(String user, int numLimit) {
		ArrayList<Message> announcements = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE recipient=\"" 
											 + user + "\" AND message_type =\"announcement\" AND was_read=0 ORDER BY date_long DESC LIMIT " + numLimit);
			while (rs.next()) {
				Message announcement = new Message(rs.getString("recipient"), rs.getString("sender"),
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"),
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"), rs.getString("message_id"));
				announcements.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return announcements;
	}
	
	public User getUser(String userName) {
		User user = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_table WHERE username=\"" + userName + "\"");
			if (rs.next()) {
				boolean type = rs.getBoolean("admin_status");
				if (type) {
					user = new Admin(rs.getString("username"), rs.getString("password"), rs.getInt("privacy"));
				} else {
					user = new User(rs.getString("username"), rs.getString("password"), rs.getInt("privacy"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	

	
	public ArrayList<String> getAllUsernames() {
		ArrayList<String> usernames = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_table");
			while (rs.next()) {
				String username = rs.getString("username");
				usernames.add(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usernames;
	}
	
	public boolean checkIfAdmin(String user) {
		Boolean isAdmin = false;
		try {
			ResultSet rs = stmt.executeQuery("SELECT admin_status FROM user_table WHERE username=\"" + user + "\"");
			if (rs.next() && rs.getBoolean("admin_status")) {
				isAdmin = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAdmin;
	}
	
	public int checkPrivacySettings(String user) {
		int setting = -1;
		try {
			ResultSet rs = stmt.executeQuery("SELECT privacy FROM user_table WHERE username=\"" + user + "\"");
			if (rs.next()) {
				setting = rs.getInt("privacy");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return setting;
	}
	
	public String getPasswordFromDB(String user) {
		String password = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT password FROM user_table WHERE username=\"" + user + "\"");
			if (rs.next()) {
				password = rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}	
	

	
	public int totalNumberOfQuizzes() {
		int quizNum = 0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS row_count FROM quiz_table");
			if (rs.next()) {
				quizNum = rs.getInt("row_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizNum;
	}

	
	public int totalNumberOfUsers() {
		int usersNum = 0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS row_count FROM user_table");
			if (rs.next()) {
				usersNum = rs.getInt("row_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersNum;
	}
	
	public int getNumberQuizzesTaken(String user) {
		int quizNum = 0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS row_count " +
					"FROM performance_table WHERE taken_by_user=\"" + user + "\"");
			if (rs.next()) {
				quizNum = rs.getInt("row_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizNum;
	}
	
	
	public int getNumberQuizzesCreated(String user) {
		int quizNum = 0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS row_count " +
					"FROM quiz_table WHERE creator=\"" + user + "\"");
			if (rs.next()) {
				quizNum = rs.getInt("row_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizNum;
	}
	
	
	public ArrayList<Message> getNRecentMessages(String user, String type, int num_recent) {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE recipient=\"" + user + 
												"\" AND message_type=\"" + type + 
												"\" AND was_read=0 ORDER BY date_long DESC LIMIT " + num_recent);
			while (rs.next()) {
				Message msg = new Message(rs.getString("recipient"), rs.getString("sender"), 
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"), 
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"),
						rs.getString("message_id"));
				messages.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public ArrayList<String> getQuizzesCreated(String user) {
		ArrayList<String> retrieved = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT quiz_id FROM quiz_table WHERE creator=\"" + user + "\"");
			String retrievedQuiz;
			while (true) {
				retrievedQuiz = rs.getString("quiz_id");
				if (retrievedQuiz == null) break;
				retrieved.add(retrievedQuiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public ArrayList<Performance> getPerformances(String user) {
		ArrayList<Performance> retrieved = new ArrayList<Performance>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM performance_table WHERE taken_by_user=\"" + user + "\"");
			while (rs.next()) {
				Performance perf = new Performance(rs.getString("quiz_name"), rs.getString("quiz_id"), 
						rs.getString("taken_by_user"), rs.getDouble("score"), rs.getString("date_string"),
						rs.getLong("date_long"), rs.getString("performance_id"), rs.getLong("time_taken"));
				retrieved.add(perf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public ArrayList<Performance> getNRecentPerformances(String user, int num_recent) {
		ArrayList<Performance> retrieved = new ArrayList<Performance>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM performance_table WHERE taken_by_user=\"" 
							+ user + "\" ORDER BY date_long DESC LIMIT " + num_recent);
			while (rs.next()) {
				Performance perf = new Performance(rs.getString("quiz_name"), rs.getString("quiz_id"), 
						rs.getString("taken_by_user"), rs.getDouble("score"), rs.getString("date_string"),
						rs.getLong("date_long"), rs.getString("performance_id"), rs.getLong("time_taken"));
				retrieved.add(perf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public ArrayList<Performance> getQuizPerformances(String user, String quiz_id) {
		ArrayList<Performance> retrieved = new ArrayList<Performance>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM performance_table WHERE quiz_id=\""
												+ quiz_id + "\" AND taken_by_user=\"" + user + "\"");
			while (rs.next()) {
				Performance perf = new Performance(rs.getString("quiz_name"), rs.getString("quiz_id"), 
						rs.getString("taken_by_user"), rs.getDouble("score"), rs.getString("date_string"),
						rs.getLong("date_long"), rs.getString("performance_id"), rs.getLong("time_taken"));
				retrieved.add(perf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public ArrayList<String> getCategories() {
		ArrayList<String> categories = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM category_table");
			while (rs.next()) {
				categories.add(rs.getString("category_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	public void addCategory(String newCat) {
		try {
			stmt.executeUpdate("INSERT INTO category_table VALUES(" + newCat + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getQuizNameFromID(String id) {
		String name = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT quiz_name FROM quiz_table WHERE quiz_id=" + id);
			if (rs.next()) name = rs.getString("quiz_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	public ArrayList<String> getNMostPopularQuizzes(int num_recent) {
		ArrayList<String> popular = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT quiz_id FROM quiz_table ORDER BY n_times_taken DESC LIMIT " + num_recent);
			while (rs.next()) {
				String id = rs.getString("quiz_id");
				popular.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return popular;
	}
	
	public ArrayList<String> getNOwnRecentlyCreatedQuizzes(String username, int num_recent) {
		ArrayList<String> recent = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT quiz_id FROM quiz_table WHERE creator=\"" + username + "\" ORDER BY date_long DESC LIMIT " + num_recent);
			while (rs.next()) {
				String id = rs.getString("quiz_id");
				recent.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recent;
	}
	
	
	public ArrayList<String> getNRecentlyCreatedQuizzes(int num_recent) {
		ArrayList<String> recent = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT quiz_id FROM quiz_table ORDER BY date_long DESC LIMIT " + num_recent);
			while (rs.next()) {
				String id = rs.getString("quiz_id");
				recent.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recent;
	}
	
	
	public ArrayList<Performance> getNRecentQuizPerformances(String quiz_id, int num_recent) {
		ArrayList<Performance> retrieved = new ArrayList<Performance>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM performance_table WHERE quiz_id=\""
												+ quiz_id + "\" ORDER BY date_long DESC LIMIT " + num_recent);
			while (rs.next()) {
				Performance perf = new Performance(rs.getString("quiz_name"), rs.getString("quiz_id"), 
						rs.getString("taken_by_user"), rs.getDouble("score"), rs.getString("date_string"),
						rs.getLong("date_long"), rs.getString("performance_id"), rs.getLong("time_taken"));
				retrieved.add(perf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	
	public ArrayList<Performance> getNOwnRecentPerformances(String username, int num_recent) {
		ArrayList<Performance> retrieved = new ArrayList<Performance>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM performance_table WHERE taken_by_user=\"" + username + 
					"\" ORDER BY date_long DESC LIMIT " + num_recent);
			while (rs.next()) {
				Performance perf = new Performance(rs.getString("quiz_name"), rs.getString("quiz_id"), 
						rs.getString("taken_by_user"), rs.getDouble("score"), rs.getString("date_string"),
						rs.getLong("date_long"), rs.getString("performance_id"), rs.getLong("time_taken"));
				retrieved.add(perf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public ArrayList<Achievement> getNewAchievements(String username) {
		ArrayList<Achievement> retrieved = new ArrayList<Achievement>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM achievement_table WHERE taken_by_user=\"" + username + "\" AND announced=0");
			while (rs.next()) {
				Achievement ach = new Achievement(rs.getString("username"), rs.getString("achievement_type"), 
						rs.getString("date_string"), rs.getLong("date_long"), rs.getBoolean("announced"), rs.getString("achievement_id"));
				retrieved.add(ach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public void addActivity(Activity act) {
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO activity_table VALUES(?, ?, ?, ?, ?);");
			pstmt.setString(1, act.getUsername());
			pstmt.setString(2, act.getActivityType());
			pstmt.setString(3, act.getRelevantID());
			pstmt.setString(4, act.getDateAsString());
			pstmt.setLong(5, act.getDateAsLong());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Activity> getRecentFriendActivity(String username, int num_recent) {
		ArrayList<Activity> recent = new ArrayList<Activity>();
		try {
			String input_str = "SELECT * FROM activity_table WHERE ";
			ArrayList<String> friends = getFriends(username);
			for (int i = 0; i < friends.size(); i++) {
				if (i != 0) input_str += "OR ";
				input_str += ("username=\"" + friends.get(i) + "\" ");
			}
			ResultSet rs = stmt.executeQuery(input_str);
			while (rs.next()) {
				Activity act = new Activity(rs.getString("username"), 
						rs.getString("activity_type"), rs.getString("relevant_id"),
						rs.getString("date_string"), rs.getLong("date_long"));
				recent.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recent;
	}
	
	
	public String getQuizDescription(String quiz_id) {
		String description = "";
		try {
			ResultSet rs = stmt.executeQuery("SELECT description FROM quiz_table WHERE quiz_id=\"" + quiz_id + "\"");
			if (rs.next()) description = rs.getString("description");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return description;
	}
	
	public String getCreator(String quiz_id) {
		String creator = "";
		try {
			ResultSet rs = stmt.executeQuery("SELECT creator FROM quiz_table WHERE quiz_id=\"" + quiz_id + "\"");
			if (rs.next()) creator = rs.getString("creator");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return creator;
	}
	
	public ArrayList<Performance> getHighestUniquePerformances(String quiz_id, int num_performers) {
		ArrayList<Performance> retrieved = new ArrayList<Performance>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT f.* FROM (SELECT taken_by_user, MAX(score) as " +
					"max_score FROM performance_table GROUP BY taken_by_user) AS x INNER JOIN " +
					"performance_table AS f ON f.taken_by_user = x.taken_by_user AND f.score = " +
					"x.max_score WHERE quiz_id=\"" + quiz_id + "\" ORDER BY score DESC LIMIT " + 
					num_performers);
			while (rs.next()) {
				Performance perf = new Performance(rs.getString("quiz_name"), rs.getString("quiz_id"), 
						rs.getString("taken_by_user"), rs.getDouble("score"), rs.getString("date_string"),
						rs.getLong("date_long"), rs.getString("performance_id"), rs.getLong("time_taken"));
				retrieved.add(perf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public ArrayList<Performance> getTodaysHighestUniquePerformances(String quiz_id,  int num_performers) {
		ArrayList<Performance> retrieved = new ArrayList<Performance>();
		Date dateObj = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		long this_date_long = Long.parseLong(dateFormat.format(dateObj));
		this_date_long *= 1000000;
		try {
			ResultSet rs = stmt.executeQuery("SELECT f.* FROM (SELECT taken_by_user, MAX(score) as " +
					"max_score FROM performance_table GROUP BY taken_by_user) AS x INNER JOIN " +
					"performance_table AS f ON f.taken_by_user = x.taken_by_user AND f.score = " +
					"x.max_score WHERE quiz_id=\"" + quiz_id + "\" AND date_long >=" + this_date_long + " ORDER BY score DESC LIMIT " + 
					num_performers);
			while (rs.next()) {
				Performance perf = new Performance(rs.getString("quiz_name"), rs.getString("quiz_id"), 
						rs.getString("taken_by_user"), rs.getDouble("score"), rs.getString("date_string"),
						rs.getLong("date_long"), rs.getString("performance_id"), rs.getLong("time_taken"));
				retrieved.add(perf);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}

	
	public int getNumTimesQuizTaken(String quiz_id) {
		int num = 0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS row_count FROM performance_table WHERE quiz_id = \"" + quiz_id + "\"");
			if (rs.next()) num = rs.getInt("row_count");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public double getQuizAverage(String quiz_id) {
		double total = 0;
		int num_taken = 0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM performance_table WHERE quiz_id=\"" + quiz_id + "\"");
			while (rs.next()) {
				total += rs.getDouble("score");
				num_taken++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (total / num_taken);
	}
	
	// ----------- challenge methods ---------- //
	
	public Challenge getChallenge(String challenge_id) {
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM challenge_table WHERE challenge_id=\"" + challenge_id + "\"");
			while (rs.next()) {
				Challenge ch;
				ch = new Challenge(rs.getString("issuer"),
								   rs.getString("recipient"),
								   rs.getString("quiz_id"),
								   rs.getString("message"),
								   rs.getString("issuer_perf_id"),
								   rs.getString("recipient_perf_id"),
								   rs.getString("status"),
								   rs.getBoolean("announced"),
								   rs.getString("winner"),
								   rs.getString("loser"),
								   rs.getString("challenge_id"));
				return ch;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	
	
	
	// --------------------------------------------- Extra  Utilities -------------------------------------------- //
	
	private Blob blobify(Object obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream o = new ObjectOutputStream(baos);
			o.writeObject(obj);
		    byte[] objAsBytes = baos.toByteArray();
		    Blob newBlob = new SerialBlob(objAsBytes);
		    return newBlob;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Object deBlob(ResultSet rs, int index) {
		try {
			if (rs.next()) {
				byte[] st = (byte[]) rs.getObject(index);
		    	ByteArrayInputStream baip = new ByteArrayInputStream(st);
		        ObjectInputStream ois = new ObjectInputStream(baip);
		        return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Closes the DatabasePipeline connection;
	 * i.e. closes the sql connection;
	 */
	public void closePipeline() {
		db_con.closeConnection();
	}
	
	
	// -------------------------------------------------- Debugging methods   --------------------------------------------------- // 
	
	// clears the quiz database and resets the tables
	public void clearDatabase() {
		// drop existing tables
		String dropTables = "DROP TABLE IF EXISTS user_table, friends_table, message_table, category_table,"
			+ "activity_table, performance_table, quiz_table, question_table, achievement_table, questions_table;";
		
		String AddQuizTables1 = "CREATE TABLE user_table (" +
				" username CHAR(64)," +
				" password CHAR(64), " +
			    " privacy INT," +  
			    " admin_status BOOLEAN" +
			    ");";
			    
		String AddQuizTables2 = "CREATE TABLE friends_table (" +
							       " friend_one CHAR(64)," +
							       " friend_two CHAR(64) " +
							       ");";
		
		String AddQuizTables3 = "CREATE TABLE message_table (" +
						       " recipient CHAR(64)," +
						       " sender CHAR(64)," +
							   " message VARCHAR(1000)," +
							   " date_string CHAR(64)," +
							   " date_long BIGINT," +
							   " was_read BOOLEAN," +
							   " quiz_id CHAR(64)," +
							   " message_type CHAR(64)," +
							   " message_id CHAR(64)" +
							  ");";
		
		String AddQuizTables4 = "CREATE TABLE performance_table (" +
						       " quiz_name CHAR(64)," +
						       " quiz_id CHAR(64)," +
						       " taken_by_user CHAR(64)," +
						       " score DECIMAL(5,4)," +
						       " date_string CHAR(64)," +
						       " date_long BIGINT," +
						       " performance_id CHAR(64)," +
						       " time_taken BIGINT" +
						       ");";
		
		String AddQuizTables5 = "CREATE TABLE quiz_table (" +
						       " quiz_name CHAR(64)," +
						       " quiz_id CHAR(64)," +
						       " date_string CHAR(64)," +
						       " date_long BIGINT," +
						       " creator CHAR(64)," +
						       " quiz_bit_dump BLOB," +
						       " category CHAR(64)," +
						       " tag_string VARCHAR(1000)," +
						       " description VARCHAR(1000)," +
						       " n_times_taken BIGINT" + 
						       ");";
		
		String AddQuizTables6 = "CREATE TABLE achievement_table (" +
						       " username CHAR(64)," +
						       " achievement_type CHAR(64), " + 
						       " date_string CHAR(64), " +
						       " date_long BIGINT," +
						       " announced BOOLEAN, " +
						       " achievement_id CHAR(64)" +
						       ");";
		
		String AddQuizTables7 = "CREATE TABLE question_table (" +
						       " question_bit_dump BLOB," +
						       " question_string VARCHAR(64)," +
						       " quiz_id CHAR(64)," +
						       " question_id CHAR(64)" +
						       ");";
		
		String AddQuizTables8 = "CREATE TABLE category_table (" +
								" category CHAR(64)" +
								");";
		
		String AddQuizTables9 = "CREATE TABLE activity_table (" +
								" username CHAR(64), " +
								" activity_type CHAR(64), " +
								" relevant_id CHAR(64), " +
								" date_string CHAR(64), " +
								"date_long BIGINT);";
		
		String AddQuizTables10 = "CREATE TABLE challenge_table (" +
								 " issuer CHAR(64), " +
								 " recipient CHAR(64), " +
								 " message VARCHAR(1000)," +
								 " issuer_perf_id CHAR(64), " +
								 " recipient_perf_id CHAR(64), " +
								 " status CHAR(64)," +
								 " announced BOOLEAN, " +
								 " winner CHAR(64)," +
								 " loser CHAR(64)," +
								 " challenge_id CHAR(64));";
		
		try {
			stmt.executeUpdate(dropTables);
			stmt.executeUpdate(AddQuizTables1);
			stmt.executeUpdate(AddQuizTables2);
			stmt.executeUpdate(AddQuizTables3);
			stmt.executeUpdate(AddQuizTables4);
			stmt.executeUpdate(AddQuizTables5);
			stmt.executeUpdate(AddQuizTables6);
			stmt.executeUpdate(AddQuizTables7);
			stmt.executeUpdate(AddQuizTables8);
			stmt.executeUpdate(AddQuizTables9);
			stmt.executeUpdate(AddQuizTables10);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// recreate tables
		
	}
	
	// --------------------------------- DBConnection inner class --------------------------------- //  
	
	/**
	 * Inner class that sets up a mySQL database connection.
	 * This is useful because it isolates the JDBC code here.
	 * The complexity of forming this connection is hidden, 
	 * since other methods create a connection when necessary. 
	 */	
	private class DBConnection {
		private Statement stmt;
		private Connection con;
		
		public static final String MYSQL_USERNAME = "ccs108rdeubler"; // //"ccs108cfoucart";  // //     
		public static final String MYSQL_PASSWORD =  "vohhaegh"; // // "aigookue";  // //    
		public static final String MYSQL_DATABASE_SERVER = "mysql-user-master.stanford.edu";
		public static final String MYSQL_DATABASE_NAME = "c_cs108_rdeubler"; // "c_cs108_cfoucart"; //     
		
		public DBConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				con = DriverManager.getConnection( "jdbc:mysql://" + MYSQL_DATABASE_SERVER, MYSQL_USERNAME ,MYSQL_PASSWORD);
				stmt = con.createStatement();
				stmt.executeQuery("USE " + MYSQL_DATABASE_NAME);			
				
				
			} catch (SQLException e) {
				 e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
					e.printStackTrace();
			}
		}
		
		public Statement getStatement() {
			return stmt;
		}
		
		public Connection getConnection() {
			return con;
		}
		
		public void closeConnection() {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
}
