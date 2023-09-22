import edu.duke.*;
import java.io.File;

public class WordLengths {

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }

    public void countWordLengths(FileResource resource, Integer [] counts) {
        // initialize counts array
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        // iterate over words in resource
        for (String word : resource.words()) {
            int wordLength = word.length();
            // if first char is not a letter subtract 1 from wordLenght
            if (!Character.isLetter(word.charAt(0))) {
                wordLength--;
            }
            // if last char is not a letter subtract 1 from wordLenght
            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                wordLength--;
            }
            System.out.println(word + " " + wordLength);
            /* if the word is equal or larger than the last
               index of the counts array, count them as the largest
               size represented in the counts array */
            if (wordLength >= counts.length) {
                counts[counts.length - 1]++;
            } else if (wordLength > 0) {
                counts[wordLength]++;
            }
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println("Words of length " + i + ": " + counts[i]);
        }
    }

    public int indexOfMax(Integer [] values) {
        int maxIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        Integer [] counts = new Integer[31];
        countWordLengths(resource, counts);
        System.out.println("Index of max value: " + indexOfMax(counts));
    }
}
