package project;

import java.io.Serializable;

public interface Question {

	/**
	 * getPercentage() always returns a double between 0 and 1
	 * indicating a percentage that the user earned on a
	 * particular question.
	 * 
	 * Weighting of each question is handled in the page 
	 * class. 
	 * 
	 */
	public double getPercentage();
	
	/**
	 * getQuestion() always returns a string specifying the
	 * question. 
	 * 
	 * For QuestionResponse, MultiAnswerQuestion, 
	 * QuestionMultipleChoice, and QuestionFillInBlank
	 * objects, returns a string of the question asked.
	 * 
	 * For pictureQuestion, returns a string of the image
	 * filename associated with the question.
	 * 
	 */
	public String getQuestion();
	
	public String getClassName();
	
	public double getWeight();
	
	public String getQuizID();
	
	public void setQuizID(String id);
	
}
