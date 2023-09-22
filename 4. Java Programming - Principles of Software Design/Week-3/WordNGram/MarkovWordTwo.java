import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index+1];
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		    if (follows.size() == 0) {
		        break;
		    }
		    //System.out.println("Key: " + key + ", Follows: " + follows);
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while (pos < myText.length) {
	        int start = indexOf(myText, key1, key2, pos);
	        if (start == -1) {
	            break;
	        }
	        if (start + key1.length() >= myText.length) {
	            break;
	        }
	        String next = myText[start+2];
	        follows.add(next);
	        pos = start + 1;
	    }
	    return follows;					
    }

	public void testGetFollows() {
		MarkovWordTwo mwt = new MarkovWordTwo();
		mwt.setTraining("this is just a test yes this is a simple test");
		ArrayList<String> follows = mwt.getFollows("this", "is");
		System.out.println(follows);
	}

	private int indexOf(String[] words, String target1, String target2, int start) {
		for (int i = start; i < words.length; i++) {
			if (words[i].equals(target1) && words[i+1].equals(target2)) {
				return i;
			}
		}
		return -1;
	}

		private void testIndexOf() {
			String text = "this is just a test yes this is a simple test";
			String[] words = text.split("\\s+");
			System.out.println(indexOf(words, "this", "is", 0));
			System.out.println(indexOf(words, "this", "is", 3));
			System.out.println(indexOf(words, "frog", "is", 0));
		}
}
