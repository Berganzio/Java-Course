import edu.duke.*;
import java.io.*;

public class TestCaesarCipherTwo {
    private int [] countLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char currChar = Character.toLowerCase(message.charAt(i));
            int idx = alphabet.indexOf(currChar); // if not found returns -1
            if (idx != -1) {
                counts[idx]++;
            }
        }
        return counts; // return the counts array with the counts for each letter
    }

    private int maxIndex(int [] values) {
        // return the index position of the largest element in values
        int maxIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String text = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        
        String encrypted = cc.encrypt(text);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        breakCaesarCipher(encrypted);
    }

    public void breakCaesarCipher(String input) {
        String firstHalf = halfOfString(input, 0);
        String secondHalf = halfOfString(input, 1);
        int[] firstHalfCounts = countLetters(firstHalf);
        int[] secondHalfCounts = countLetters(secondHalf);
        int firstHalfMaxIndex = maxIndex(firstHalfCounts);
        int secondHalfMaxIndex = maxIndex(secondHalfCounts);
        int firstHalfDecryptionKey = firstHalfMaxIndex - 4;
        int secondHalfDecryptionKey = secondHalfMaxIndex - 4;
        if (firstHalfMaxIndex < 4) {
            firstHalfDecryptionKey = 26 - (4 - firstHalfMaxIndex); 
        }
        if (secondHalfMaxIndex < 4) {
            secondHalfDecryptionKey = 26 - (4 - secondHalfMaxIndex); 
        }
        CaesarCipherTwo cc2 = new CaesarCipherTwo(firstHalfDecryptionKey, secondHalfDecryptionKey);
        System.out.println("Keys used to encrypt: " + firstHalfDecryptionKey + ", " + secondHalfDecryptionKey);
        System.out.println("Decrypted with broken key: " + cc2.decrypt(input));
    }
}
