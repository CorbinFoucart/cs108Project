package project;

import java.util.Random;

public class AutoQuestionSubtraction implements Question{

	private String question;
	private String answer;
	private String userAnswer;
	private double weight;
	private String quiz_id;
	private String id;
	private Random rnd = new Random();
	private String topInt;
	private String bottomInt;
	
	
	/**
	 * Ctor; takes in how may digits for each number 
	 * in the subtraction problem
	 * 
	 * If nDigits > 5-6 we can end up with weird results due to 
	 * the integers not being the right size.
	 * 
	 */
	public AutoQuestionSubtraction(int n1Digits, int n2Digits) {
		this.weight = 1;
		this.userAnswer = "";
		generateID();
		generateQuestion(n1Digits, n2Digits);
	}
	
	public void generateID() {
		IDGenerator generator = new IDGenerator();
		id = generator.generateID();
	}
	
	/*
	 * create 2 rdm ints, subtract, save appropriately; only 
	 * interesting feature here is that it compares lengths of
	 * the two ints and returns the bigger one as topInt. 
	 * If they're the same size, it makes no such choice.
	 * 
	 * Rationale for this is to get nice looking subtraction
	 * questions such as:
	 * 
	 *      1234                         56
	 *      - 56   : rather than :   - 1234 
	 *     ------                   --------
	 *     
	 *  which I think we can all agree would be worse. Negative 
	 *  numbers can be just brutal.
	 */
	private void generateQuestion(int n1Digits, int n2Digits) {
		int n1 = createRandomNDigitInt(n1Digits);
		int n2 = createRandomNDigitInt(n2Digits);
		int ans = n1 - n2;
		
		this.answer = "" + ans;
		this.topInt = "" + Math.max(n1, n2);
		this.bottomInt = "" + Math.min(n1, n2);
		this.question = "" + topInt + " " + bottomInt;
		
	}
	
	/**
	 * Creates a random n-digit integer
	 * @param digits - number of digits in the random integer being created
	 * 
	 * note: due to the format of a multiple choice question, 
	 * it is important that the first digit be 1-9 and the 
	 * others be 0-9. Forms as a string then concatenates.
	 * If it is a 1 digit number, it generates 1-9. The decision
	 * not to include 0 is because that it leads to relly lame 
	 * multiplication questions.
	 * 
	 * note: feeding this function 0 as argument gives a 
	 * one digit number. Don't do it, ya dingus!
	 * 
	 * Doesn't work on more than 10 digits as well, 
	 * due to integer problems. This should never be an issue
	 * if the client follows the parameter spec. above. 
	 * 
	 */
	public int createRandomNDigitInt(int digits) {
		
		int firstDigit = generateRndIntInRange(1,9);
		
		String concatenator = "" + firstDigit;
		for (int i = 1; i < digits; i++){
			int innerDigit = generateRndIntInRange(0,9);
			concatenator += innerDigit;
		}
		
		int result = Integer.parseInt(concatenator);
		return result;
	}
	
		// generates a random integer in range [min, max], inc. 
		public int generateRndIntInRange(int min, int max) {
			int result = rnd.nextInt((max - min) + 1) + min;
			return result;
		}
	
	public boolean isCorrect() {
		return answer.equals(userAnswer);
	}
		
	public double getPercentage() {
		if (isCorrect()) return 1;
		return 0;
	}
	
	
	// --------------------- Getter, Setter Methods ------------------------ //
	
	public String getQuestion() {
		return question;
	}
	
	public String getTopInt() {
		return topInt;
	}
	
	public String getBottomInt() {
		return bottomInt;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getClassName() {
		return "AutoQuestionSubtraction";
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setUserAnswer(String userAns){
		this.userAnswer = userAns;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setQuizID(String id) {
		quiz_id = id;		
	}	
	
	public String getQuizID() {
		return quiz_id;
	}
	
	public String getID() {
		return id;
	}
	
}
