import edu.duke.*;
import java.util.*;

public class GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	private ArrayList<String> usedWords;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "datalong";
	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			usedWords.add(label);
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			usedWords.add(label);
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			usedWords.add(label);
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			usedWords.add(label);
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			usedWords.add(label);
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			usedWords.add(label);
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			usedWords.add(label);
			return randomFrom(timeList);
		}
		if (label.equals("number")){
			usedWords.add(label);
			return ""+myRandom.nextInt(50)+5;
		}
		if (label.equals("verb")){
			usedWords.add(label);
			return randomFrom(verbList);
		}
		if (label.equals("fruit")){
			usedWords.add(label);
			return randomFrom(fruitList);
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
		usedWords.add(sub);
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
		// initialize the usedWords ArrayList
		usedWords = new ArrayList<String>();
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\nnumber of words replaced = " + usedWords.size());
		// clear the usedWords ArrayList for the next run
		usedWords.clear();
	}
	
	public static void main(String[] args) {
	    GladLib gl = new GladLib();
	    gl.makeStory();
	}
}