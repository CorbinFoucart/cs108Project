package project;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class MatchingAndOtherTest {

	@Test
	public void testingCategoriesTagsAndDelete() {
		DatabasePipeline pipeline = new DatabasePipeline();
		Quiz rebecca_testing = new Quiz("Rebecca's Test Quiz A", true, false, false, "Oreo");
		QuestionResponse q1 = new QuestionResponse("What time is it?");
		q1.addAcceptedAnswer("CS time!");
		q1.addAcceptedAnswer("The time when Rebecca starts to feel jet lag.");
		q1.setWeight(2);
		rebecca_testing.addDescription("Fun times with CS project!");
		rebecca_testing.setCategory("Miscellaneous");
		rebecca_testing.addTag("Rebecca");
		rebecca_testing.addTag("fun");
		rebecca_testing.addQuestion(q1);
		pipeline.addQuizToDB(rebecca_testing);
		
		Quiz rebecca_testing2 = new Quiz("Rebecca's Test Quiz B", false, false, false, "Cookie");
		QuestionMatching q2 = new QuestionMatching("Last Names of Group");
		q2.addMatching("Corbin", "Foucart");
		q2.addMatching("Ariana", "Bhatia");
		q2.addMatching("Emily", "Field");
		q2.addMatching("Rebecca", "Deubler");
		q2.addRightOption("Notingroup");
		q2.addRightOption("Alsonotingroup");
		q2.setWeight(3);
		rebecca_testing2.addDescription("Our CS group is da bomb.");
		rebecca_testing2.setCategory("Miscellaneous");
		rebecca_testing2.addTag("cs group");
		rebecca_testing2.addTag("fun");
		rebecca_testing2.addQuestion(q2);
		pipeline.addQuizToDB(rebecca_testing2);
		
		Quiz rebecca_testing3 = new Quiz("Rebecca's Test Quiz C", false, false, false, "Spot");
		QuestionFillinBlank q3 = new QuestionFillinBlank("Rebecca thinks ", " is a funny site name");
		q3.addAcceptedAnswer("Nontrivial Pursuit");
		q3.setWeight(5);
		rebecca_testing3.addQuestion(q3);
		rebecca_testing3.addDescription("Rebecca likes puns. Maybe too much.");
		rebecca_testing3.setCategory("Entertainment");
		rebecca_testing3.addTag("fun");
		rebecca_testing3.addTag("Rebecca");
		pipeline.addQuizToDB(rebecca_testing3);
		
		ArrayList<String> miscQuizzes = pipeline.getQuizzesInCategory("Miscellaneous");
		String quiz1 = miscQuizzes.get(0);
		String quiz2 = miscQuizzes.get(1);
		String quizz1 = pipeline.getQuizNameFromID(quiz1);
		String quizz2 = pipeline.getQuizNameFromID(quiz2);
		assertEquals(miscQuizzes.size(), 2);
		assertTrue(quizz1.equals("Rebecca's Test Quiz A"));
		assertTrue(quizz2.equals("Rebecca's Test Quiz B"));
		
		ArrayList<String> rebeccaQuizzes = pipeline.getQuizzesWithTag("Rebecca");
		quiz1 = rebeccaQuizzes.get(0);
		quiz2 = rebeccaQuizzes.get(1);
		quizz1 = pipeline.getQuizNameFromID(quiz1);
		quizz2 = pipeline.getQuizNameFromID(quiz2);
		assertEquals(rebeccaQuizzes.size(), 2);
		assertTrue(quizz1.equals("Rebecca's Test Quiz A"));
		assertTrue(quizz2.equals("Rebecca's Test Quiz C"));
		
		ArrayList<String> funQuizzes = pipeline.getQuizzesWithTag("fun");
		quiz1 = funQuizzes.get(0);
		quiz2 = funQuizzes.get(1);
		String quiz3 = funQuizzes.get(2);
		quizz1 = pipeline.getQuizNameFromID(quiz1);
		quizz2 = pipeline.getQuizNameFromID(quiz2);
		String quizz3 = pipeline.getQuizNameFromID(quiz3);
		assertEquals(funQuizzes.size(), 3);
		assertTrue(quizz1.equals("Rebecca's Test Quiz A"));
		assertTrue(quizz2.equals("Rebecca's Test Quiz B"));
		assertTrue(quizz3.equals("Rebecca's Test Quiz C"));
		
		ArrayList<String> csGroupQuizzes = pipeline.getQuizzesWithTag("cs group");
		quiz1 = csGroupQuizzes.get(0);
		quizz1 = pipeline.getQuizNameFromID(quiz1);
		assertEquals(csGroupQuizzes.size(), 1);
		assertTrue(quizz1.equals("Rebecca's Test Quiz B"));
		
		pipeline.removeQuiz(rebecca_testing.getQuizID());
		pipeline.removeQuiz(rebecca_testing2.getQuizID());
		pipeline.removeQuiz(rebecca_testing3.getQuizID());
		
		User fakeUser = new User("Mr. Whiskers", "imacat");
		pipeline.addUser(fakeUser);
		pipeline.removeUser("Mr. Whiskers");
		
		pipeline.closePipeline();
	}
}
