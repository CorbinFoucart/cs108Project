package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatabasePipelineMethodTest {
	
	private DatabasePipeline pipeline = new DatabasePipeline();

	// creating doggyworld with only the DBpipeline
	// clears the databases and resets them 
	
	
//	@Test
//	public void clearDB() {
////		pipeline.clearDatabase();
//		
////		// create users
////		User bo = new User("bo", "bobo");
////		pipeline.addUser(bo);
//		
//		
//	}

	
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
		User teddy = new User("Teddy", "teddyteddy");
		User marley = new User("Marley", "marleymarley");
		User rocky = new User("Rocky", "rockyrocky");
		User rover = new User("Rover", "roverrover");
		User spike = new User("Spike", "spikespike");
		User randy = new User("Randy", "randyrandy");
		User rex = new User("Rex", "rexrex");
		User duke = new User("Duke", "dukeduke");
		User zeus = new User("Zeus", "zeuszeus");
		User hunter = new User("Hunter", "hunterhunter");
		User toto = new User("Toto", "totototo");
		User boomer = new User("Boomer", "boomerboomer");
		User winnDixie = new User("WinnDixie", "winndixiewinndixie");
		User bailey = new User("Bailey", "baileybailey");
		User clifford = new User("Clifford", "cliffordclifford");
		User cookie = new User("Cookie", "cookiecookie");
		User spot = new User("Spot", "spotspot");
		User cliff = new User("Cliff", "cliffcliff");
		User pluto = new User("Pluto", "plutopluto");
		User oreo = new User("Oreo", "oreooreo");
		User ollie = new User("Ollie", "ollieollie");
		User garfield = new User("Garfield", "garfieldgarfield");
		pipeline.addUser(bo);
		pipeline.addUser(sarge);
		pipeline.addUser(scout);
		pipeline.addUser(beethoven);
		pipeline.addUser(airBud);
		pipeline.addUser(oski);
		pipeline.addUser(maggie);
		pipeline.addUser(teddy);
		pipeline.addUser(marley);
		pipeline.addUser(rocky);
		pipeline.addUser(rover);
		pipeline.addUser(spike);
		pipeline.addUser(randy);
		pipeline.addUser(rex);
		pipeline.addUser(duke);
		pipeline.addUser(zeus);
		pipeline.addUser(hunter);
		pipeline.addUser(toto);
		pipeline.addUser(boomer);
		pipeline.addUser(winnDixie);
		pipeline.addUser(bailey);
		pipeline.addUser(clifford);
		pipeline.addUser(cookie);
		pipeline.addUser(spot);
		pipeline.addUser(cliff);
		pipeline.addUser(pluto);
		pipeline.addUser(oreo);
		pipeline.addUser(ollie);
		pipeline.addUser(garfield);
		
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
		pipeline.addFriendshipToDB(maggie, rocky);
		pipeline.addFriendshipToDB(rocky, rover);
		pipeline.addFriendshipToDB(rover, spike);
		pipeline.addFriendshipToDB(spike, randy);
		pipeline.addFriendshipToDB(randy, rex);
		pipeline.addFriendshipToDB(rex, duke);
		pipeline.addFriendshipToDB(rex, spot);
		pipeline.addFriendshipToDB(duke, zeus);
		pipeline.addFriendshipToDB(zeus, hunter);
		pipeline.addFriendshipToDB(boomer, toto);
		pipeline.addFriendshipToDB(spot, cookie);
		pipeline.addFriendshipToDB(cookie, clifford);
		pipeline.addFriendshipToDB(bailey, clifford);
		pipeline.addFriendshipToDB(bailey, winnDixie);
		pipeline.addFriendshipToDB(winnDixie, clifford);
		pipeline.addFriendshipToDB(pluto, cliff);
		
		
		// update privacy settings for dogs
		// tests updatePrivacySetting()
		pipeline.updatePrivacySetting("Scout", 1);
		pipeline.updatePrivacySetting("Oski", 2);
		//pipeline.updatePrivacySetting("oski", 0); // misspelling 
		
		// PREVIOUS TEST DISCOVERS USERNAMES MUST BE CASE INSENSTIVE ^^
		pipeline.updatePrivacySetting("Timothy", 1); // non-existent
		
		// add 2 quizzes
		// tests addQuizToDB()
		Quiz quiz1 = new Quiz("Dog Trivia 1", false, false, false, "Bo");
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
		
		Quiz quiz2 = new Quiz("Dog Trivia 2", false, false, false, "Scout");
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
		pipeline.resetTimesQuizTaken(quiz1.getQuizID());

		
		
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
		Performance per11 = new Performance(quiz2, "Clifford", 0.45);
		Performance per12 = new Performance(quiz2, "Oski", 0.85);
		Performance per13 = new Performance(quiz2, "Beethoven", 0.55);
		Performance per14 = new Performance(quiz2, "Spike", 0.65);
		Performance per15 = new Performance(quiz2, "Spot", 0.75);
		per1.setTimeTaken(12302);
		per2.setTimeTaken(14502);
		per3.setTimeTaken(124302);
		per4.setTimeTaken(123602);
		per5.setTimeTaken(142302);
		per6.setTimeTaken(112302);
		per7.setTimeTaken(123402);
		per8.setTimeTaken(123452);
		per9.setTimeTaken(12382);
		per10.setTimeTaken(12692);
		per11.setTimeTaken(246702);
		per12.setTimeTaken(266702);
		per13.setTimeTaken(166702);
		per14.setTimeTaken(566702);
		per15.setTimeTaken(286702);
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
		
		pipeline.addCategory("Art");
		pipeline.addCategory("Entertainment");
		pipeline.addCategory("Geography");
		pipeline.addCategory("History");
		pipeline.addCategory("Holiday");
		pipeline.addCategory("Language");
		pipeline.addCategory("Literature");
		pipeline.addCategory("Math");
		pipeline.addCategory("Miscellaneous");
		pipeline.addCategory("Movies");
		pipeline.addCategory("Music");
		pipeline.addCategory("Nature");
		pipeline.addCategory("Religion");
		pipeline.addCategory("Science");
		pipeline.addCategory("Sports");
		pipeline.addCategory("Television");
		pipeline.addCategory("Vocabulary");

		
		// creates notes
		// tests note class, addNote()
		Note n1 = new Note("Sarge", "Bo", "I was just diagnosed with irritable bowel syndrome.");
		Note n2 = new Note("Bo", "Sarge", "I'm sorry to hear that, bo.");
		Note n3 = new Note("Air Bud", "Beethoven", "My 90s movies were better than yours.");
		Note n4 = new Note("Beethoven", "Maggie", "You're slobbering on me.");
		Note n5 = new Note("Maggie", "Beethoven", "Sorry.");
		Note n6 = new Note("Clifford", "Maggie", "I love your program.");
		Note n7 = new Note("Oski", "Maggie", "Don't forget your lunch money tomorrow!");
		
		pipeline.addNote(n1);
		pipeline.addNote(n2);
		pipeline.addNote(n3);
		pipeline.addNote(n4);
		pipeline.addNote(n5);
		pipeline.addNote(n6);
		pipeline.addNote(n7);
		
		// creates challenges
		// tests challenge class, addChallenges()
		Challenge challenge1 = new Challenge("Air Bud", "Beethoven", quiz1.getQuizID());
		challenge1.setMessage("Let's find out who's the better 90s dog!");
		Challenge challenge2 = new Challenge("Sarge", "Bo", quiz1.getQuizID());
		challenge2.setMessage("They should call you cat-face meowmers.");
		Challenge challenge3 = new Challenge("ascascd", "snssrgser", quiz2.getQuizID());
		Challenge challenge4 = new Challenge("Maggie", "Beethoven", quiz2.getQuizID());
		Challenge challenge5 = new Challenge("Spot", "Sarge", quiz2.getQuizID());
		Challenge challenge6 = new Challenge("Duke", "Bo", quiz2.getQuizID());
		Challenge challenge7 = new Challenge("Hunter", "Oski", quiz2.getQuizID());
		Challenge challenge8 = new Challenge("Zeus", "Maggie", quiz2.getQuizID());
		Challenge challenge9 = new Challenge("Winn Dixie", "Zeus", quiz2.getQuizID());
		Challenge challenge10 = new Challenge("Clifford", "Spot", quiz2.getQuizID());
		Challenge challenge11 = new Challenge("Maggie", "Cliff", quiz2.getQuizID());
		Challenge challenge12 = new Challenge("Ollie", "Beethoven", quiz2.getQuizID());
		Challenge challenge13 = new Challenge("Spike", "Duke", quiz2.getQuizID());
		Challenge challenge14 = new Challenge("Rocky", "Rover", quiz2.getQuizID());
		Challenge challenge15 = new Challenge("Rover", "Teddy", quiz2.getQuizID());
		Challenge challenge16 = new Challenge("Cookie", "Ollie", quiz2.getQuizID());
		Challenge challenge17 = new Challenge("Pluto", "Air Bud", quiz2.getQuizID());
		Challenge challenge18 = new Challenge("Marley", "Spike", quiz2.getQuizID());
		pipeline.addChallengeToDB(challenge1);
		pipeline.addChallengeToDB(challenge2);
		pipeline.addChallengeToDB(challenge3);
		pipeline.addChallengeToDB(challenge4);
		pipeline.addChallengeToDB(challenge5);
		pipeline.addChallengeToDB(challenge6);
		pipeline.addChallengeToDB(challenge7);
		pipeline.addChallengeToDB(challenge8);
		pipeline.addChallengeToDB(challenge9);
		pipeline.addChallengeToDB(challenge10);
		pipeline.addChallengeToDB(challenge11);
		pipeline.addChallengeToDB(challenge12);
		pipeline.addChallengeToDB(challenge13);
		pipeline.addChallengeToDB(challenge14);
		pipeline.addChallengeToDB(challenge15);
		pipeline.addChallengeToDB(challenge16);
		pipeline.addChallengeToDB(challenge17);
		pipeline.addChallengeToDB(challenge18);

		
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
		
		
		// UH OH, BAD QUIZ THAT DESERVES TO BE NUKED
		// tests clearQuizHistory();
		Quiz ohNoBadQuiz = new Quiz("CAT QUIZ!", true, true, true, "Air Bud");
		QuestionResponse badq1 = new QuestionResponse("What is the best brand of cat food?");
		badq1.addAcceptedAnswer("Fancy Feast");
		badq1.addAcceptedAnswer("Meow Mix");
		badq1.addAcceptedAnswer("fancy feast");
		quiz1.addQuestion(badq1);
		
		QuestionResponse badq2 = new QuestionResponse("Who was the most famous cat?");
		badq2.addAcceptedAnswer("Garfield");
		badq2.addAcceptedAnswer("garfield");
		badq2.addAcceptedAnswer("GARFIELD");
		quiz1.addQuestion(badq2);
		
		pipeline.addQuizToDB(ohNoBadQuiz);
		
		// UH OH, BAD QUIZ THAT DESERVES TO BE NUKED
		// tests clearQuizHistory();
		Quiz ohNoBadQuiz2 = new Quiz("CAT QUIZ! Part II", true, true, true, "Air Bud");
		QuestionResponse badq4 = new QuestionResponse("What is the best brand of cat food?");
		badq1.addAcceptedAnswer("Fancy Feast");
		badq1.addAcceptedAnswer("Meow Mix");
		badq1.addAcceptedAnswer("fancy feast");
		quiz2.addQuestion(badq2);
		
		QuestionResponse badq5 = new QuestionResponse("Who was the most famous cat?");
		badq2.addAcceptedAnswer("Garfield");
		badq2.addAcceptedAnswer("garfield");
		badq2.addAcceptedAnswer("GARFIELD");
		quiz2.addQuestion(badq5);
		
		pipeline.addQuizToDB(ohNoBadQuiz2);
		
		
		
		// ------------------------------------------------ Retrieval Tests ---------------------------------------------- //
		
		Quiz retrievedQuiz1 = pipeline.retrieveQuizFromDB(quiz1.getQuizID());
		Quiz retrievedQuiz2 = pipeline.retrieveQuizFromDB(ohNoBadQuiz.getQuizID());
				
		
	}
	
//	// addQuizToDB()
//	@Test
//	public void addQuizToDBTest() {
//		

//	}
	
	
//	// closePipeLine()
//	@Test
//	public void closePipelineTest() {
//		pipeline.closePipeline();
//	}
	
}
