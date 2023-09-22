import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word); // add one new slot to myWords
                myFreqs.add(1); // add one new slot to myFreqs
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1); // increment the value at the index of the word
            }
        }
    }

    public void tester() {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " 
        + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }

    public int findIndexOfMax() {
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
// Assignment 2 left to do