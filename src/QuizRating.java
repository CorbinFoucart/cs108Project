package project;

import java.io.Serializable;

public class QuizRating implements Serializable {
	private String quiz_id;
	private String username;
	private int rating_val;

	public QuizRating(String quiz_id, String username, int rating_val) {
		this.quiz_id = quiz_id;
		this.username = username;
		this.rating_val = rating_val;
	}
	
	public String getQuizID() {
		return quiz_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getRatingValue() {
		return rating_val;
	}
	
}
