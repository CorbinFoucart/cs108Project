package project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PageTest {


	@Test
	public void test1() {
		Page page1 = new Page();
		
		String q1Str = "What is the best mollusk?";
		QuestionResponse q1 = new QuestionResponse(q1Str);
		q1.addAcceptedAnswer("Nautilus");
		q1.setUserAnswer("Octopus");
		page1.addQuestion(q1);
		//System.out.println(page1.getQuestion(0).getClass().toString());
		
		String q2Str = "What is the best log?";
		QuestionResponse q2 = new QuestionResponse(q2Str);
		q1.addAcceptedAnswer("Birch");
		page1.addQuestion(q2);
	}
	
	// no questions
	@Test	
	public void test2() {
		Page page2 = new Page();
		assertTrue(0 == page2.getScore());
		assertTrue(0 == page2.getPercentage());
		assertTrue(null == page2.getQuestion(1));
	}
	
	// all wrong
	@Test
	public void test3() {
		Page page3 = new Page();
		
		String q1Str = "What is the best mollusk?";
		QuestionResponse q1 = new QuestionResponse(q1Str);
		q1.addAcceptedAnswer("Nautilus");
		q1.setUserAnswer("Octopus");
		page3.addQuestion(q1);
		
		String q2Str = "What is the best log?";
		QuestionResponse q2 = new QuestionResponse(q2Str);
		q2.addAcceptedAnswer("Birch");
		page3.addQuestion(q2);
		q2.setUserAnswer("Lincoln Log");
		
		assertTrue(0 == page3.getScore());
		assertTrue(0 == page3.getPercentage());
		System.out.println(page3.getPercentage());
	}
	
	// a correct answer, all same kind of question
	@Test
	public void test4() {
		Page page4 = new Page();
		System.out.println(page4.numComponents);
		String q1Str = "What is the best mollusk?";
		QuestionResponse q1 = new QuestionResponse(q1Str);
		q1.addAcceptedAnswer("Nautilus");
		q1.setUserAnswer("Octopus");
		page4.addQuestion(q1);
		
		String q2Str = "What is the best log?";
		QuestionResponse q2 = new QuestionResponse(q2Str);
		q2.addAcceptedAnswer("Birch");
		page4.addQuestion(q2);
		q2.setUserAnswer("Birch");
		
		System.out.println(page4.numComponents);
		assertTrue(1 == page4.getScore());
		System.out.println(page4.getPercentage());
	}
	
	@Test
	public void test5() {
		Page page5 = new Page();
		String str1 = "List 5 dumb yoga positions.";
		
	}

}
