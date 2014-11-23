package project;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import project.*;

public class DatabasePipelineTestToyWorld {
	DatabasePipeline pipeline;
	
	// construct 2 quizzes with questions and add it to the database
	@Test
	public void test1() {
		
		pipeline = new DatabasePipeline();
		
		Quiz quiz1 = new Quiz("Dog Trivia 1", false, false, false, "bo");
		QuestionResponse q1 = new QuestionResponse("Who was the best dog of all time?");
		q1.addAcceptedAnswer("Lassie");
		q1.addAcceptedAnswer("lassie");
		q1.addAcceptedAnswer("LASSIE");
		quiz1.addQuestion(q1);
		
		QuestionResponse q2 = new QuestionResponse("What is the best type of dog treat?");
		q2.addAcceptedAnswer("Beggin Strips");
		q2.addAcceptedAnswer("beggin strips");
		q2.addAcceptedAnswer("BEGGIN STRIPS");
		quiz1.addQuestion(q2);
		
		pipeline.addQuizToDB(quiz1);
		
		String quiz_id = quiz1.getQuizID();
		
		Quiz quizRetrieved = pipeline.retrieveQuizFromDB(quiz_id);
		
		Quiz quiz2 = new Quiz("Dog Trivia 2", false, false, false, "scout");
		QuestionResponse q21 = new QuestionResponse("What color are Dalmations when they are born?");
		q21.addAcceptedAnswer("white");
		q21.addAcceptedAnswer("WHITE");
		q21.addAcceptedAnswer("White");
		quiz2.addQuestion(q21);
		
		QuestionResponse q22 = new QuestionResponse("What breed was Paul McCartney's dog?");
		q22.addAcceptedAnswer("shetland sheepdog");
		q22.addAcceptedAnswer("shetland");
		q22.addAcceptedAnswer("sheepdog");
		quiz2.addQuestion(q22);
		
		QuestionResponse q23 = new QuestionResponse("Who's a good boy? Who's a gooood boy?");
		q23.addAcceptedAnswer("scout is!");
		q23.addAcceptedAnswer("scout");
		q23.addAcceptedAnswer("me");
		quiz2.addQuestion(q23);
		
		pipeline.addQuizToDB(quiz2);
		
		// add dog performances
		Performance p1 = new Performance(quiz1, "bo", 0.92);
		pipeline.addPerformanceToDB(p1);
		
		Performance p2 = new Performance(quiz1, "oski", 0.91);
		pipeline.addPerformanceToDB(p2);
		
		Performance p3 = new Performance(quiz1, "beethoven", 0.88);
		pipeline.addPerformanceToDB(p3);
		
		Performance p4 = new Performance(quiz1, "sarge", 0.40);
		pipeline.addPerformanceToDB(p4);
		
		Performance p5 = new Performance(quiz1, "scout", 0.22);
		pipeline.addPerformanceToDB(p5);
		
		Performance p6 = new Performance(quiz1, "maggie", 0.92);
		pipeline.addPerformanceToDB(p6);
		
		// quiz 2
		Performance p7 = new Performance(quiz2, "air bud", 0.92);
		pipeline.addPerformanceToDB(p7);
		Performance p8 = new Performance(quiz2, "maggie", 0.91);
		pipeline.addPerformanceToDB(p8);
		Performance p9 = new Performance(quiz2, "sarge", 0.88);
		pipeline.addPerformanceToDB(p9);
		Performance p10 = new Performance(quiz2, "sarge", 0.40);
		pipeline.addPerformanceToDB(p10);
		Performance p11 = new Performance(quiz2, "scout", 0.22);
		pipeline.addPerformanceToDB(p11);
		Performance p12 = new Performance(quiz2, "maggie", 0.92);
		pipeline.addPerformanceToDB(p12);
		
		//int boPrivacyResult = pipeline.checkPrivacySettings("bo");
		//System.out.println(boPrivacyResult);

		
		
	}
	

}
