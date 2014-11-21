package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatabasePipelineMethodTest {
	
	private DatabasePipeline pipeline = new DatabasePipeline();

	// creating doggyworld with only the DBpipeline
	// clears the databases and resets them 
	
	@Test
	public void clearDB() {
//		pipeline.clearDatabase();
		
//		// create users
//		User bo = new User("bo", "bobo");
//		pipeline.addUser(bo);
		
		
	}

	
	@Test
	public void setupDoggyWorld() {
		pipeline.clearDatabase();
		
		// create users
		User bo = new User("Bo", "bobo");
		User sarge = new User("Sarge", "sargesarge");
		User scout = new User("Sarge", "sargesarge");
		User beethoven = new User("Sarge", "sargesarge");
		User airBud = new User("Sarge", "sargesarge");
		//User airB = new User("Sarge", "sargesarge");
		//User airBud = new User("Sarge", "sargesarge");
		pipeline.addUser(bo);
		
		
		
	}
	
//	// addQuizToDB()
//	@Test
//	public void addQuizToDBTest() {
//		
//		// create quizzes
//		Quiz quiz1 = new Quiz("Dog Trivia 1", false, false, false, "bo");
//		QuestionResponse q1 = new QuestionResponse("Who was the best dog of all time?");
//		q1.addAcceptedAnswer("Lassie");
//		q1.addAcceptedAnswer("lassie");
//		q1.addAcceptedAnswer("LASSIE");
//		quiz1.addQuestion(q1);
//		
//		QuestionResponse q2 = new QuestionResponse("What is the best type of dog treat?");
//		q2.addAcceptedAnswer("Beggin Strips");
//		q2.addAcceptedAnswer("beggin strips");
//		q2.addAcceptedAnswer("BEGGIN STRIPS");
//		quiz1.addQuestion(q2);
//		
//		Quiz quiz2 = new Quiz("Dog Trivia 2", false, false, false, "scout");
//		QuestionResponse q21 = new QuestionResponse("What color are Dalmations when they are born?");
//		q21.addAcceptedAnswer("white");
//		q21.addAcceptedAnswer("WHITE");
//		q21.addAcceptedAnswer("White");
//		quiz2.addQuestion(q21);
//		
//		QuestionResponse q22 = new QuestionResponse("What breed was Paul McCartney's dog?");
//		q22.addAcceptedAnswer("shetland sheepdog");
//		q22.addAcceptedAnswer("shetland");
//		q22.addAcceptedAnswer("sheepdog");
//		quiz2.addQuestion(q22);
//		
//		QuestionResponse q23 = new QuestionResponse("Who's a good boy? Who's a gooood boy?");
//		q23.addAcceptedAnswer("scout is!");
//		q23.addAcceptedAnswer("scout");
//		q23.addAcceptedAnswer("me");
//		quiz2.addQuestion(q23);
//		
//		// add to DB
//		pipeline.addQuizToDB(quiz1);
//		pipeline.addQuizToDB(quiz2);
//	}
	
	
	// closePipeLine()
	@Test
	public void closePipelineTest() {
		pipeline.closePipeline();
	}
	
}
