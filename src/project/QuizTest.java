package project;

import static org.junit.Assert.*;

import org.junit.*;

public class QuizTest {
	
	// test with no questions
	@Test
	public void test1() {
		Quiz quiz1 = new Quiz("Books I Like", "", false);
		assertTrue(quiz1.getQuizScore() == 0);
		assertEquals(0, quiz1.getNumQuestions());
	}
	
	
	// test with 2 questions, unweighted, pre-set user answers
	@Test
	public void test3() {
		Quiz quiz1 = new Quiz("Books I Like", "", false);
		QuestionMultipleChoice q1 = new QuestionMultipleChoice("What is Rebecca's favorite book?");
		q1.addChoice(false, "The Scarlet Letter");
		q1.addChoice(true, "Atonement");
		q1.addChoice(false, "Rebecca");
		q1.choose(1);
		
		QuestionResponse q2 = new QuestionResponse("When does Rebecca have time to read books anymore?");
		q2.addAcceptedAnswer("Never. It's sad.");
		q2.setUserAnswer("All the time!");
		
		quiz1.addQuestion(q1);
		assertTrue(1 == quiz1.getPointsPossible());
		quiz1.addQuestion(q2);
		assertTrue(2 == quiz1.getPointsPossible());
		assertEquals(2, quiz1.getNumQuestions());		// all score getting in a row
		assertTrue(1.0 == quiz1.getQuizScore());
		
		assertTrue(1.0 == quiz1.getQuizScore());		// no getting page score, just totals
		assertTrue(0.5 == quiz1.getQuizPercentage());
		
		assertTrue(1.0 == quiz1.getQuizScore());		// checking if they each work independently
		assertTrue(0.5 == quiz1.getQuizPercentage());
		
		assertTrue(1 == quiz1.getQuestionPercentage(1));
		assertTrue(0 == quiz1.getQuestionPercentage(2));
		assertTrue(1 == quiz1.getQuestionScore(1));
		assertTrue(0 == quiz1.getQuestionScore(2));
	}
	
	// 2 questions, weighted, later added user answers
	@Test
	public void test4() {
		Quiz quiz1 = new Quiz("Books I Like", "", false);
		QuestionMultipleChoice q1 = new QuestionMultipleChoice("What is Rebecca's favorite book?");
		q1.addChoice(false, "The Scarlet Letter");
		q1.addChoice(true, "Atonement");
		q1.addChoice(false, "Rebecca");
		q1.setWeight(2);
		
		QuestionResponse q2 = new QuestionResponse("When does Rebecca have time to read books anymore?");
		q2.addAcceptedAnswer("Never. It's sad.");
		q2.setWeight(3);
		
		quiz1.addQuestion(q1);
		assertTrue(2 == quiz1.getPointsPossible());
		quiz1.addQuestion(q2);
		
		assertTrue(5 == quiz1.getPointsPossible());
		assertEquals(2, quiz1.getNumQuestions());
		assertTrue(0 == quiz1.getQuizScore());
		
		((QuestionMultipleChoice) quiz1.getQuestion(1)).choose(1);
		((QuestionResponse) quiz1.getQuestion(2)).setUserAnswer("All the time!");
		assertTrue(5 == quiz1.getPointsPossible());
		assertEquals(2, quiz1.getNumQuestions());		// all score getting in a row
		assertTrue(2.0 == quiz1.getQuizScore());
		
		assertTrue(2.0 == quiz1.getQuizScore());		// no getting page score, just totals
		assertTrue(0.4 == quiz1.getQuizPercentage());
		
		assertTrue(1 == quiz1.getQuestionPercentage(1));
		assertTrue(0 == quiz1.getQuestionPercentage(2));
		assertTrue(2 == quiz1.getQuestionScore(1));
		assertTrue(0 == quiz1.getQuestionScore(2));
	}
	
	// testing 3 kinds of questions, weighting, late addition of answers
	@Test
	public void test5() {
		Quiz quiz1 = new Quiz("Rebecca and Books", "", false);
		QuestionMultipleChoice q1 = new QuestionMultipleChoice("What is Rebecca's favorite book?");
		q1.addChoice(false, "The Scarlet Letter");
		q1.addChoice(true, "Atonement");
		q1.addChoice(false, "Rebecca");
		q1.setWeight(2);
		
		QuestionResponse q2 = new QuestionResponse("When does Rebecca have time to read books anymore?");
		q2.addAcceptedAnswer("Never. It's sad.");
		q2.setWeight(3);
		
		MultiAnswerQuestion q3 = new MultiAnswerQuestion("List 4 books Rebecca read as a child:", false, 4);
		q3.addAcceptedAnswer("Harry Potter and the Sorcerer's Stone");
		q3.addAcceptedAnswer("Phantom Tollbooth");
		q3.addAcceptedAnswer("Anne of Green Gables");
		q3.addAcceptedAnswer("The Last of the Really Great Wangdoodles");
		q3.addAcceptedAnswer("Lord of the Flies");
		q3.setWeight(5);
		
		quiz1.addQuestion(q1);
		assertTrue(2 == quiz1.getPointsPossible());
		quiz1.addQuestion(q2);
		assertTrue(5 == quiz1.getPointsPossible());
		quiz1.addQuestion(q3);
		assertTrue(10 == quiz1.getPointsPossible());

		assertEquals(3, quiz1.getNumQuestions());
		assertTrue(0 == quiz1.getQuizScore());
		
		for (int i = 1; i <= 3; i++) {
			Question q = quiz1.getQuestion(i);
			String type = q.getClassName();
			if (type.equals("MultiAnswerQuestion")){
				MultiAnswerQuestion quest1 = (MultiAnswerQuestion) q;
				quest1.setUserAnswer("The Last of the Really Great Wangdoodles");
				quest1.setUserAnswer("Harry Potter and the Sorcerer's Stone");
				quest1.setUserAnswer("Twilight");
			} else if (type.equals("QuestionResponse")) {
				QuestionResponse quest2 = (QuestionResponse) q;
				quest2.setUserAnswer("All the time!");
			} else {
				QuestionMultipleChoice quest3 = (QuestionMultipleChoice) q;
				quest3.choose(1);
			}
		}
		assertTrue(10 == quiz1.getPointsPossible());
		assertEquals(3, quiz1.getNumQuestions());		// all score getting in a row
		assertTrue(4.5 == quiz1.getQuizScore());
		assertTrue(0.45 == quiz1.getQuizPercentage());
		
		assertTrue(1 == quiz1.getQuestionPercentage(1));
		assertTrue(0 == quiz1.getQuestionPercentage(2));
		assertTrue(0.5 == quiz1.getQuestionPercentage(3));
		assertTrue(2 == quiz1.getQuestionScore(1));
		assertTrue(0 == quiz1.getQuestionScore(2));
		assertTrue(2.5 == quiz1.getQuestionScore(3));
	}
	
}

