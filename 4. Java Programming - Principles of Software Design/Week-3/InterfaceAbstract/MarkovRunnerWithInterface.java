import edu.duke.*;
import java.util.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
        markov.printHashMapInfo();
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        //MarkovZero mz = new MarkovZero();
        //runModel(mz, st, size, 1024);
    
        //MarkovOne mOne = new MarkovOne();
        //runModel(mOne, st, size, 1024);
        
        //MarkovModel mThree = new MarkovModel(3);
        //runModel(mThree, st, size, 1024);
        
        //MarkovFour mFour = new MarkovFour();
        //runModel(mFour, st, size, 1024);

        EfficientMarkovModel emm = new EfficientMarkovModel(6);
        runModel(emm, st, size, 792);

    }

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

    public void testHashMap() {
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        emm.setRandom(42);
        emm.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
        emm.buildMap();
        emm.getRandomText(50);
        emm.printHashMapInfo();
    }

	public void compareMethods() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;

		long startTime = System.nanoTime();
		MarkovModel mTwo = new MarkovModel(2);
		runModel(mTwo, st, size, seed);
		runModel(mTwo, st, size, seed);
		runModel(mTwo, st, size, seed);
		long endTime = System.nanoTime();
		// convert in seconds
		int seconds = (int) ((endTime - startTime) / 1000000000);
		System.out.println("**********************************************");
		
		startTime = System.nanoTime();
		EfficientMarkovModel emm = new EfficientMarkovModel(2);
		emm.setTraining(st);
		emm.setRandom(seed);
		emm.buildMap();
		runModel(emm, st, size, seed);
		runModel(emm, st, size, seed);
		runModel(emm, st, size, seed);
		endTime = System.nanoTime();
		int seconds2 = (int) ((endTime - startTime) / 1000000000);
		System.out.println("**********************************************");
		
		System.out.println("MarkovModel took " + seconds + " seconds");
		System.out.println("EfficientMarkovModel took " + seconds2 + " seconds");
	}
}
