package project;

public class Announcement extends Message{
	
	/*
	 * inherited class to put announcements in database
	 * inherits from message, which is the main ctor.
	 */
	public Announcement(String from, String text) {
		super(null, from, text, ANNOUNCEMENT);
	}
}
