import java.io.IOException;
import edu.duke.*;

public class TestCaesarCipher {
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

    private void simpleTests() {
        FileResource fr = new FileResource();
        String text = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(text);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        breakCaesarCipher(encrypted);
    }

    private void breakCaesarCipher(String input) {
        int[] counts = countLetters(input);
        int maxIndex = maxIndex(counts);
        int decryptionKey = maxIndex - 4;
        if (maxIndex < 4) {
            decryptionKey = 26 - (4 - maxIndex); 
        }
        CaesarCipher cc = new CaesarCipher(decryptionKey);
        System.out.println("Decrypted with broken key: " + cc.decrypt(input));
    }
}