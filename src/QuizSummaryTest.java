package project;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;


public class QuizSummaryTest {
	
	@Test
	public void test1() {
		QuizSummary summary = new QuizSummary("0M084WZV", "Sarge");
		String description = summary.getQuizDescription();
		assertTrue(description == null);
		assertTrue(summary.getQuizCreator().equals("Scout"));
		ArrayList<Performance> performances = summary.getUsersPastQuizPerformance();
	}
}
