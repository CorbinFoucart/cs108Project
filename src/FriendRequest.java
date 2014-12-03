package project;

public class FriendRequest extends Message{

	/*
	 * Inherited class to add a friend request message
	 * to the database; inherits the message ctor.
	 */
	public FriendRequest(String to, String from) {
		super(to, from, null, REQUEST);
	}
}
