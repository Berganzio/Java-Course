
/**
 * Write a description of findABC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class findABC {
    public void printABCSubstrings(String input) {
        int index = input.indexOf("abc");
        System.out.println("index before updating " + index);
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            String found = input.substring(index + 1, index + 4);
            System.out.println(found);
            index = input.indexOf("abc", index + 3);
            System.out.println("index after updating " + index);

        }
    }

        public void test() { 
            printABCSubstrings("abcabcabcabca");
    }
}
