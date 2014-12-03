package project;

/**
 * Announcement class - creates a new Announcement
 * with specified from and text fields.
 * @author rebeccadeubler
 *
 */

public class Announcement extends Message{
	

	public Announcement(String from, String text) {
		super(null, from, text, null, ANNOUNCEMENT);
	}
}
