package project;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class AutoQuestionSubtractionTest {

	// General test of methods, functionality
	@Test
	public void test() {
		AutoQuestionSubtraction aqs = new AutoQuestionSubtraction(2,3);
		
		// test of auto generating int ability
		int n = 9;
		for (int i = 0; i < n; i++) {
			int result = aqs.createRandomNDigitInt(i + 1);
			System.out.println(result);
		}
		System.out.println("\n");
		
		int numberSampleQuestions = 10;
		for (int i = 0; i < numberSampleQuestions; i++) {
			int n1Digits = aqs.generateRndIntInRange(1, 4);
			int n2Digits = aqs.generateRndIntInRange(1, 4);
			AutoQuestionSubtraction aqsDisplay = new AutoQuestionSubtraction(n1Digits, n2Digits);
			
			String ti = aqsDisplay.getTopInt();
			String bi = aqsDisplay.getBottomInt();
			String answer = aqsDisplay.getAnswer();
			
			int numSpaces = ti.length() - bi.length();
			char[] chars = new char[numSpaces];
			Arrays.fill(chars, ' ');
			String spaceStr = new String(chars);
			
			String topString = "  " + ti;
			String bottomString = "- " + spaceStr + bi;
			
			int maxStringLen = Math.max(answer.length(), topString.length());
			
			char[] ABchars = new char[maxStringLen];
			Arrays.fill(ABchars, '-');
			String answerBarStr = new String(ABchars);
			
			String scoreBefore = "Score before: " + aqsDisplay.getPercentage();
			aqsDisplay.setUserAnswer(answer);
			String scoreAfter = "Score after: " + aqsDisplay.getPercentage();
			
			System.out.println(topString);
			System.out.println(bottomString);
			System.out.println(answerBarStr);
			System.out.println(answer);
			System.out.println("\n");
			System.out.println(scoreBefore);
			System.out.println(scoreAfter);
			System.out.println("\n");
			
						
		}
			
	}
	
	// test to see if this question type works in a quiz
	@Test
	public void quizTest() {
		Quiz q1 = new Quiz("RoboThirdGrade", false, false, false, "Catface Meowmers");
		
		int nQuestions = 5;
		for (int i = 0; i < nQuestions; i++) {
			AutoQuestionAddition aqs1 = new AutoQuestionAddition(2,2);
			q1.addQuestion(aqs1);
		}
		
		
	}

}
