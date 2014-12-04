package project;

/**
 * Challenge class stores information for
 * challenges from one user to another to
 * take a quiz - including identity, ids,
 * status of the challenge (completed, in progress,
 * etc.), winner, loser, id, whether it has
 * been announced, etc.
 * @author rebeccadeubler
 *
 */

public class Challenge{

	private String issuer;
	private String recipient;
	private String message;
	private String issuer_perf_id;
	private String recipient_perf_id;
	private int status;
	private boolean announced;
	private String winner;
	private String loser;
	private String challenge_id;
	private String quiz_id;
	
	
	/**
	 * Challenge constructor - takes a user,
	 * recipient, and String id
	 * @param issuer user challenging
	 * @param recipient user receiving challenge
	 * @param quiz_id id of quiz subject of challenge
	 */
	public Challenge(String issuer, String recipient, String quiz_id) {
		this.issuer = issuer;
		this.recipient = recipient;
		this.quiz_id = quiz_id;
		
		generateID();
		status = 0;
		announced = false;
		winner = null;
		loser = null;
		message = null;
		issuer_perf_id = null;
		recipient_perf_id = null;
		
	}	
	
	/**
	 * Creates a Challenge object from info from database
	 * @param issuer user challenging
	 * @param recipient user receiving challenge
	 * @param quiz_id id of quiz subject of challenge
	 * @param message message from challenger
	 * @param issuer_perf_id id of issuer's performance
	 * @param recipient_perf_id id of recipient'f performance
	 * @param status status of challenge
	 * @param announced whether challenge has been announced
	 * @param winner winner of challenge
	 * @param loser loser of challenge
	 * @param challenge_id challenge's id
	 */
	public Challenge(String issuer,
					 String recipient, 
					 String quiz_id,
					 String message,
					 String issuer_perf_id,
					 String recipient_perf_id,
					 int status,
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
	
	/**
	 * Generates an id for the Challenge object.
	 */
	public void generateID() {
		IDGenerator id_generator = new IDGenerator();
		challenge_id = id_generator.generateID();
	}
	
	// -------------------- setters --------------------- //
	
	/**
	 * Sets the id of the relevant performance of the
	 * user issuing the challenge.
	 * @param id id of issuer's performance
	 */
	public void setIssuerPerfID(String id) {
		this.issuer_perf_id = id;
	}
	
	/**
	 * Sets the id of the relevant performance of 
	 * the user receiving the challenge
	 * @param id id of recipient's performance
	 */
	public void setRecipientPerfID(String id) {
		this.recipient_perf_id = id;
	}
	
	/**
	 * Sets the message associated with challenge
	 * @param message message to set for challenge
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Sets the status of the challenge
	 * @param status status of the challenge
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * Sets the winner of the challenge
	 * @param user username of winner
	 */
	public void setWinner(String user) {
		this.winner = user;
	}
	
	/**
	 * Sets the loser of the challenge
	 * @param user username of loser
	 */
	public void setLoser(String user) {
		this.loser = user;
	}
	
	// -------------------- getters --------------------- //
	
	/**
	 * Returns ID of challenge
	 * @return id of challenge
	 */
	public String getID() {
		return challenge_id;
	}
	
	/**
	 * Returns ID of quiz associated with challenge
	 * @return ID of quiz associated with challenge
	 */
	public String getQuizID() {
		return quiz_id;
	}
	
	/**
	 * Returns username of issuing user
	 * @return username of issuer
	 */
	public String getIssuer() {
		return issuer;
	}
	
	/**
	 * Returns username of recipient
	 * @return username of recipient
	 */
	public String getRecipient() {
		return recipient;
	}
	
	/**
	 * Returns id of relevant performance
	 * for issuing user
	 * @return id of issuer's performance
	 */
	public String getIssuerPerfID() {
		return issuer_perf_id;
	}
	
	/**
	 * Returns id of relevant performance
	 * for receiving user
	 * @return id of recipient's performance
	 */
	public String getRecipientPerfID() {
		return recipient_perf_id;
	}
	
	/**
	 * Returns message associated with challenge
	 * @return challenge's message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Returns status of challenge
	 * @return int status of challenge
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Returns whether challenge has been
	 * announced or not
	 * @return false if challenge not yet announced
	 */
	public boolean getAnnounced() {
		return announced;
	}
	
	/**
	 * Returns username of challenge winner
	 * @return username of challenge winner
	 */
	public String getWinner() {
		return winner;
	}
	
	/**
	 * Returns username of challenge loser
	 * @return username of challenge loser
	 */
	public String getLoser() {
		return loser;
	}
	
	
}
