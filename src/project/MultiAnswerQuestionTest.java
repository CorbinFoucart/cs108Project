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
		q1 = new MultiAnswerQuestion(q1Str, q1_Ordered);
		String[] breads = { "Pumpernickel", 
							"Potato Bread", 
							"Subway's Italian Bread", 
							"Portuguese Sweet Bread",
							"Sourdough"};
		
		q1.addAcceptedAnswer(breads[0]);
		q1.addAcceptedAnswer(breads[1]);
		q1.addAcceptedAnswer(breads[2]);
		q1.addAcceptedAnswer(breads[3]);
		q1.addAcceptedAnswer(breads[4]);
		
		// 1 correct answer
		q1.setUserAnswer(breads[0]);
		q1.tallyScore();
		assertEquals(1, q1.numCorrect);
		q1.clearUserAnswers();
		
		q1.setUserAnswer(breads[1]);
		q1.tallyScore();
		assertEquals(1, q1.numCorrect);
		q1.clearUserAnswers();
		
		// 2 correct answers
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[1]);
		q1.tallyScore();
		assertEquals(2, q1.numCorrect);
		q1.clearUserAnswers();

		// same correct answer
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[1]);
		q1.tallyScore();
		assertEquals(2, q1.numCorrect);
		q1.clearUserAnswers();
		
		// all correct answers
		q1.setUserAnswer(breads[0]);
		q1.setUserAnswer(breads[1]);
		q1.setUserAnswer(breads[2]);
		q1.setUserAnswer(breads[3]);
		q1.setUserAnswer(breads[4]);
		q1.tallyScore();
		assertEquals(5, q1.numCorrect);
		q1.clearUserAnswers();
		
		// 1 right one wrong
		q1.setUserAnswer("Foccacia");
		q1.setUserAnswer(breads[1]);
		q1.tallyScore();
		assertEquals(1, q1.numCorrect);
		q1.clearUserAnswers();
		
		// all wrong
		q1.setUserAnswer("Foccacia");
		q1.setUserAnswer("Wunderbread");
		q1.setUserAnswer("");
		q1.setUserAnswer("Sucky Bread");
		q1.setUserAnswer("Dry Rolls");
		q1.tallyScore();
		assertEquals(0, q1.numCorrect);
		q1.clearUserAnswers();
		
		// no answers
		q1.tallyScore();
		assertEquals(0, q1.numCorrect);
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
		q1.setUserAnswer("Poop bread");
		q1.tallyScore();
		assertEquals(5, q1.numCorrect);
		q1.clearUserAnswers();	

		
	}

}
