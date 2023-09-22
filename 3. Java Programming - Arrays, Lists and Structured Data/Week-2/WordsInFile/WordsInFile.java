import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordsInFile {
    // create a new HashMap to store the words in files
    private HashMap<String, ArrayList<String>> map;

    // create a constructor to initialize the hash map
    public WordsInFile() {
        map = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        // create a new FileResource
        FileResource fr = new FileResource(f);
        // iterate over the words in the file
        for (String word : fr.words()) {
            /* if the word is not in the map, add it by creating a new list
               with the file name and adding it to the map */
            if (!map.containsKey(word)) {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(f.getName());
                map.put(word, fileList);
            } else {
                // if the word is in the map, add the file name to the list
                ArrayList<String> fileList = map.get(word);
                if (!fileList.contains(f.getName())) {
                    fileList.add(f.getName());
                    map.put(word, fileList);
                }
            }
        }
    }

    void buildWordFileMap() {
        // clear the map
        map.clear();
        // create a new DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // iterate over the files in the directory
        for (File f : dr.selectedFiles()) {
            // add the words from the file to the map
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        // initialize the max number of files to 0
        int max = 0;
        // iterate over the words in the map
        for (String word : map.keySet()) {
            // get the number of files for the word
            int num = map.get(word).size();
            // if the number of files is greater than the max, update the max
            if (num > max) {
                max = num;
            }
        }
        // return the max number of files
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        // create a new ArrayList to store the words
        ArrayList<String> words = new ArrayList<String>();
        // iterate over the words in the map
        for (String word : map.keySet()) {
            // get the number of files for the word
            int num = map.get(word).size();
            // if the number of files is equal to the number, add the word
            if (num == number) {
                words.add(word);
            }
        }
        // return the ArrayList of words
        return words;
    }

    public void printFilesIn(String word) {
    // get the ArrayList of files for the word
    ArrayList<String> fileList = map.get(word);
    // check if fileList is null
    if (fileList == null) {
        System.out.println("The word " + word + " does not appear in any files.");
        return;
    }
    // iterate over the files in the list
    for (String file : fileList) {
        // print the file name
        System.out.println(file);
    }
}

    public void tester() {
        // build the map
        buildWordFileMap();
        // get the max number of files
        int max = maxNumber();
        // print the max number of files
        System.out.println("The greatest number of files a word appears in is " + max);
        // get the words in the max number of files
        ArrayList<String> words = wordsInNumFiles(4);
        // print the words
        System.out.println("The count of the words that occurs in all seven files is " + words.size());
        // print the files in the word
        String word = "laid";
        System.out.println("The word " + "'" + word + "'" + " appears in the following files:");
        printFilesIn(word);
    }

    public static void main(String[] args) {
        WordsInFile wif = new WordsInFile();
        wif.tester();
    }
}
