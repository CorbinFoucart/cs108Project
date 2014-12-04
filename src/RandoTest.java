package project;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class RandoTest {

	@Test
	public void testQuestions() {
		DatabasePipeline pipeline = new DatabasePipeline();
		Quiz emily_quiz = pipeline.retrieveQuizFromDB("XO9BOOLO");
		
		QuestionMultipleChoice q1 = (QuestionMultipleChoice) emily_quiz.getQuestion(0);
		QuestionMatching q5 = (QuestionMatching) emily_quiz.getQuestion(4);
		ArrayList<String> leftoptions = q5.getLeftOptions();
		for (int i = 0; i < leftoptions.size(); i++) {
			System.out.println(leftoptions.get(i));
		}
		System.out.println("---------");
		
		ArrayList<String> rightoptions = q5.getRightOptions();
		for (int i = 0; i < rightoptions.size(); i++) {
			System.out.println(rightoptions.get(i));
		}
		System.out.println("---------");
		
		ArrayList<String> correctanswers = q5.getCorrectAnswers();
		for (int i = 0; i < correctanswers.size(); i++) {
			System.out.println(correctanswers.get(i));
		}
		System.out.println("---------");
	}
	
}
