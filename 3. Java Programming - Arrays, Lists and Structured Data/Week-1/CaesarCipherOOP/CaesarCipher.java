public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar)); // if not found returns -1
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                sb.setCharAt(i, Character.isLowerCase(currChar) ? Character.toLowerCase(newChar) : newChar);
                /* This is the same as:
                if (Character.isLowerCase(currChar)) {
                    sb.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    sb.setCharAt(i, newChar);
                } */
            }
        }
        return sb.toString();
    }

    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}