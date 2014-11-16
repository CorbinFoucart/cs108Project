package project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionResponseTest {
	
	private QuestionResponse q1;
	private QuestionResponse q2;

	@Before
	public void setUp() throws Exception {
		String q1Str = "Who was the first President of the U.S.";
		q1 = new QuestionResponse(q1Str);
		q1.addAcceptedAnswer("George Washington");
		q1.addAcceptedAnswer("george washington");
		q1.addAcceptedAnswer("washington");
		
		String q2Str = "Who was the first President of the U.S.";
		q2 = new QuestionResponse(q2Str);
		
	}

	@Test
	public void q1Test() {
		q1.setUserAnswer("George Washington");
		assertEquals(true, q1.isCorrect());
		assertTrue(1 == q1.getPercentage());
		
		q1.setUserAnswer("george washington");
		assertEquals(true, q1.isCorrect());
		assertTrue(1 == q1.getPercentage());
		
		q1.setUserAnswer("washington");
		assertEquals(true, q1.isCorrect());
		assertTrue(1 == q1.getPercentage());
		
		q1.setUserAnswer("meow");
		assertEquals(false, q1.isCorrect());
		assertTrue(0 == q1.getPercentage());
		
//		q1.printPossible();
		
		q1.clearAcceptedAnswers();
		q1.printPossible();
		q1.setUserAnswer("washington");
		assertEquals(false, q1.isCorrect());	
		assertTrue(0 == q1.getPercentage());
		
		assertTrue(q1.getWeight() == 1);
		q1.setWeight(3);
		assertTrue(q1.getWeight() == 3);
	}
	
	@Test
	public void q2Test() {
		assertEquals(false, q2.isCorrect());
		assertTrue(0 == q2.getPercentage());
		
	}

}
