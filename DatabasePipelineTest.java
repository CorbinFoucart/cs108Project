package project;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import project.*;

public class DatabasePipelineTest {
	DatabasePipeline pipeline;
	
	@Test
	public void test1() {
		pipeline = new DatabasePipeline();
		QuestionResponse ques1 = new QuestionResponse("Does this work?");
		ques1.addAcceptedAnswer("Let's hope so.");
		ques1.setQuizID("none");
		pipeline.addQuestionToDB(ques1);
		QuestionResponse result = (QuestionResponse) pipeline.retrieveQuestionFromDB("Does this work?");
		assertTrue(result.getQuestion().equals("Does this work?"));
	}
	
	public void test2() {
		QuestionResponse ques1 = new QuestionResponse("Does this work?");
		ques1.addAcceptedAnswer("Let's hope so.");
		QuestionResponse ques2 = new QuestionResponse("OK does this work on quizzes?");
		ques2.addAcceptedAnswer("Let's REALLY hope so.");
	}
}
