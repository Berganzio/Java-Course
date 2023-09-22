import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    // initialize an array of CaesarCipher objects
    CaesarCipher[] ciphers;
    
    public VigenereCipher(int[] key) {
        // initialize the array of CaesarCipher objects with the key passed in
        ciphers = new CaesarCipher[key.length];
        /* for each element in the key array, create a new CaesarCipher object
           with that key and add it to the ciphers array */
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            // get the index with the remainder of i divided by the length of the ciphers array
            int cipherIndex = i % ciphers.length; 
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
}
