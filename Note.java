package project;

public class Note extends Message{

	/**
	 * inherited constructor that only allows user to enter
	 * to from and text; the rest is handled by the superclass
	 */
	public Note(String to, String from, String text) {
		super(to, from , text, null, NOTE);
	}

}
