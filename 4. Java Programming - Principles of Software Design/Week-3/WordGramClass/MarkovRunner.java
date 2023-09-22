/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

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

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(7); 
        runModel(markovWord, st, 100, 953);
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
        String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        runModel(markovWord, st, 50, 42);
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 42;
        long startTime = System.nanoTime();
        MarkovWord markovWord = new MarkovWord(2);
        runModel(markovWord, st, size, seed);
        long endTime = System.nanoTime();
        System.out.println("MarkovWord took " + (endTime - startTime) + " nanoseconds");
        startTime = System.nanoTime();
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        runModel(efficientMarkovWord, st, size, seed);
        endTime = System.nanoTime();
        System.out.println("EfficientMarkovWord took " + (endTime - startTime) + " nanoseconds");
    }

    public static void main(String[] args) {
        // create an efficientmarkovmodel of order 3 with random seed 371 and print the hashmap info
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        emw.setRandom(65);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        emw.setTraining(st);
        // build the hashmap
        emw.buildMap();
        // print the hashmap info
        emw.printHashMapInfo();
        
    }
}
