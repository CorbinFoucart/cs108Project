package project;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Message class allows creation of message objects
 * with relevant info
 * @author rebeccadeubler
 *
 */
public class Message {
	private String to;
	private String from;
	private String text;
	private String type;
	private String date_string;
	private long date_long;
	private boolean was_read;
	private String msg_id;
	
	public static final String NOTE = "note";
	public static final String REQUEST = "friend_request";
	public static final String ANNOUNCEMENT = "announcement";

	//types are: message, announcement, challenge, friend request
	
	/**
	 * Constructor creates a new message object
	 * @param to message recipient
	 * @param from message sender
	 * @param text message text
	 * @param type type of message
	 */
	public Message(String to, String from, String text, String type){
		this.to = to;
		this.from = from;
		this.text = text;
		this.type = type;
		this.was_read = false;
		generateID();
		Date dateObj = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss");
		date_string = dateFormat.format(dateObj);
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		date_long = Long.parseLong(dateFormat.format(dateObj));
	}
	/**
	 * Constructor for user when formed out of database
	 * @param to recipient
	 * @param from sender
	 * @param text text of message
	 * @param date_string date as string
	 * @param date_long date as long
	 * @param was_read true if read
	 * @param type message type
	 * @param msg_id message id
	 */
	public Message(String to, String from, String text, String date_string, long date_long, boolean was_read, String type, String msg_id){
		this.to = to;
		this.from = from;
		this.text = text;
		this.type = type;
		this.date_string = date_string;
		this.date_long = date_long;
		this.was_read = was_read;
		this.msg_id = msg_id;
	}
	
	/**
	 * Sets the recipient of the message
	 * @param username username of recipient
	 */
	public void setRecipient(String username) {
		this.to = username;
	}
	
	/**
	 * Sets the message as read
	 */
	public void setToRead(){
		was_read = true;
	}
	
	/**
	 * Returns message recipient
	 * @return message recipient
	 */
	public String getTo(){
		return to;
	}
	
	/**
	 * Returns message sender
	 * @return message sender
	 */
	public String getFrom(){
		return from;
	}
	
	/**
	 * Returns the message text
	 * @return message text
	 */
	public String getMessage(){
		return text;
	}
	
	/**
	 * Returns the message type
	 * @return message_type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Returns the date as a string
	 * @return date as string
	 */
	public String getDateAsString(){
		return date_string;
	}
	
	/**
	 * Returns the date as a long
	 * @return date as long
	 */
	public long getDateAsLong(){
		return date_long;
	}
	
	/**
	 * Returns status of message
	 * @return true if read
	 */
	public boolean getStatus(){
		return was_read;
	}
	
	/**
	 * Generates an id for the message
	 */
	public void generateID() {
		IDGenerator id_generator = new IDGenerator();
		msg_id = id_generator.generateID();
	}
	
	/**
	 * Returns message id
	 * @return message id
	 */
	public String getID() {
		return msg_id;
	}
}
