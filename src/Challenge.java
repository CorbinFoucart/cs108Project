package project;

public class Challenge{

	private String issuer;
	private String recipient;
	private String message;
	private String issuer_perf_id;
	private String recipient_perf_id;
	private String status;
	private boolean announced;
	private String winner;
	private String loser;
	private String challenge_id;
	private String quiz_id;
	
	
	// Ctor for creating a new challenge object
	public Challenge(String issuer, String recipient, String quiz_id) {
		this.issuer = issuer;
		this.recipient = recipient;
		this.quiz_id = quiz_id;
		
		generateID();
		status = "0";
		announced = false;
		winner = null;
		loser = null;
		message = null;
		issuer_perf_id = null;
		recipient_perf_id = null;
		
	}	
	
	// Ctor for buiding new challenge object from DB info 
	public Challenge(String issuer,
					 String recipient, 
					 String quiz_id,
					 String message,
					 String issuer_perf_id,
					 String recipient_perf_id,
					 String status,
					 boolean announced,
					 String winner,
					 String loser,
					 String challenge_id) {
		
		this.issuer = issuer;
		this.recipient = recipient;
		this.quiz_id = quiz_id;
		this.message = message;
		this.issuer_perf_id = issuer_perf_id;
		this.recipient_perf_id = recipient_perf_id;
		this.status = status;
		this.announced = announced;
		this.winner = winner;
		this.loser = loser;
		this.challenge_id = challenge_id;		
	}
	
	public void generateID() {
		IDGenerator id_generator = new IDGenerator();
		challenge_id = id_generator.generateID();
	}
	
	// -------------------- setters --------------------- //
	
	public void setIssuerPerfID(String id) {
		this.issuer_perf_id = id;
	}
	
	public void setRecipientPerfID(String id) {
		this.recipient_perf_id = id;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setStatus(int status) {
		this.status = Integer.toString(status);
	}
	
	public void setWinner(String user) {
		this.winner = user;
	}
	
	public void setLoser(String user) {
		this.loser = user;
	}
	
	// -------------------- getters --------------------- //
	
	public String getID() {
		return challenge_id;
	}
	
	public String getQuizID() {
		return quiz_id;
	}
	
	public String getIssuer() {
		return issuer;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public String getIssuerPerfID() {
		return issuer_perf_id;
	}
	
	public String getRecipientPerfID() {
		return recipient_perf_id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getStatus() {
		return status;
	}
	
	public boolean getAnnounced() {
		return announced;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public String getLoser() {
		return loser;
	}
	
	
}
