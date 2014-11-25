package project;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;


public class DatabasePipelineSummaryPagesTest {

	@Test
	public void testQuizSummary() {
		DatabasePipeline pipeline = new DatabasePipeline();
		String description = pipeline.getQuizDescription("0M084WZV");
		assertTrue(description == null);
		assertTrue(pipeline.getCreator("0M084WZV").equals("Scout"));
		ArrayList<Performance> performances = pipeline.getQuizPerformances("Sarge", "0M084WZV");
		assertTrue(performances.get(0).getScore() == 0.20);
		assertTrue(performances.get(1).getScore() == 0.40);
		assertTrue(performances.get(2).getScore() == 0.82);
		assertTrue(performances.size() == 3);
		performances = pipeline.getHighestUniquePerformances("0M084WZV", 3);
		assertTrue(performances.get(0).getScore() == 0.95);
		assertTrue(performances.get(1).getScore() == 0.82);
		assertTrue(performances.get(2).getScore() == 0.41);
		assertTrue(performances.size() == 3);
		performances = pipeline.getTodaysHighestUniquePerformances("0M084WZV", 3);
		int num_taken = pipeline.getNumTimesQuizTaken("0M084WZV");
		assertEquals(num_taken, 5);
		double avg = pipeline.getQuizAverage("0M084WZV");
		System.out.println(avg);
		pipeline.closePipeline();
	}
	
	@Test
	public void testHomePage() {
		DatabasePipeline pipeline = new DatabasePipeline();
		ArrayList<Message> messages = pipeline.getNNewAnnouncements("Beethoven", 9);
		assertTrue(messages.get(0).getMessage().equals("Note: All cat-related content will be flagged and removed!"));
		assertTrue(messages.get(1).getMessage().equals("Remember to tune into FM 106.3 WOOF tonight!"));
		ArrayList<String> quizzes = pipeline.getNMostPopularQuizzes(2);
		quizzes = pipeline.getNRecentlyCreatedQuizzes(2);
		quizzes = pipeline.getNOwnRecentlyCreatedQuizzes("Sarge", 2);
		ArrayList<Performance> performances = pipeline.getNOwnRecentPerformances("Sarge", 2);
		pipeline.closePipeline();
	}
	
	
	
}
