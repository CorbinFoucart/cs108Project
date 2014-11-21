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
		// test addUser()
		User bo = new User("Bo", "bobo");
		User sarge = new User("Sarge", "sargesarge");
		User scout = new User("Scout", "scoutscout");
		User beethoven = new User("Beethoven", "beethovenbeethoven");
		User airBud = new User("Air Bud", "airbudairbud");
		User maggie = new User("Maggie", "maggiemaggie");
		User oski = new User("Oski", "oskioski");
		User noNameTest = new User("", ""); // handle this?
		pipeline.addUser(bo);
		pipeline.addUser(sarge);
		pipeline.addUser(scout);
		pipeline.addUser(beethoven);
		pipeline.addUser(airBud);
		pipeline.addUser(maggie);
		pipeline.addUser(oski);
		pipeline.addUser(noNameTest);
		
		// promote sarge to admin
		// tests romoteToAdmin(), demoteFromAdmin()
		// two weird names don't exist
		pipeline.promoteToAdmin("sarge");
		pipeline.promoteToAdmin("askmaksc");
		pipeline.promoteToAdmin("airBud");
		pipeline.demoteFromAdmin("airBud");
		pipeline.demoteFromAdmin("ancakmsckla");
		
		// add friend connections
		// tests addFriendshiptoDB()
		pipeline.addFriendshipToDB(bo, sarge);
		pipeline.addFriendshipToDB(bo, scout);
		pipeline.addFriendshipToDB(scout, sarge);
		pipeline.addFriendshipToDB(scout, beethoven);
		pipeline.addFriendshipToDB(beethoven, bo);
		pipeline.addFriendshipToDB(beethoven, sarge);
		pipeline.addFriendshipToDB(beethoven, airBud);
		pipeline.addFriendshipToDB(maggie, oski);
		
		// update privacy settings for dogs
		// tests updatePrivacySetting()
		pipeline.updatePrivacySetting("Scout", 1);
		pipeline.updatePrivacySetting("Oski", 2);
		//pipeline.updatePrivacySetting("oski", 0); // misspelling 
		
		// PREVIOUS TEST DISCOVERS USERNAMES MUST BE CASE INSENSTIVE ^^
		pipeline.updatePrivacySetting("Timothy", 1); // non-existent
		
		// add 2 quizzes
		// tests addQuizToDB()
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
		
		pipeline.addQuizToDB(quiz1);
		pipeline.addQuizToDB(quiz2);
		
		// add doggy performances 
		// tests addPerformanceToDB()
		Performance per1 = new Performance(quiz1, "Maggie", 0.95);
		Performance per2 = new Performance(quiz1, "Oski", 0.90);
		Performance per3 = new Performance(quiz1, "Air Bud", 0.88);
		Performance per4 = new Performance(quiz1, "Beethoven", 0.88);
		Performance per5 = new Performance(quiz1, "Scout", 0.68);
		Performance per6 = new Performance(quiz2, "Sarge", 0.20);
		Performance per7 = new Performance(quiz2, "Sarge", 0.40);
		Performance per8 = new Performance(quiz2, "Sarge", 0.82);
		Performance per9 = new Performance(quiz2, "Bo", 0.41);
		Performance per10 = new Performance(quiz2, "Maggie", 0.95);
		pipeline.addPerformanceToDB(per1);
		pipeline.addPerformanceToDB(per2);
		pipeline.addPerformanceToDB(per3);
		pipeline.addPerformanceToDB(per4);
		pipeline.addPerformanceToDB(per5);
		pipeline.addPerformanceToDB(per6);
		pipeline.addPerformanceToDB(per7);
		pipeline.addPerformanceToDB(per8);
		pipeline.addPerformanceToDB(per9);
		pipeline.addPerformanceToDB(per10);
		
		// add some note messages
		// tests addMessage
		Message msg1 = new Message("Sarge", "Bo", "I was just diagnosed with irritable bowel syndrome.", true, null, "note");
		Message msg2 = new Message("Bo", "Sarge", "I'm sorry to hear that, bo.", true, null, "note");
		Message msg3 = new Message("Air Bud", "Beethoven", "My 90s movies were better than yours.", true, null, "note");
		Message msg4 = new Message("Beethoven", "Air Bud", "Factual. Want to grab Slushies later?", false, null, "note");
		pipeline.addMessage(msg1);
		pipeline.addMessage(msg2);
		pipeline.addMessage(msg3);
		pipeline.addMessage(msg4);
		
		
		
		
		
		
		
		
		
		
		
	}
	
//	// addQuizToDB()
//	@Test
//	public void addQuizToDBTest() {
//		

//	}
	
	
	// closePipeLine()
	@Test
	public void closePipelineTest() {
		pipeline.closePipeline();
	}
	
}
