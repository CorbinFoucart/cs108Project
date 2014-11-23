package project;

/**
 * Quiz object contains pages of questions.
 * Note the cursor idea... when you add a page,
 * you have a cursor on that page and can add questions to it.
 * If you need to go back or forward, there are methods for that
 * (turnPage, goBackAPage)
 */

import java.util.*;

public class Quiz {
	private String name;
	private String id;
	private ArrayList<Question> questions;
	private boolean random;
	
	public Quiz(String name, String id, boolean random) {
		this.questions = new ArrayList<Question>();
		this.name = name;
		this.random = random;
		this.id = id;
	}
	
	public void addQuestion(Question q) {
		questions.add(q);
	}
	
	public int getNumQuestions() {
		return questions.size();
	}
	
	public double getPointsPossible() {
		double total = 0;
		for (int i = 0; i < questions.size(); i++) {
			total += questions.get(i).getWeight();
		}
		return total;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Question getQuestion(int i) {
		if (i > 0 && i <= questions.size()) {
			return questions.get(i - 1);
		}
		return null;
	}
	
	public double getQuestionPercentage(int i) {
		if (i > 0 && i <= questions.size()) {
			Question q = questions.get(i - 1);
			return q.getPercentage();
		}
		return 0;
	}
	
	public double getQuestionScore(int i) {
		if (i > 0 && i <= questions.size()) {
			Question q = questions.get(i - 1);
			return q.getPercentage()*q.getWeight();
		}
		return 0;
	}
	
	public double getQuizScore() {
		double total = 0;
		for (int i = 0; i < questions.size(); i++) {
			Question q = questions.get(i);
			total += q.getPercentage()*q.getWeight();
		}
		return total;
	}
	
	public double getQuizPercentage() {
		return getQuizScore()/getPointsPossible();
	}
	
	public String getName() {
		return name;
	}
	
}