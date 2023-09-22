import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> usedWords;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		myMap = new HashMap<String, ArrayList<String>>();
		String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
		for (String s : labels) {
			ArrayList<String> list = readIt(source + "/" + s + ".txt");
			myMap.put(s, list);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String category) {
		if (category.equals("number")) {
			return "" + myRandom.nextInt(50) + 5;
		}
		if (myMap.containsKey(category)) {
			usedWords.add(category);
			return randomFrom(myMap.get(category));
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		while (usedWords.contains(sub)) {
			sub = getSubstitute(w.substring(first+1,last));
		}
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		// one or more spaces for splitting the string s
		for(String w : s.split("\\s+")){
		// in regex "\\s+" means one or more spaces
			if (charsWritten + w.length() > lineWidth){ // if the word w is too long
				System.out.println(); // print a new line
				charsWritten = 0; // reset the counter
			} // else
			System.out.print(w+" "); // print the word w
			charsWritten += w.length() + 1; // update the counter
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			// URLResource is a class in edu.duke package that represents a resource on the web
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
		// initialize usedWords
		usedWords = new ArrayList<String>();
		System.out.println("\n");
		String story = fromTemplate("datalong/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n\nReplaced words: " + usedWords.size());
	}

	public int totalWordsInMap() {
		int total = 0;
		for (String s : myMap.keySet()) {
			total += myMap.get(s).size();
		}
		return total;
	}

	public int totalWordsConsidered() {
		int total = 0;
		for (String s : usedWords) {
			total += myMap.get(s).size();
		}
		usedWords.clear();
		return total;
	}
	
	public static void main(String[] args) {
	    GladLibMap glm = new GladLibMap();
	    glm.makeStory();
		System.out.println("Total words in the map: " + glm.totalWordsInMap());
		System.out.println("Total words considered: " + glm.totalWordsConsidered());
	}
}