package project;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import project.*;

public class DatabasePipelineTestToyWorld2 {


//	@Test
//	public void testGettingQuestions() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		QuestionResponse q1 = (QuestionResponse) pipeline.retrieveQuestionFromDB("Who was the best dog of all time?");
//		assertEquals(q1.getQuestion(), "Who was the best dog of all time?");
//		QuestionResponse q2 = (QuestionResponse) pipeline.retrieveQuestionFromDB("What is the best type of dog treat?");
//		assertEquals(q2.getQuestion(), "What is the best type of dog treat?");
//		pipeline.closePipeline();
//	}
//	
//	@Test
//	public void testGettingFriends() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		ArrayList<String> friends = pipeline.getFriends("scout");
//		assertEquals(friends.size(), 3);
//		assertTrue(friends.get(0).equals("bo"));
//		assertTrue(friends.get(1).equals("sarge"));
//		assertTrue(friends.get(2).equals("beethoven"));
//		friends = pipeline.getFriends("mogli");
//		assertEquals(friends.size(), 0);
//		friends = pipeline.getFriends("beethoven");
//		assertEquals(friends.size(), 4);
//		assertTrue(friends.get(0).equals("scout"));
//		assertTrue(friends.get(1).equals("bo"));
//		assertTrue(friends.get(2).equals("sarge"));
//		assertTrue(friends.get(3).equals("air bud"));
//		pipeline.closePipeline();
//	}
//	
//	@Test
//	public void testGettingMessages() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		ArrayList<Message> messages = pipeline.getMessages("bo");
//		assertEquals(messages.size(), 1);
//		assertTrue(messages.get(0).getFrom().equals("sarge"));
//		assertTrue(messages.get(0).getMessage().equals("I was just diagnosed with irritable bowel syndrome."));	
//		assertTrue(messages.get(0).getDateAsString().equals("201402140643"));	
//		assertTrue(messages.get(0).getDateAsLong() == 201402140643L);
//		assertTrue(messages.get(0).getType().equals("note"));
//		assertTrue(messages.get(0).getStatus());
//
//		messages = pipeline.getFriendRequests("bo");
//		assertEquals(messages.size(), 1);
//		assertTrue(messages.get(0).getFrom().equals("maggie"));
//		assertTrue(messages.get(0).getMessage().equals("Add me as a friend!"));
//		assertTrue(messages.get(0).getDateAsString().equals("201312230123"));
//		assertTrue(messages.get(0).getDateAsLong() == 201312230123L);
//		assertTrue(messages.get(0).getType().equals("friend_request"));
//		assertFalse(messages.get(0).getStatus());
//		
//		messages = pipeline.getChallenges("sarge");
//		assertEquals(messages.size(), 2);
//		assertTrue(messages.get(0).getFrom().equals("bo"));
//		assertTrue(messages.get(0).getMessage().equals("Dog Test 1"));
//		assertTrue(messages.get(0).getDateAsString().equals("201312230123"));
//		assertTrue(messages.get(0).getDateAsLong() == 201312230123L);
//		assertTrue(messages.get(0).getType().equals("challenge"));
//		assertFalse(messages.get(0).getStatus());
//		assertTrue(messages.get(1).getFrom().equals("bo"));
//		assertTrue(messages.get(1).getMessage().equals("Dog Test 1"));
//		assertTrue(messages.get(1).getDateAsString().equals("201312230123"));
//		assertTrue(messages.get(1).getDateAsLong() == 201312230123L);
//		assertTrue(messages.get(1).getType().equals("challenge"));
//		assertTrue(messages.get(1).getStatus());
//		
//		messages = pipeline.getRecentMessages("bo");
//		assertEquals(messages.size(), 1);
//		assertTrue(messages.get(0).getFrom().equals("sarge"));
//		assertTrue(messages.get(0).getMessage().equals("I was just diagnosed with irritable bowel syndrome."));
//		assertTrue(messages.get(0).getDateAsString().equals("201402140643"));
//		assertTrue(messages.get(0).getDateAsLong() == 201402140643L);
//		assertTrue(messages.get(0).getType().equals("note"));
//		assertTrue(messages.get(0).getStatus());
//		pipeline.closePipeline();
//	}
//	
//	@Test
//	public void testGettingAnnouncements() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		ArrayList<Message> announcements = pipeline.getAnnouncements("bo");
//		assertEquals(announcements.size(), 1);
//		assertTrue(announcements.get(0).getFrom().equals("sarge"));
//		assertTrue(announcements.get(0).getMessage().equals("Reminder: all quizzes with cat subject matter will be flagged and removed!"));
//		assertTrue(announcements.get(0).getDateAsString().equals("201312230123"));
//		assertTrue(announcements.get(0).getDateAsLong() == 201312230123L);
//		assertTrue(announcements.get(0).getType().equals("announcement"));
//		assertFalse(announcements.get(0).getStatus());
//		pipeline.closePipeline();
//	}
	
	
//	// * Can't use this b/c it tries to get friends and we have no db connection.
//	@Test
//	public void testGettingUsers() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		User user = pipeline.getUser("air bud");
//		assertTrue(user.getUsername().equals("air bud"));
//		assertTrue(user.getPassword().equals("air budair bud"));
//		assertTrue(user.getPrivacy() == 0);
//		pipeline.closePipeline();
//	}
	
	
//	@Test
//	public void testAdminStatusPrivacyAndPassword() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		assertTrue(pipeline.checkIfAdmin("sarge"));
//		assertFalse(pipeline.checkIfAdmin("bo"));
//		assertFalse(pipeline.checkIfAdmin("maggie"));
//		assertTrue(pipeline.checkPrivacySettings("scout") == 1);
//		assertTrue(pipeline.checkPrivacySettings("oski") == 2);
//		assertTrue(pipeline.checkPrivacySettings("maggie") == 0);
//		assertTrue(pipeline.getPasswordFromDB("scout").equals("scoutscout"));
//		assertTrue(pipeline.getPasswordFromDB("oski").equals("oskioski"));
//		assertTrue(pipeline.getPasswordFromDB("air bud").equals("air budair bud"));
//		pipeline.closePipeline();
//	}
//	
//	@Test
//	public void testTotalsAndNumQuizzesTaken() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		assertEquals(pipeline.totalNumberOfQuizzes(), 2);
//		assertEquals(pipeline.totalNumberOfUsers(), 7);
//		assertEquals(1, pipeline.getNumberQuizzesTaken("bo"));
//		assertEquals(3, pipeline.getNumberQuizzesTaken("sarge"));
//		assertEquals(1, pipeline.getNumberQuizzesCreated("bo"));
//		assertEquals(0, pipeline.getNumberQuizzesCreated("sarge"));
//		pipeline.closePipeline();
//	}
	
	
//	// MUST BE RUN SEPARATELY; otherwise sarge's scores below get copied.
//	@Test
//	public void testPerformancesAndQuizzesCreated() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		ArrayList<Quiz> quizzesCreated = pipeline.getQuizzesCreated("bo");
//		assertEquals(quizzesCreated.size(), 1);
//		quizzesCreated = pipeline.getQuizzesCreated("maggie");
//		assertEquals(quizzesCreated.size(), 0);
//		
//		
//		ArrayList<Performance> performances = pipeline.getPerformances("sarge");
//		assertEquals(3, performances.size());
//		assertTrue(performances.get(0).getQuizName().equals("Dog Trivia 1"));
//		assertTrue(performances.get(1).getQuizName().equals("Dog Trivia 2"));
//		assertTrue(performances.get(0).getScore() == 0.4);
//		assertTrue(performances.get(1).getScore() == 0.88);
//		performances = pipeline.getPerformances("doggies");
//		assertEquals(performances.size(), 0);
//		
//		pipeline.updatePrivacySetting("sarge", 0);
//		
//		/** Can do only if you have a quiz ID - so check.
//		 * performances = pipeline.getQuizPerformances("sarge", "");
//		 * assertEquals(performances.size(), 2);
//		assertTrue(performances.get(0).getQuizName().equals("Dog Trivia 2"));
//		assertTrue(performances.get(1).getQuizName().equals("Dog Trivia 2"));
//		assertTrue(performances.get(0).getScore() == 0.88);
//		assertTrue(performances.get(1).getScore() == 0.4);
//		performances = pipeline.getQuizPerformances("doggies", "");
//		assertEquals(performances.size(), 0);
//		
//		 */
//		pipeline.closePipeline();
//	}
//	
	
	
//	@Test
//	public void testPerformances() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		Quiz quiz1 = new Quiz("Hounds of Page & Screen", false, false, false, "sarge");
//		QuestionMultipleChoice q1 = new QuestionMultipleChoice("Which is both a famous literary dog and a grocery store chain?");
//		q1.addChoice(true, "Winn-Dixie");
//		q1.addChoice(false, "Safeway");
//		q1.addChoice(false, "Stop & Shop");
//		q1.setWeight(2);	
//		QuestionResponse q2 = new QuestionResponse("How many Dalmatians are there?");
//		q2.addAcceptedAnswer("101");
//		q2.setWeight(3);
//		quiz1.addQuestion(q1);
//		assertTrue(2 == quiz1.getPointsPossible());
//		quiz1.addQuestion(q2);
//		
//		pipeline.addQuizToDB(quiz1);
//		
//		Performance perf = new Performance(quiz1, "oski", 1.0);
//		pipeline.addPerformanceToDB(perf);
//		perf = new Performance(quiz1, "sarge", 0.9);
//		pipeline.addPerformanceToDB(perf);
//		perf = new Performance(quiz1, "sarge", 0.8);
//		pipeline.addPerformanceToDB(perf);
//		
//		pipeline.closePipeline();
//	}
	
//	@Test
//	public void testAddAllKindsOfMessages() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		Message message = new Message("air bud", "sarge", "Sup, dude?", false, null, "note");
//		pipeline.addMessage(message);
//		message = new Message("maggie", "bo", "Be my friend?", false, null, "friend_request");
//		pipeline.addMessage(message);
//		message = new Message("scout", "bo", "Dog Trivia 1", false, null, "challenge");
//		pipeline.addMessage(message);
//		pipeline.closePipeline();
//	}
//	
//	@Test
//	public void testAddingUser() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		User usr1 = new User("winn-dixie", "winn-dixiewinn-dixie");
//		pipeline.addUser(usr1);
//		User usr2 = new User("hound of the baskervilles", "watson");
//		pipeline.addUser(usr2);
//		pipeline.addFriendshipToDB(usr1, usr2);
//		pipeline.closePipeline();
//	}
//	
//	@Test
//	public void testChangingSettings() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		pipeline.promoteToAdmin("oski");
//		pipeline.promoteToAdmin("sarge");
//		pipeline.promoteToAdmin("rebecca, who is not a dog");
//		pipeline.updatePrivacySetting("oski", 0);
//		pipeline.updatePrivacySetting("scout", 1);
//		pipeline.updatePrivacySetting("balloon", 1);
//		pipeline.updatePrivacySetting("hound of the baskervilles", 2);
//		pipeline.closePipeline();
//	}
//
//	@Test
//	public void testRecent() {
//		DatabasePipeline pipeline = new DatabasePipeline();
//		ArrayList<Performance> performances = pipeline.getRecentPerformances("sarge");
//		assertEquals(performances.size(), 3);
//		assertTrue(performances.get(0).getQuizName().equals("Hounds of Page & Screen"));
//		performances = pipeline.getRecentPerformances("doggies");
//		assertEquals(performances.size(), 0);
//		
//		Message message = new Message("air bud", "bo", "Hey man.", false, null, "note");
//		pipeline.addMessage(message);
//		message = new Message("air bud", "beethoven", "Playing mah piano.", false, null, "note");
//		pipeline.addMessage(message);
//		
//		ArrayList<Message> msgs = pipeline.getRecentMessages("air bud");
//		assertEquals(msgs.size(), 3);
//		
//		pipeline.closePipeline();
//	}
}
