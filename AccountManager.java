package project;

import java.util.HashMap;

public class AccountManager {
	HashMap <String, String> accounts; 
	private HashCracker cracker;
	
	public AccountManager(){
		accounts = new HashMap<String, String>();
		cracker = new HashCracker();
		//accounts.put("Patrick", "1234");
		//accounts.put("Molly", "FloPup");
	}
	
	public boolean checkForAccount(String name){
		if(accounts.containsKey(name))return true;
		else return false;
	}
	
	public boolean checkPassword(String name, String passwordEntered){
		String passwordStored = accounts.get(name);
		String hexStringOfEntered = cracker.getHexString(passwordEntered);
		if(passwordStored.equals(hexStringOfEntered)) return true;
		else return false;
	}
	
	public void createAccount(String name, String password){
		String hexString = cracker.getHexString(password);
		accounts.put(name, hexString);
	}
}
