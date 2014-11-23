package project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MultiAnswerQuestionTest {

	private MultiAnswerQuestion q1;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	// simple test, order does not matter
	public void q1test() {
		String q1Str = "List the 5 only types of bread worth knowing about";
		boolean q1_Ordered = false;
		String[] breads = { "Pumpernickel", 
				"Potato Bread", 
				"Subway's Italian Bread", 
				"Portuguese Sweet Bread",
				"Sourdough"};
		q1 = new MultiAnswerQuestion(q1Str, q1_Ordered, breads.length);

		
		q1.addAcceptedAnswer(breads[0]);
		q1.addAcceptedAnswer(breads[1]);
		q1.addAcceptedAnswer(breads[2]);
		q1.addAcceptedAnswer(breads[3]);
		q1.addAcceptedAnswer(breads[4]);
		
		assertTrue(q1.getWeight() == 1);
		q1.setWeight(3);
		assertTrue(q1.getWeight() == 3);
		
		// 1 correct answer
		q1.setUserAnswer(breads[0]);
		q1.tallyAnswers();
		assertEquals(1, q1.numCorrect);
		assertTrue(0.2 == q1.getPercentage());
		q1.clearUserAnswers();
		
		q1.setUserAnswer(breads[1]);
		q1.tallyAnswers();
		assertEquals(1, q1.numCorrect);
		assertTrue(0.2 == q1.getPercentage());
		q1.clearUserAnswers();
		
		// 2 correct answers
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[1]);
		q1.tallyAnswers();
		assertEquals(2, q1.numCorrect);
		assertTrue(0.4 == q1.getPercentage());
		q1.clearUserAnswers();

		// same correct answer
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[1]);
		q1.tallyAnswers();
		assertEquals(2, q1.numCorrect);
		assertTrue(0.4 == q1.getPercentage());
		q1.clearUserAnswers();
		
		// all correct answers
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[1]);
		q1.setUserAnswer(breads[2]);
		q1.setUserAnswer(breads[3]);
		q1.setUserAnswer(breads[4]);
		q1.tallyAnswers();
		assertEquals(5, q1.numCorrect);
		assertTrue(1 == q1.getPercentage());
		q1.clearUserAnswers();
		
		// 1 right one wrong
		q1.setUserAnswer("Foccacia");
		q1.setUserAnswer(breads[1]);
		q1.tallyAnswers();
		assertEquals(1, q1.numCorrect);
		assertTrue(0.2 == q1.getPercentage());
		q1.clearUserAnswers();
		
		// all wrong
		q1.setUserAnswer("Foccacia");
		q1.setUserAnswer("Wunderbread");
		q1.setUserAnswer("");
		q1.setUserAnswer("Sucky Bread");
		q1.setUserAnswer("Dry Rolls");
		q1.tallyAnswers();
		assertEquals(0, q1.numCorrect);
		assertTrue(0 == q1.getPercentage());
		q1.clearUserAnswers();
		
		// no answers
		q1.tallyAnswers();
		assertEquals(0, q1.numCorrect);
		assertTrue(0 == q1.getPercentage());
		q1.clearUserAnswers();
		
		// this SHOULD be prevented by number of boxes
		// add more answers than are allowed
		// all correct answers
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[1]);
		q1.setUserAnswer(breads[2]);
		q1.setUserAnswer(breads[3]);
		q1.setUserAnswer(breads[4]);
		q1.setUserAnswer("Foccacia");
		q1.setUserAnswer("Hawaii Toast");
		q1.tallyAnswers();
		assertEquals(5, q1.numCorrect);
		assertTrue(1.0 == q1.getPercentage());
		q1.clearUserAnswers();	
		assertTrue(0 == q1.getPercentage());

		
	}
	
	// testing order matters
	@Test
	public void orderTest() {
		String qstr = "List the last three presidents who had IQs below 80, in reverse order";
		String[] answers = {"George W. Bush", "Ronald Reagan", "Warren G. Harding"};
		MultiAnswerQuestion q2 = new MultiAnswerQuestion(qstr, true, answers.length);
		q2.addAcceptedAnswer("George W. Bush");
		q2.addAcceptedAnswer("Ronald Reagan");
		q2.addAcceptedAnswer("Warren G. Harding");
		
		q2.setUserAnswer(answers[0]);
		q2.setUserAnswer(answers[1]);
		q2.setUserAnswer(answers[2]);
		System.out.println(q2.getPercentage());
		assertEquals(3, q2.numCorrect);

		assertTrue(1.0 == q2.getPercentage());
	}
	

}
