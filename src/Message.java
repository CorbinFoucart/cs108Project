package project;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
	private String to;
	private String from;
	private String text;
	private String type;
	private String date_string;
	private long date_long;
	private String quiz_id;
	private boolean was_read;

	//types are: message, announcement, challenge, friend request
	
	//To create a new message
	public Message(String to, String from, String text, boolean was_read, String quiz_id, String type){
		this.to = to;
		this.from = from;
		this.text = text;
		this.type = type;
		this.quiz_id = quiz_id;
		this.was_read = was_read;
		Date dateObj = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss");
		date_string = dateFormat.format(dateObj);
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		date_long = Long.parseLong(dateFormat.format(dateObj));
	}
	
	//To reconstruct message from database
	public Message(String to, String from, String text, String date_string, long date_long, boolean was_read, String quiz_id, String type){
		this.to = to;
		this.from = from;
		this.text = text;
		this.type = type;
		this.date_string = date_string;
		this.date_long = date_long;
		this.quiz_id = quiz_id;
		this.was_read = was_read;
	}
	
	public void setToRead(){
		was_read = true;
	}
	
	public String getTo(){
		return to;
	}
	
	public String getFrom(){
		return from;
	}
	
	public String getMessage(){
		return text;
	}
	
	public String getType(){
		return type;
	}
	
	public String getDateAsString(){
		return date_string;
	}
	
	public long getDateAsLong(){
		return date_long;
	}
	
	public boolean getStatus(){
		return was_read;
	}
	
	public String getQuizID(){
		return quiz_id;
	}
}