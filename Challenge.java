package project;

public class Challenge extends Message{

	/*
	 * Inherited class to add a challenge message to the database
	 * inherits message ctor.
	 */
	public Challenge(String to, String from, String text, String quiz_id) {
		super(to, from, text, quiz_id, CHALLENGE);
	}
	
}
