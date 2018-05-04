import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTRandom(){
    	myRandom = new Random();
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[]words, String target, int start){
        for(int i=start; i<words.length; i++){
            if(words[i].equals(target)){
                return i;
            }
        }
        return -1;
    }
       
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
//        System.out.println("key: " + key);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
//            System.out.println(follows);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length-key.length()){
	        int currentIdx = indexOf(myText,key,pos);
	        pos = currentIdx + 1;
	        if (currentIdx !=-1 && currentIdx < myText.length-key.length()){
	            String followChar = myText[currentIdx+1];
	            follows.add(followChar);
	        }
	        else {
	            break;
	        }
	    }
        return follows;
    }

    public void testIndexOf(){
        String[] words = {"this","is","just","a","test","yes","this","is","a","simple","test"};
        System.out.println(indexOf(words,"this",0));
        System.out.println(indexOf(words,"this",3));
        System.out.println(indexOf(words,"frog",0));
        System.out.println(indexOf(words,"frog",5));
        System.out.println(indexOf(words,"simple",2));
        System.out.println(indexOf(words,"test",5));
    }
    
}
