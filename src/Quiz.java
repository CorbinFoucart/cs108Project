package project;

/**
 * Quiz object contains pages of questions.
 * Note the cursor idea... when you add a page,
 * you have a cursor on that page and can add questions to it.
 * If you need to go back or forward, there are methods for that
 * (turnPage, goBackAPage)
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class Quiz implements Serializable{
	private String name;
	private String id;
	private ArrayList<Question> questions;
	private boolean random;
	private String creator;
	private String date_string;
	private long date_long;
	private String category;
	private ArrayList<String> tags;
	private boolean one_per_page;
	private boolean immediate_feedback;
	
	private static final int ID_LEN = 8;
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	
	public Quiz(String name, boolean random, boolean one_per_page, boolean immediate_feedback, String creator) {
		this.questions = new ArrayList<Question>();
		this.name = name;
		this.random = random;
		this.one_per_page = one_per_page;
		this.immediate_feedback = immediate_feedback;
		this.creator = creator;
		Date dateObj = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss");
		date_string = dateFormat.format(dateObj);
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		date_long = Long.parseLong(dateFormat.format(dateObj));
		category = "";
		tags = new ArrayList<String>();
		generateQuizID();
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
	
	public ArrayList<Question> getAllQuestions() {
		return questions;
	}
	
	/**
	 * NOTE THAT THIS STARTS WITH QUESTION 0
	 * @param i
	 * @return
	 */
	public Question getQuestion(int i) {
		if (i >= 0 && i < questions.size()) {
			return questions.get(i);
		}
		return null;
	}
	
	/**
	 * NOTE THAT THIS STARTS WITH QUESTION 0
	 * @param i
	 * @return
	 */
	public double getQuestionPercentage(int i) {
		if (i >= 0 && i < questions.size()) {
			Question q = questions.get(i);
			return q.getPercentage();
		}
		return 0;
	}
	
	/**
	 * NOTE THAT THIS STARTS WITH QUESTION 0
	 * @param i
	 * @return
	 */
	public double getQuestionScore(int i) {
		if (i >= 0 && i < questions.size()) {
			Question q = questions.get(i);
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
	
	public void setQuizID(String id) {
		this.id = id;
	}
	
	public String getQuizID() {
		return id;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public String getDateAsString() {
		return date_string;
	}
	
	public long getDateAsLong() {
		return date_long;
	}
	
	public void setCategory(String cat) {
		category = cat;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public void removeTag(String tag) {
		tags.remove(tag);
	}
	
	public String getTagsString() {
		String str = "";
		for (int i = 0; i < tags.size(); i++) {
			str += tags.get(i);
			str += "/";
		}
		return str;
	}
	
	public ArrayList<String> getTagsArray() {
		return tags;
	}
	
	public boolean isRandom() {
		return random;
	}
	
	public boolean isOnePerPage() {
		return one_per_page;
	}
	
	public boolean isImmediateFeedback() {
		return immediate_feedback;
	}
	
	public void shuffleQuiz() {
		Collections.shuffle(questions);
	}
	
	public void generateQuizID() {
		StringBuilder sb = new StringBuilder(ID_LEN);
		for (int i = 0; i < ID_LEN; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		this.id = sb.toString();
	}
	
}