package project;

import java.io.*;
import java.sql.*;
import java.util.Random;

import javax.sql.rowset.serial.SerialBlob;

import project.*;


public class DatabasePipeline {
	private DBConnection db_con;
	private Connection con;
	private Statement stmt;
	
	private static final int ID_LEN = 8;
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
			Question q = quiz.getQuestion(i + 1);
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
			pstmt.setInt(6, perf.getDateAsInt());
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
	
	
	public Blob blobify(Object obj) {
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
		
		public static final String MYSQL_USERNAME = "ccs108rdeubler";
		public static final String MYSQL_PASSWORD = "vohhaegh";
		public static final String MYSQL_DATABASE_SERVER = "mysql-user-master.stanford.edu";
		public static final String MYSQL_DATABASE_NAME = "c_cs108_rdeubler";
		
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
