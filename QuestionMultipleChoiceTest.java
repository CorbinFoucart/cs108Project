package project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionMultipleChoiceTest {

	private QuestionMultipleChoice mc1;
	
	@Before
	public void setUp() throws Exception {
		mc1 =  new QuestionMultipleChoice("Which is the best?");
		mc1.addChoice(false, "Berkeley");
		mc1.addChoice(false, "Drugs");
		mc1.addChoice(true, "CS 108");
		mc1.addChoice(false, "Wii Tennis");
		
		mc1.choose(2);
		assertTrue(1 == mc1.getPercentage());
		
	}

	@Test
	public void test() {
		assertEquals(true, mc1.isCorrect());
		
		mc1.unChoose(2);
		mc1.choose(1);
		assertEquals(false, mc1.isCorrect());
		assertTrue(0 == mc1.getPercentage());
		
		mc1.choose(2);
		mc1.resetUserChoices();
		assertEquals(false, mc1.isCorrect());
		assertTrue(0 == mc1.getPercentage());
		
		mc1.choose(2);
		assertEquals(true, mc1.isCorrect());
		assertTrue(1 == mc1.getPercentage());
		mc1.choose(3);
		assertEquals(false, mc1.isCorrect());
		assertTrue(0 == mc1.getPercentage());
		
		assertTrue(mc1.getWeight() == 1);
		mc1.setWeight(3);
		assertTrue(mc1.getWeight() == 3);
	}

}
