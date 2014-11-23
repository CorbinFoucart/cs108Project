package project;

public class PictureQuestion extends QuestionResponse{

	private String imageFilename;
	
	/**
	 * The picture question constructor uses the Question response constructor,
	 * but initializes an empty string question. Users can later manually set a
	 * text question to accompany the picture.
	 * 
	 * The PictureQuestion constructor takes in a string, but this
	 * string represents the filename of the image.
	 */
	public PictureQuestion(String imageFilename) {
		super("");
		this.imageFilename = imageFilename;
	}
	
	// returns the filename of the image
	public String getImageFilename() {
		return imageFilename;
	}
	
	/**
	 * Uses protected methods in the super class to change the private
	 * instance variable "question" of the QuestionResponse object
	 * from which the PictureQuestion inherits. 
	 * 
	 * In the constructor, this is initialized as the empty string "".
	 * 
	 * @param question - the optional text question to accompany the picture
	 */
	public void setOptionalTextQuestion(String question) {
		String superQuestion = getSuperQuestion();
		setSuperQuestion(question);
	}
	
	//TODO should picture files be stored here? (seems to make sense) or in another database?

}
