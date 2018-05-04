import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int N;
    private HashMap<String,ArrayList<String>> followsList;
    public EfficientMarkovModel(int numChar) {
        myRandom = new Random();
        N = numChar;
        followsList = new HashMap<String,ArrayList<String>>();
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index,index+N);
        sb.append(key);
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public void buildMap(){
        for(int i=0; i<myText.length()-N; i++){
            getFollows(myText.substring(i,i+N));
        }
        printHashMapInfo();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows= followsList.get(key);
        if(follows == null){
            follows = new ArrayList<String>();
            int pos = 0;
            while(pos < myText.length()-N){
                int start = myText.indexOf(key,pos);
                if(start == -1){
                    break;
                }
                if(start+key.length()>=myText.length()){
                    break;
                }
                follows.add(myText.substring(start+key.length(),start+key.length()+1));
                pos = start+1;
            }
            followsList.put(key,follows);
        }
        return follows;
    }
    
    public void printHashMapInfo(){
        int keyNum = 0;
        int maxKey = 0;
        ArrayList<String> maxKeyList= new ArrayList<String>();
        for(String key : followsList.keySet()){
            System.out.println(key + " " + followsList.get(key));
            keyNum++;
            if(followsList.get(key).size()>maxKey){
                maxKey = followsList.get(key).size();
                maxKeyList.clear();
//                maxKeyList.add(followsList.get(key));
            }
            if(followsList.get(key).size()==maxKey){
//                maxKeyList.add(followsList.get(key));
            }
        }
        System.out.println("# of keys: " + keyNum);
        System.out.println("# of largest ArrayList: " + maxKey);
        System.out.println(maxKeyList);
    }
    
    public String toString() {
        return "Efficient Markov model of order " + N;
    }
    
}
