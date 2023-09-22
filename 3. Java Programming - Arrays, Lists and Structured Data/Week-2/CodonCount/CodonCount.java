import edu.duke.*;
import java.util.*;

public class CodonCount {
    // create a hashmap <key, value> where key is the codon and value is the count
    private HashMap<String,Integer> codonMap;

    // constructor to initialize the hashmap
    public CodonCount() {
        codonMap = new HashMap<String,Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        int index = start;
        while (index < dna.length()) {
            // if there are less than 3 characters left, break
            if (index + 3 > dna.length()) {
                break;
            }
            String codon = dna.substring(index, index + 3);
            if (codonMap.containsKey(codon)) {
                // increment the count
                codonMap.put(codon, codonMap.get(codon) + 1);
            } else {
                // add the codon to the map
                codonMap.put(codon, 1);
            }
            // move to the next codon
            index += 3; 
        }
    }

    public String getMostCommonCodon() {
        // set the max count to the smallest possible integer
        int maxCount = Integer.MIN_VALUE; 
        String mostCommonCodon = "";
        for (String codon : codonMap.keySet()) {
            // get the count for the codon
            int count = codonMap.get(codon); 
            if (count > maxCount) {
                maxCount = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start,  int end) {
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            // if the count is between start and end, print it
            if (count >= start && count <= end) { 
                System.out.println(codon + "\t" + count);
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        // read the dna string, remove whitespace and convert to uppercase
        String dna = fr.asString().trim().toUpperCase();
        for (int start = 0; start < 3; start++) {
            buildCodonMap(start, dna);
            System.out.println("Reading frame starting with " + start +
            " results in " + codonMap.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommonCodon +
            " with count " + codonMap.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(1, 5);
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
