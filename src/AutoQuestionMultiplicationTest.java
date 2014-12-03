package project;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class AutoQuestionMultiplicationTest {

	// General test of methods, functionality
	@Test
	public void test() {
		AutoQuestionMultiplication aqm = new AutoQuestionMultiplication(2,3);
		
		// test of auto generating int ability
		int n = 9;
		for (int i = 0; i < n; i++) {
			int result = aqm.createRandomNDigitInt(i + 1);
			System.out.println(result);
		}
		System.out.println("\n");
		
		int numberSampleQuestions = 10;
		for (int i = 0; i < numberSampleQuestions; i++) {
			int n1Digits = aqm.generateRndIntInRange(1, 4);
			int n2Digits = aqm.generateRndIntInRange(1, 4);
			AutoQuestionMultiplication aqmDisplay = new AutoQuestionMultiplication(n1Digits, n2Digits);
//			AutoQuestionMultiplication aqmDisplay = new AutoQuestionMultiplication(2,2);
			
			String ti = aqmDisplay.getTopInt();
			String bi = aqmDisplay.getBottomInt();
			String answer = aqmDisplay.getAnswer();
			
			int numSpaces = ti.length() - bi.length();
			char[] chars = new char[numSpaces];
			Arrays.fill(chars, ' ');
			String spaceStr = new String(chars);
			
			String topString = "  " + ti;
			String bottomString = "x " + spaceStr + bi;
			
			int maxStringLen = Math.max(answer.length(), topString.length());
			
			char[] ABchars = new char[maxStringLen];
			Arrays.fill(ABchars, '-');
			String answerBarStr = new String(ABchars);
			
			String scoreBefore = "Score before: " + aqmDisplay.getPercentage();
			aqmDisplay.setUserAnswer(answer);
			String scoreAfter = "Score after: " + aqmDisplay.getPercentage();
			
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
			AutoQuestionMultiplication aqm = new AutoQuestionMultiplication(2,2);
			q1.addQuestion(aqm);
		}
		
		
	}

}
