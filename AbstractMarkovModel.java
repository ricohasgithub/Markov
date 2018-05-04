import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows= new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()-key.length()){
	        int currentIdx = myText.indexOf(key,pos);
	        pos = currentIdx + 1;
	        if (currentIdx !=-1 && currentIdx < myText.length()-key.length()){
	            String followChar = myText.substring(currentIdx+key.length(), currentIdx+key.length()+1);
	            follows.add(followChar);
	        }
	        else {
	            break;
	        }
	    }
        return follows;
    }
    
}
