package project;

import java.io.Serializable;

public class QuizReview implements Serializable {
	private String quiz_id;
	private String username;
	private String review_string;

	public QuizReview(String quiz_id, String username, String review_string) {
		this.quiz_id = quiz_id;
		this.username = username;
		this.review_string = review_string;
	}
	
	public String getQuizID() {
		return quiz_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getReviewText() {
		return review_string;
	}
}
