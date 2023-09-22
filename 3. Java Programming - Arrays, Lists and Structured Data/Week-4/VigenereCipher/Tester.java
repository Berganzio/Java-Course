import edu.duke.*;

public class Tester {
    private void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(15);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
    }

    private void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker('a');
        FileResource fr = new FileResource();
        String message = fr.asString();
        String decrypted = cc.decrypt(message);
        System.out.println(decrypted);
    }

    private void testVigenereCipher() {
        int[] key = {17, 14, 12, 4}; // "rome" in numbers
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = vc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }

    private void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        //t.testCaesarCipher();
        //t.testCaesarCracker();
        //t.testVigenereCipher();
        t.testVigenereBreaker();
    }
}
