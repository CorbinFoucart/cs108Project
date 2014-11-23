package project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class IDGenerator {
	private static final int ID_LEN = 8;
	private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final Random rnd = new Random();
	
	public IDGenerator() {
	}
	
	public String generateID() {
		StringBuilder sb = new StringBuilder(ID_LEN);
		for (int i = 0; i < ID_LEN; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}
}
