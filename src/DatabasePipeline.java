package project;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.rowset.serial.SerialBlob;
import project.*;


public class DatabasePipeline {
	private DBConnection db_con;
	private Connection con;
	private Statement stmt;
	
	private static final int ID_LEN = 8;
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int NUM_RECENT = 15;
	static Random rnd = new Random();

	public DatabasePipeline() {
		db_con = new DBConnection();
		con = db_con.getConnection();
		stmt = db_con.getStatement();
	}
	
	public void closePipeline() {
		db_con.closeConnection();
	}
	
	public void addQuizToDB(Quiz quiz) {
		Blob quizBlob = blobify(quiz);
		String id = generateQuizID();
		quiz.setQuizID(id);
		for (int i = 0; i < quiz.getNumQuestions(); i++) {
			Question q = quiz.getQuestion(i);
			q.setQuizID(id);
			addQuestionToDB(q);
		}
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO quizzes_table VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, quiz.getName());
			pstmt.setString(2, quiz.getQuizID());
			pstmt.setString(3, quiz.getDateAsString());
			pstmt.setLong(4, quiz.getDateAsLong());
			pstmt.setString(5, quiz.getCreator());
			pstmt.setBlob(6, quizBlob);
			pstmt.setString(7, quiz.getCategory());
			pstmt.setString(8, quiz.getTagsString());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Quiz retrieveQuizFromDB(String quiz_id) {
		Quiz retrieved = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes_table WHERE quiz_id=\"" + quiz_id + "\"");
			retrieved = (Quiz) deBlob(rs, 6);
			if (retrieved != null && retrieved.isRandom()) retrieved.shuffleQuiz();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public void addPerformanceToDB(Performance perf) {
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO performance_table VALUES(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, perf.getQuizName());
			pstmt.setString(2, perf.getQuizID());
			pstmt.setString(3, perf.getUser());
			pstmt.setDouble(4, perf.getScore());
			pstmt.setString(5, perf.getDateAsString());
			pstmt.setLong(6, perf.getDateAsLong());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addQuestionToDB(Question ques) {
		Blob quesBlob = blobify(ques);
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO question_table VALUES(?, ?, ?)");
			pstmt.setBlob(1, quesBlob);
			pstmt.setString(2, ques.getQuestion());
			pstmt.setString(3, ques.getQuizID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Question retrieveQuestionFromDB(String question) {
		Question retrieved = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM question_table WHERE question_string=\"" + question + "\"");
			retrieved = (Question) deBlob(rs, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrieved;
	}
	
	public ArrayList<String> getFriends(String user) {
		ArrayList<String> friends = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM friends_table WHERE friend_one=\"" + user + "\"");
			while (rs.next()) {
				String friend = rs.getString("friend_two");
				friends.add(friend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friends;
	}
	
	public ArrayList<Message> getMessages(String user) {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE recipient=\"" + user + "\" AND message_type !=\"announcement\"");
			while (rs.next()) {
				Message msg = new Message(rs.getString("recipient"), rs.getString("sender"), 
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"), 
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"));
				messages.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public void addMessage(Message msg) {
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO message_table VALUES(?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, msg.getTo());
			pstmt.setString(2, msg.getFrom());
			pstmt.setString(3, msg.getMessage());
			pstmt.setString(4, msg.getDateAsString());
			pstmt.setLong(5, msg.getDateAsLong());
			pstmt.setBoolean(6, false);
			pstmt.setString(7, msg.getQuizID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addAnnouncement(Message announcement) {
		addMessage(announcement);
	}
	
	public ArrayList<Message> getAnnouncements(String user) {
		ArrayList<Message> announcements = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM message_table WHERE recipient=\"" + user + "\" AND message_type =\"announcement\"");
			while (rs.next()) {
				Message announcement = new Message(rs.getString("recipient"), rs.getString("sender"),
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"),
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_table WHERE username=\"" + user + "\"");
			if (rs.next()) {
				boolean type = rs.getBoolean("admin");
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
	
	public void addUser(User user) {
		try {
			PreparedStatement pstmt = 
				con.prepareStatement("INSERT INTO message_table VALUES(?, ?, ?, ?)");
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
	
	public boolean checkIfAdmin(String user) {
		Boolean isAdmin = false;
		try {
			ResultSet rs = stmt.executeQuery("SELECT admin FROM user_table WHERE username=\"" + user + "\"");
			if (rs.next() && rs.getBoolean("admin")) {
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
	
	
	public void promoteToAdmin(String user) {
		try {
			stmt.executeUpdate("UPDATE user_table SET admin=\"" + true + "\" WHERE username=\"" + user + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addFriendshipToDB(User friend1, User friend2) {
		try {
			stmt.executeUpdate("INSERT INTO friends_table VALUES(" + friend1.getUsername() + ", " + friend2.getUsername() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePrivacySetting(String user, int privacy) {
		try {
			stmt.executeUpdate("UPDATE user_table SET privacy=\"" + privacy + "\" WHERE username=\"" + user + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearQuizHistory(Quiz quiz) {
		try {
			stmt.executeUpdate("DELETE FROM performance_table WHERE quiz_id=\"" + quiz.getQuizID() + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int totalNumberOfQuizzes() {
		int quizNum = 0;
		try {
			quizNum = stmt.executeUpdate("SELECT COUNT(*) AS row_count FROM quizzes_table");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizNum;
	}
	
	
	
	public int totalNumberOfUsers() {
		int usersNum = 0;
		try {
			usersNum = stmt.executeUpdate("SELECT COUNT(*) AS row_count FROM user_table");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersNum;
	}
	
	public int getNumberQuizzesTaken(String user) {
		int quizNum = 0;
		try {
			quizNum = stmt.executeUpdate("SELECT COUNT(*) AS row_count FROM performance_table WHERE username=\"" + user + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizNum;
	}
	
	
	public int getNumberQuizzesCreated(String user) {
		int quizNum = 0;
		try {
			quizNum = stmt.executeUpdate("SELECT COUNT(*) AS row_count FROM quizzes_table WHERE creator=\"" + user + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizNum;
	}
	
	
	public ArrayList<Message> getRecentMessages(String user) {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT TOP " + NUM_RECENT + " FROM message_table WHERE recipient=\"" + user + "\" AND message_type !=\"announcement\" ORDER BY date_long desc");
			while (rs.next()) {
				Message msg = new Message(rs.getString("recipient"), rs.getString("sender"), 
						rs.getString("message"), rs.getString("date_string"), rs.getLong("date_long"), 
						rs.getBoolean("was_read"), rs.getString("quiz_id"), rs.getString("message_type"));
				messages.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	
	private String generateQuizID() {
		while (true) {
			StringBuilder sb = new StringBuilder(ID_LEN);
			for (int i = 0; i < ID_LEN; i++) {
				sb.append(AB.charAt(rnd.nextInt(AB.length())));
			}
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes_table WHERE quiz_id=\"" + sb.toString() + "\"");
				if (rs != null) return sb.toString();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
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
	
	private class DBConnection {
		private Statement stmt;
		private Connection con;
		
		public static final String MYSQL_USERNAME =  "ccs108rdeubler"; // "ccs108cfoucart";  //
		public static final String MYSQL_PASSWORD =  "vohhaegh"; // "aigookue";  //
		public static final String MYSQL_DATABASE_SERVER = "mysql-user-master.stanford.edu";
		public static final String MYSQL_DATABASE_NAME = "c_cs108_cfoucart";
		
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
