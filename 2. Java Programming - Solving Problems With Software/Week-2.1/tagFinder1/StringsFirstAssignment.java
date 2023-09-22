import edu.duke.*;
import java.io.*;

public class StringsFirstAssignment {
    public static void main (String[] args) {

        Part1 part1 = new Part1();
        part1.testSimpleGene();

        Part2 part2 = new Part2();
        part2.testSimpleGene();

        Part3 part3 = new Part3();
        System.out.println(part3.twoOccurrences("by", "A story by Abby Long"));
        System.out.println(part3.twoOccurrences("a", "banana"));
        System.out.println(part3.twoOccurrences("atg", "ctgtatgta"));
        
        Part4 part4 = new Part4();
        part4.printUrls("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}

class Part1 {

    public String findSimpleGene(String dna) {
        int start = dna.indexOf("ATG");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("TAG", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String a = "CCCATGGGGTTTAAATAATAATAGGAGAGAGAGAGAGAGTTT";
        String ap = "ATGGGGTTTAAATAATAATAG";
        String result = findSimpleGene(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }
}

// Part 2

class Part2 {

    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String dnaUpper = dna.toUpperCase();
        int start = dnaUpper.indexOf(startCodon);
        if (start == -1) {
            return "";
        }
        int stop = dnaUpper.indexOf(stopCodon, start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }

    public void testSimpleGene() {
        String a = "CCCATGGGGTTTAAATAATAATAGGAGAGAGAGAGAGAGTTT";
        String ap = "ATGGGGTTTAAATAATAATAG";
        String result = findSimpleGene(a, "ATG", "TAG");
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }
}

// part 3

class Part3 {

    public boolean twoOccurrences(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        if (first == -1) {
            return false;
        }
        int second = stringb.indexOf(stringa, first+1);
        if (second == -1) {
            return false;
        }
        return true;
    }
}

// part 4

class Part4 {

    public void printUrls(String url) {
        URLResource ur = new URLResource(url);                     // convert the string into a URLResource object
        for (String word : ur.words()) {                           // iterate over the words in the URLResource object
            String wordLower = word.toLowerCase();                 // convert the word to lowercase
            int position = wordLower.indexOf("youtube.com");   // find the position of "youtube.com"
            if (position != -1) {                                  // if the word contains "youtube.com"
                int begin = word.lastIndexOf("\"", position);  // find the last " before position
                int end = word.indexOf("\"", position+1);      // find the first " after position
                System.out.println(word.substring(begin+1, end));  // print the substring between the two "
            }
        }
    }
}
