package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnswerComparatorTest {

	// ex1 isolation test cases
	@Test
	public void test1() {
		String accepted = "woody";
		
		//true
		String usr1 = "woody";
		String usr2 = "woodyy";
		String usr3 = "wooody";
		String usr4 = "wwoody";
		
		// false
		String usr5 = "woooody";
		String usr6 = "noody";
		String usr7 = "";
		String usr8 = "Wooody";
		
		AnswerComparator ac = new AnswerComparator(true, false, true);
		
		assertEquals(true, ac.oneCharacterOff(accepted, usr1));
		assertEquals(true, ac.oneCharacterOff(accepted, usr2));
		assertEquals(true, ac.oneCharacterOff(accepted, usr3));
		assertEquals(true, ac.oneCharacterOff(accepted, usr4));
		
		assertEquals(false, ac.oneCharacterOff(accepted, usr5));
		assertEquals(false, ac.oneCharacterOff(accepted, usr6));
		assertEquals(false, ac.oneCharacterOff(accepted, usr7));
		assertEquals(false, ac.oneCharacterOff(accepted, usr8));
	}
	
	// ex2 isolation test cases, case doesn't matter
	@Test
	public void test2() {
		String accepted = "woody";
		
		//true
		String usr1 = "woody";
		String usr2 = "wooly";
		String usr3 = "woodi";
		String usr4 = "qoody";
		
		// false
		String usr5 = "woooody";
		String usr6 = "wody";
		String usr7 = "";
		String usr8 = "dooly";
		String usr9 = "Woody"; //should return true; one letter can fix this
		String usr10 = "WOODY"; //should return false; case matters here
		
		AnswerComparator ac = new AnswerComparator(false, true, true);
		
		assertEquals(true, ac.oneCharacterOff(accepted, usr1));
		assertEquals(true, ac.oneCharacterOff(accepted, usr2));
		assertEquals(true, ac.oneCharacterOff(accepted, usr3));
		assertEquals(true, ac.oneCharacterOff(accepted, usr4));
		
		assertEquals(false, ac.oneCharacterOff(accepted, usr5));
		assertEquals(false, ac.oneCharacterOff(accepted, usr6));
		assertEquals(false, ac.oneCharacterOff(accepted, usr7));	
		assertEquals(false, ac.oneCharacterOff(accepted, usr8));
		assertEquals(true, ac.oneCharacterOff(accepted, usr9));
		assertEquals(false, ac.oneCharacterOff(accepted, usr10));
	}
	
	// case matters isolation test cases
	@Test
	public void test3() {
		String accepted = "woody";
		
		//true
		String usr1 = "woody";
		
		// false
		String usr2 = "WoodY";
		String usr3 = "wooodi";
		String usr4 = "qoody";
		String usr5 = "WOODY";
		String usr6 = "wody";
		String usr7 = "";
		String usr8 = "wOody";
		
		AnswerComparator ac = new AnswerComparator(false, false, true);
		
		assertEquals(true, ac.oneCharacterOff(accepted, usr1));
		assertEquals(false, ac.oneCharacterOff(accepted, usr2));
		assertEquals(false, ac.oneCharacterOff(accepted, usr3));
		assertEquals(false, ac.oneCharacterOff(accepted, usr4));
		
		assertEquals(false, ac.oneCharacterOff(accepted, usr5));
		assertEquals(false, ac.oneCharacterOff(accepted, usr6));
		assertEquals(false, ac.oneCharacterOff(accepted, usr7));	
		assertEquals(false, ac.oneCharacterOff(accepted, usr8));	
	}
	
	
	// case doesn't matter isolation test cases
	@Test
	public void test4() {
		String accepted = "beethoven";
		
		//true
		String usr1 = "beethoven";
		String usr2 = "BEETHOVEN";
		String usr3 = "bEETHOveN";
		String usr4 = "BEEThoven";
		String usr5 = "beethoveN";
		String usr6 = "Beethoven";
		String usr7 = "";
		String usr8 = "meow";
		
		AnswerComparator ac = new AnswerComparator(false, false, false);
		
		assertEquals(true, ac.oneCharacterOff(accepted, usr1));
		assertEquals(true, ac.oneCharacterOff(accepted, usr2));
		assertEquals(true, ac.oneCharacterOff(accepted, usr3));
		assertEquals(true, ac.oneCharacterOff(accepted, usr4));
		
		assertEquals(true, ac.oneCharacterOff(accepted, usr5));
		assertEquals(true, ac.oneCharacterOff(accepted, usr6));
		assertEquals(false, ac.oneCharacterOff(accepted, usr7));	
		assertEquals(false, ac.oneCharacterOff(accepted, usr8));	
	}
	
	
	
	

}
