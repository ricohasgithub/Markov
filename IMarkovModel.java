
public interface IMarkovModel {
	
	// Sets the training text from a file
	public void setTraining(String text);
	
	// Sets the seed for the RNG
	public void setRandom(int seed);
	
	// Gets the random text and cuts it off at a given length
	public String getRandomText(int numChars);
	
}