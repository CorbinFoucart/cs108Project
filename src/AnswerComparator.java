package project;

import java.io.*;

/**
 * 
 * Utility class used for general string comparisons in checking 
 * question answers against user answers
 * 
 * General idea is that client can create a an answer comparator 
 * and check for extended correctness with it. e.g. checking if
 * an accepted string and the user answer string are off by one
 * character (to allow for typos).
 * 
 * User enters which cases they would like to check for in form
 * of booleans. For each example, see oneCharacterOff()
 * documentation.
 * 
 * input case determines whether or not case should matter
 *
 */
public class AnswerComparator {
	
	private boolean ex1;
	private boolean ex2;
	private boolean caseMatters;

	
	public AnswerComparator(boolean ex1, boolean ex2, boolean caseMatters) {
		this.ex1 = ex1;
		this.ex2 = ex2;
		this.caseMatters = caseMatters;
	}
	
	/**
	 * Class method that checks if a string is equal to another string
	 * up to one 'typo'
	 * @param str1 - comparison string 1
	 * @param str2 - comparison string 2
	 * @return - boolean determining if the two strings are the 
	 * same to the difference of one character.
	 * 
	 * notes:
	 * 
	 * trivially, "woody","woody" should return true without any checks
	 * other than equality
	 * 
	 * non trivial examples:
	 * ex. 1: "woody", "woodyy" | "woody", "wooddy"
	 * should return true, because there is only one extra character
	 * 
	 * ex. 2: "woody", "woofy" | "woody", "woodi"
	 * should return true, allowing one character substitution
	 *  
	 * Note that we do NOT offer the user to check combinations 
	 * of ex1, ex2 i.e. "woody" "boordy", because we believe that
	 * this allows the user to be too far off from the original answer
	 * string. Each of these cases check discretely.
	 * 
	 */
	public boolean oneCharacterOff(String accepted, String user) {
		// no typo case
		if (accepted.equals(user)) return true;
		
		// if case doesn't matter, change input to lower case
		if (caseMatters == false) {
			accepted = accepted.toLowerCase();
			user = user.toLowerCase();
			if (accepted.equals(user)) return true;
		}
		
		// catch for one letter added		
		if (ex1 && oneLetterAdded(accepted, user)) return true;
		
		// catch for one letter changed
		if (ex2 && oneLetterChanged(accepted, user)) return true;
		
		
		return false;
	}
	
	// helper method to handle cases such as example 1
	private boolean oneLetterAdded(String accepted, String user) {
		
		// check if the sizes are off by one for speed
		if (user.length() - accepted.length() == 1) {
			for (int i = 0; i < accepted.length(); i++) {
				if (accepted.charAt(i) != user.charAt(i)) {
					StringBuilder sb = new StringBuilder(user);
					sb.deleteCharAt(i);
					String fixed = sb.toString();
					return (accepted.equals(fixed));
				}
			}
			String fixed = user.substring(0, user.length() - 1);
			return (accepted.equals(fixed));
		}
		
		return false;
	}
	
	// helper method handle cases like ex. 2
	private boolean oneLetterChanged(String accepted, String user) {
		//check if same size for speed
		if (accepted.length() != user.length()) return false;
		
		for (int i = 0; i < accepted.length(); i++) {
			char skdm = accepted.charAt(i);
			char skcm = user.charAt(i);
			boolean truth = (accepted.charAt(i) != user.charAt(i));
			if (accepted.charAt(i) != user.charAt(i)) {
				StringBuilder sbA = new StringBuilder(accepted);
				StringBuilder sbU = new StringBuilder(user);
				
				sbA.deleteCharAt(i);
				sbU.deleteCharAt(i);
				
				String washA = sbA.toString();
				String washU = sbU.toString();
				
				return (washA.equals(washU));				
			}
		}
		
		
		return false;
		
		
	}
	
}
