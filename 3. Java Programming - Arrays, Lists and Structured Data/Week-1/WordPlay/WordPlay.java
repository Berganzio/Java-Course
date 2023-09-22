
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        if (vowels.indexOf(Character.toLowerCase(ch)) != -1) {
            return true;
        }
        return false;
    }

    public void testIsVowel(String input) {
        for (int i = 0; i < input.length(); i++) {
            System.out.println(isVowel(input.charAt(i)));
        }
    }

    public String replaceVowel(String input, char ch) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            if (isVowel(input.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString(); // convert StringBuilder to String
    }

    public void testReplaceVowel() {
        System.out.println(replaceVowel("Hello World", '*'));
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(phrase.charAt(i)) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, '*');
                } else {
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }

    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));}
}
