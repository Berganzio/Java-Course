import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] MyText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        MyText = text.split("\\s+");
    }

    public int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - target.length(); i++) {
            WordGram wg = new WordGram(words, i, target.length());
            if (wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }


    private ArrayList<String> getFollows(WordGram kGram) {
        // create an ArrayList of all the single words that immediately follow the key in the training text
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < MyText.length) {
            // find the first match of key in training text
            int start = indexOf(MyText, kGram, pos);
            // if the key is not in the training text
            if (start == -1) {
                break;
            }
            // if the key is at the end of the training text
            if (start + kGram.length() >= MyText.length) { 
                break;
            }
            // add the word that follows the key to the ArrayList
            String next = MyText[start + kGram.length()];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }


    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(MyText.length - myOrder);
        WordGram key = new WordGram(MyText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
}
