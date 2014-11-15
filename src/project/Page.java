package project;

import java.util.*;

public class Page {
	
	private ArrayList<PageComponent> pageQuestions;
	public int numComponents;
	
	public Page() {
		numComponents = 0;
		pageQuestions = new ArrayList<PageComponent>();
	}
	
	public Question getQuestion(int i) {
		if (i >= pageQuestions.size() || i < 0) return null;
		PageComponent pc = pageQuestions.get(i);
		return pc.q;
	}
	
	public double getScore() {
		if (pageQuestions.isEmpty()) {
			return 0;
		} else {
			int total = 0;
			for (int i = 0; i < pageQuestions.size(); i++) {
				PageComponent pc = pageQuestions.get(i);
				double score = pc.getScore();
				double weight = pc.weight;
				total += score*weight;
			}
			return total;
		}
	}
	
	public double getPercentage() {
		if (numComponents == 0) return 0;
		return getScore()/numComponents;
	}
	
	public void addQuestion(Question Q) {
		PageComponent newComp = new PageComponent(Q);
		pageQuestions.add(newComp);
	}
	
	private class PageComponent {
		
		private int questionNumber;
		private Question q;
		private double score;
		private double weight;
		
		
		public PageComponent(Question q) {
			this.q = q;
			score = 0;
			weight = 1;
			questionNumber = pageQuestions.size();
			numComponents++;
		}
		
		public Object getQuestionType() {
			return q.getClass();
		}
		
		public void changeWeight(double weight) {
			this.weight = weight;
		}
		
		public double getScore() {
			return q.getScore();
		}
		
	}

}
