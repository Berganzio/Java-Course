import java.util.*;

public class MarkovFour extends AbstractMarkovModel {
	
	public MarkovFour() {
		myRandom = new Random();
	}
	
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        sb.append(myText.charAt(index));

        for (int k = 0; k < numChars - 4; k++) {
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

    public String toString() {
        return "MarkovModel of order 4.";    
    }
}
