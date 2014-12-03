package project;

public class userRatingCalculator {
	
	private User userA;
	private User userB;
	private double newRatingA;
	private double newRatingB;
	private int sA = 0;
	private int sB = 0;
	
	// ELO K factor in chess rating algorithm
	private static final int K = 32;
	
	public userRatingCalculator(User userA, User userB, boolean userAwon) {
		this.userA = userA;
		this.userB = userB;
		
		// deduce winner
		if (userAwon) {
			sA = 1;
		}else {
			sB = 1;
		}
		
		calculateNewRatings();
	}
	
	private void calculateNewRatings() {
		double oldRatingA = userA.getRating();
		double oldRatingB = userB.getRating();
		
		double qA = Math.pow(10, oldRatingA/400);
		double qB = Math.pow(10, oldRatingB/400);
		
		// Calculate expectations
		double EA = qA / (qA + qB);
		double EB = qB / (qA + qB);
		
		newRatingA = oldRatingA + K * (sA - EA);
		newRatingB = oldRatingB + K * (sB - EA);
	}
	
	public double getNewRatingA() {
		return newRatingA;
	}
	
	public double getNewRatingB() {
		return newRatingB;
	}
	
	
	
	
	
	
	

}
