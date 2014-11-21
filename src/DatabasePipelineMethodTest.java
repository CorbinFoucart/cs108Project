package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatabasePipelineMethodTest {
	
	private DatabasePipeline pipeline = new DatabasePipeline();

	// creating doggyworld with only the DBpipeline
	// clears the databases and resets them 
	
	public static final String NOTE = "note";
	public static final String CHALLENGE = "challenge";
	public static final String REQUEST = "friend_request";
	public static final String ANNOUNCEMENT = "announcement";
	
	
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
		
		// quiz incrementation
		// tests incrementQuizTaken(), clearquizPerformace()
		
		//works
		pipeline.incrementQuizTaken(quiz1.getQuizID());
		pipeline.incrementQuizTaken(quiz1.getQuizID());
		pipeline.incrementQuizTaken(quiz1.getQuizID());

		
		
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

		
		// creates notes
		// tests note class, addNote()
		Note n1 = new Note("Sarge", "Bo", "I was just diagnosed with irritable bowel syndrome.");
		Note n2 = new Note("Sarge", "Bo", "I'm sorry to hear that, bo.");
		Note n3 = new Note("Air Bud", "Beethoven", "My 90s movies were better than yours.");
		Note n4 = new Note("Beethoven", "Air Bud", "Factual. Want to grab slushies later?");
		pipeline.addNote(n1);
		pipeline.addNote(n2);
		pipeline.addNote(n3);
		pipeline.addNote(n4);
		
		// creates challenges
		// tests challenge class, addChallenges()
		Challenge challenge1 = new Challenge("Air Bud", "Beethoven", "Let's find out who's the better 90s dog!", quiz1.getQuizID());
		Challenge challenge2 = new Challenge("Sarge", "Bo", "They should call you cat-face meowmers.", quiz1.getQuizID());
		Challenge challenge3 = new Challenge("ascascd", "snssrgser", "Proof that non-users can create challenges.", quiz2.getQuizID());
		Challenge challenge4 = new Challenge("Maggie", "Beethoven", "Proof that non-friends can create challenges", quiz2.getQuizID());
		pipeline.addChallenge(challenge1);
		pipeline.addChallenge(challenge2);
		pipeline.addChallenge(challenge3);
		pipeline.addChallenge(challenge4);
		
		// creates announcements
		Announcement an1 = new Announcement("Sarge", "Note: All cat-related content will be flagged and removed!");
		Announcement an2 = new Announcement("Sarge", "Remember to tune into FM 106.3 WOOF tonight!");
		pipeline.addAnnouncement(an1);
		pipeline.addAnnouncement(an2);
		
		// creates friend requests
		// tests friend request class, addFriendRequest()
		FriendRequest fr1 = new FriendRequest("Maggie", "Bo");
		FriendRequest fr2 = new FriendRequest("Oksi", "Bo");
		FriendRequest bogus = new FriendRequest("jancjkna", "asckjbasc");
		FriendRequest bogus2 = new FriendRequest("Beethoven", "Bo");
		pipeline.addFriendRequest(fr1);
		pipeline.addFriendRequest(fr2);
		pipeline.addFriendRequest(bogus);	
		pipeline.removeMessage(bogus.getID());
		pipeline.removeMessage(bogus2.getID());
		pipeline.addFriendRequest(bogus2);	
		pipeline.removeMessage(bogus2.getID());
		
		
		// General testing observations
		// message input type pretty shady, consider subclassing it?
		
		
		
		
		
		
		
		
		
		
		
		
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
