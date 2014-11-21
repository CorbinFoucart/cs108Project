package project;

public class DoggyWorldMasterTest {
	
	private DatabasePipeline dp = new DatabasePipeline();
	
	public void main() {
		dp.clearDatabase();
		User bo = new User("bo", "bobo");
		dp.addUser(bo);
	}

}
