
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder sb = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar)); // if not found returns -1
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }

    public void testCaesar() {
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder sb = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar;
                if (i % 2 == 0) {
                    newChar = shiftedAlphabet1.charAt(idx);
                } else {
                    newChar = shiftedAlphabet2.charAt(idx);
                }
                if (Character.isLowerCase(currChar)){ 
                    newChar = Character.toLowerCase(newChar);
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }

    public void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6));
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        cc.testCaesar();
        cc.testEncryptTwoKeys();
    }
}
