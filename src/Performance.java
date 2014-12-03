package project;

import java.text.SimpleDateFormat;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Performance implements Serializable {
	private String quiz_name;
	private String quiz_id;
	private String user;
	private double score;
	private String date_string;
	private long date_long;
	private String id;
	private ArrayList<Long> time_segments;
	private long current_start = 0;
	private long current_stop = 0;
	private long time_taken = 0;

	
	/**
	 * To make a new Performance object for the first time
	 * @param quiz_name
	 * @param quiz_id
	 * @param user
	 */
	public Performance(Quiz q, String user, double score) {
		this.quiz_name = q.getName();
		this.quiz_id = q.getQuizID();
		this.user = user;
		this.score = score;
		generateID();
		Date dateObj = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss");
		date_string = dateFormat.format(dateObj);
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		date_long = Long.parseLong(dateFormat.format(dateObj));
		time_segments = new ArrayList<Long>();
	}
	
	
	// TODO update database for performance times, as well as doc
	
	/**
	 * To fill a Performance object from table info
	 * @param quiz_name
	 * @param quiz_id
	 * @param user
	 * @param score
	 * @param date
	 */
	public Performance(String quiz_name, String quiz_id, String user, double score,
			String date_string, long date_long, String idmm, long time_taken) {
		this.quiz_name = quiz_name;
		this.quiz_id = quiz_id;
		this.user = user;
		this.score = score;
		this.date_string = date_string;
		this.date_long = date_long;
		this.id = id;
		this.time_taken = time_taken;
	}
	
	// --------------  performance timing ---------------- //
	
	public void startTimer() {
		current_start = System.currentTimeMillis();
	}
	
	public void stopTimer(){
		current_stop = System.currentTimeMillis();
		long time_segment = current_stop - current_start;
		time_segments.add(time_segment);
		sumTimeTaken();
	}
		// Helper method to StopTimer()
		private long sumTimeTaken() {
			time_taken = 0;
			for (int i = 0; i < time_segments.size(); i++){
				time_taken += time_segments.get(i);
			}
			return time_taken;
		}
	
	// ------------------ getters, setters ------------------ //
	
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
	
	public long getDateAsLong() {
		return date_long;
	}
	
	public String getQuizName() {
		return quiz_name;
	}

	public String getQuizID() {
		return quiz_id;
	}
	
	public void generateID() {
		IDGenerator generator = new IDGenerator();
		id = generator.generateID();
	}
	
	public String getID() {
		return id;
	}
	
	//debugging method
	public void setTimeTaken(long l) {
		time_taken = l;
	}
	
	public long getTimeTaken() {
		return time_taken;
	}
	
}
