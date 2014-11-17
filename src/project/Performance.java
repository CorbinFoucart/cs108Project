package project;

import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Date;

public class Performance implements Serializable {
	private String quiz_name;
	private String quiz_id;
	private String user;
	private double score;
	private String date_string;
	private int date_int;

	
	/**
	 * To make a new Performance object for the first time
	 * @param quiz_name
	 * @param quiz_id
	 * @param user
	 */
	public Performance(String quiz_name, String quiz_id, String user) {
		this.quiz_name = quiz_name;
		this.quiz_id = quiz_id;
		this.user = user;
		Date dateObj = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss");
		date_string = dateFormat.format(dateObj);
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		date_int = Integer.parseInt(dateFormat.format(dateObj));
	}
	
	/**
	 * To fill a Performance object from table info
	 * @param quiz_name
	 * @param quiz_id
	 * @param user
	 * @param score
	 * @param date
	 */
	public Performance(String quiz_name, String quiz_id, String user, double score, String date_string, int date_int) {
		this.quiz_name = quiz_name;
		this.quiz_id = quiz_id;
		this.user = user;
		this.score = score;
		this.date_string = date_string;
		this.date_int = date_int;
	}
	
	/**
	 * Set score of new performance object
	 * @param score
	 */
	public void setScore(double score) {
		this.score = score;
	}
	
	public double getScore() {
		return score;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getDateAsString() {
		return date_string;
	}
	
	public int getDateAsInt() {
		return date_int;
	}
	
	public String getQuizName() {
		return quiz_name;
	}

	public String getQuizID() {
		return quiz_id;
	}
	
}
