import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            answer.append(message.charAt(i));
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W+")) { // \\W+ means one or more non-word characters
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }    
        }
        return count;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c)) {
                    map.put(c, 1);
                } else {
                    map.put(c, map.get(c)+1);
                }
            }
        }
        int max = 0;
        char answer = ' ';
        for (char c : map.keySet()) {
            if (map.get(c) > max) {
                max = map.get(c);
                answer = c;
            }
        }
        return answer;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int totalWords = 0;
        String answer = "";
        int keyLength = 0;
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > totalWords) {
                totalWords = count;
                answer = decrypted;
                keyLength = i;
            }
        }
        // print the number of total words
        System.out.println("Total real words: " + totalWords);
        // print the key length
        System.out.println("Key length: " + keyLength + "\n\n");
        return answer;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String answer = "";
        String language = "";
        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int count = countWords(decrypted, dictionary);
            if (count > max) {
                max = count;
                answer = decrypted;
                language = lang;
            }
        }
        System.out.println("Language selected: " + language);
        System.out.println("Total real words: " + max + "\n\n");
        System.out.println(answer);
    }
    
    public void breakVigenere () {
        // create a new FileResource to read the encrypted message
        FileResource fr = new FileResource();
        // read the entire contents of the file into a String
        String encrypted = fr.asString();
        // create a new FileResource to read from the English dictionary file
        //FileResource fr2 = new FileResource("dictionaries/English"); 
        // read the contents of that file into a HashSet of Strings
        //HashSet<String> dictionary = readDictionary(fr2); 
        // use the breakForLanguage method to decrypt the encrypted message
        //String decrypted = breakForLanguage(encrypted, dictionary);
        // use tryKeyLength method to find the key for the message you read in
        //int[] key = tryKeyLength(encrypted, 38, 'e');
        // create a new VigenereCipher, passing in the key that tryKeyLength found for you
        //VigenereCipher vc = new VigenereCipher(key);
        // use vc.decrypt to decrypt the encrypted message
        //String decrypted = vc.decrypt(encrypted);
        // count the number of real words that are in it
        //int count = countWords(decrypted, dictionary);
        // print the number of real words and print the decrypted message
        //System.out.println("Total real words: " + count + "\n");
        // print out the decrypted message
        //System.out.println(decrypted);

        /* create a new HashMap of String to HashSet of Strings, that
           will map each language name to the set of words in its dictionary */
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        // read each of the dictionaries into that HashMap
        String[] languagesNames = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (String lang : languagesNames) {
            FileResource fr3 = new FileResource("dictionaries/" + lang);
            HashSet<String> dict = readDictionary(fr3);
            languages.put(lang, dict);
        }
        // use breakForAllLangs to decrypt the encrypted message
        breakForAllLangs(encrypted, languages);
    }
}
