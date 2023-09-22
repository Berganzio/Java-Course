import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] MyText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        MyText = text.split("\\s+");
    }

    public int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - myOrder; i++) {
            WordGram wg = new WordGram(words, i, myOrder);
            if (wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(MyText.length - myOrder);
        WordGram key = new WordGram(MyText, index, myOrder);
        sb.append(key.toString());
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

    public void buildMap() {
        for (int i = 0; i <= MyText.length - myOrder; i++) {
            WordGram kGram = new WordGram(MyText, i, myOrder);
            if (!myMap.containsKey(kGram)) {
                myMap.put(kGram, new ArrayList<String>());
            }
            if (i + myOrder < MyText.length) {
                String next = MyText[i + myOrder];
                myMap.get(kGram).add(next);
            }
        }
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        return myMap.get(kGram);
    }

    public void printHashMapInfo() {
        System.out.println("Number of keys: " + myMap.size());
        int maxSize = 0;
        ArrayList<WordGram> maxKeys = new ArrayList<WordGram>();
        for (WordGram kGram : myMap.keySet()) {
            int size = myMap.get(kGram).size();
            if (size > maxSize) {
                maxSize = size;
                maxKeys.clear();
                maxKeys.add(kGram);
            } else if (size == maxSize) {
                maxKeys.add(kGram);
            }
        }
        System.out.println("Largest value in the HashmyMap: " + maxSize);
        System.out.println("Keys with the maximum size value: ");
        for (WordGram kGram : maxKeys) {
            System.out.println(kGram.toString());
        }
    }
}
