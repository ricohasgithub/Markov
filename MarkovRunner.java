import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runEvoModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    }
    
//    public void runMarkov(String filename) { 
//    	File f = new File(filename);
//        String st = f.toString(); 
//        //String st = "this is just a test yes this is a simple test";
//        st = st.replace('\n', ' '); 
//        MarkovWordOne markovWord = new MarkovWordOne(); 
//        markovWord.setRandom(175);
//        runModel(markovWord, st, 120); 
//    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public static void main(String[] arg) throws FileNotFoundException{
    	String filename = "romeo.txt";
//    	evolutionaryMarkov(filename);
    	BufferedReader br = new BufferedReader(new FileReader(filename));
        String st = "";
    	while(true){
    		try{
    			String temp = br.readLine();
    			if(temp != null){
    				st += temp;
    			}else{
    				break;
    			}
    		}catch(IOException E){
    			System.out.println("Reading Failed:");
    			System.out.println(st.length());
    		}
    	}
    	MarkovRunner mr = new MarkovRunner();
        //String st = "this is just a test yes this is a simple test";
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne();
        // Remove next line for total randomness (without seed in RNG)
        markovWord.setRandom(100);
        // Good seeds: 250 12 175 100
        mr.runModel(markovWord, st, 200);
    }
    public static void evolutionaryMarkov(String filename) throws FileNotFoundException{
    	BufferedReader br = new BufferedReader(new FileReader(filename));
        String st = "";
    	while(true){
    		try{
    			String temp = br.readLine();
    			if(temp != null){
    				st += temp;
    			}else{
    				break;
    			}
    		}catch(IOException E){
    			System.out.println("Reading Failed:");
    			System.out.println(st.length());
    		}
    	}
    	MarkovRunner mr = new MarkovRunner();
        //String st = "this is just a test yes this is a simple test";
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne();
        MarkovZero markovZero = new MarkovZero();
        MarkovOne markovOne = new MarkovOne();
        MarkovFour markovFour = new MarkovFour();
        // Remove next line for total randomness (without seed in RNG)
        int seed = 200;
        markovZero.setRandom(seed);
        markovOne.setRandom(seed);
        markovFour.setRandom(seed);
        markovWord.setRandom(seed);
        // Good seeds: 250 12 175 100
        mr.runEvoModel(markovZero, st, 150);
        mr.runEvoModel(markovOne, st, 150);
        mr.runEvoModel(markovFour, st, 150);
        mr.runEvoModel(markovWord, st, 150);
    }
}
