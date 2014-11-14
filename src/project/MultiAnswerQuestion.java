package project;

import java.util.*;

import project.QuestionResponse.Answer;

public class MultiAnswerQuestion {
	
	private String question;
	private boolean ordered;
	private ArrayList<String> userAnswers;
	private ArrayList<Answer> acceptedAnswers;
	private int numCorrect;
	
	/**
	 * Constructor for multi-answer question.
	 * 
	 * @param ordered - user determines if order matters in
	 * answering the questions.
	 */
	public MultiAnswerQuestion(boolean ordered, String question) {
		this.ordered = ordered;
		this.question = question;
		this.userAnswers = new ArrayList<String>();
		this.acceptedAnswers = new ArrayList<Answer>();
		this.numCorrect = 0;
	}
	
	public void addAcceptedAnswer(String ans) {
		Answer a  = new Answer(ans);
		acceptedAnswers.add(a);
	}
	

	public void setUserAnswer(String userAns) {
		userAnswers.add(userAns);
	}
	
	
	public boolean getScore() {
		for (int i = 0; i < userAnswers.size(); i++) {
			if (ordered) {
				if (acceptedAnswers.size() > i && 
						userAnswers.get(i).equals(acceptedAnswers.get(i).answer)) {
					numCorrect++;
				}
			} else {
				for (int j = 0; j < acceptedAnswers.size(); j++) {
					if (userAnswers.get(i).equals(acceptedAnswers.get(j).answer) 
							&& acceptedAnswers.get(j).correct) {
								numCorrect++;
								acceptedAnswers.get(j).correct = true;
							}
				}
			}
		}
		
		
		return false;
	}
	
	
	
	
	// ---- answer inner array ----- //
	
	private class Answer {
		
		private boolean correct;
		private String answer;
		
		
		public Answer(String ans) {
			answer = ans;
			correct = false;
		}
		
		public boolean equals(Answer other) {
			// LATER need 1 step away comparator etc
			
			// for now, just use string matching
			return (other.answer.equals(answer));
		}
		
		public String toString() {
			return answer;
		}
		
		 
		
	}
	
}
