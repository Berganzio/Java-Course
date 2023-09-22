import java.util.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel {

    public int myOrder;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int order) {
        map = new HashMap<String, ArrayList<String>>();
        myOrder = order;
    }

    public void buildMap() {
        for (int i = 0; i <= myText.length() - myOrder; i++) {
            String key = myText.substring(i, i + myOrder);
            if (!map.containsKey(key)) {
                ArrayList<String> follows = new ArrayList<String>();
                int pos = i;
                while (pos < myText.length() - myOrder) {
                    int index = myText.indexOf(key, pos);
                    // if the key is not found
                    if (index == -1) {
                        break;
                    }
                    // if the key is found and the key is not at the end of the text
                    if (index + key.length() < myText.length()) {
                        follows.add(myText.substring(index + key.length(), index + key.length() + 1));
                    }
                    pos = index + 1;
                }
                map.put(key, follows);
            }
        }
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public String getRandomText(int numChars) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - myOrder); // random key
        String key = myText.substring(index, index + myOrder); // random key
        sb.append(key);

        for (int k = 0; k < numChars - myOrder; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
        return map.getOrDefault(key, new ArrayList<String>());
    }

    public void printHashMapInfo() {
        int maxSize = 0;
        for (String key : map.keySet()) {
            maxSize = Math.max(maxSize, map.get(key).size());
        }
        if (map.size() == 0 || maxSize == 0) {
            System.out.println("No keys found in the map or the size of the largest value is 0.");
        } else {
            System.out.println("Number of keys: " + map.size());
            System.out.println("Size of largest value: " + maxSize);
            System.out.println("Keys with maximum size value: ");
            for (String key : map.keySet()) {
                if (map.get(key).size() == maxSize) {
                    System.out.println("This is the biggest value: " + "'" + key + "'.");
                }
            }
        }
    }

    public String toString() {
        return "EfficientMarkovModel of order " + myOrder;
    }

    public static void main(String[] args) {
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        emm.setRandom(531);
        FileResource fr = new FileResource();
        String st = fr.asString();
        String text = st.replace('\n', ' ');
        emm.setTraining(text);
        emm.buildMap();
        emm.printHashMapInfo();
    }
}
