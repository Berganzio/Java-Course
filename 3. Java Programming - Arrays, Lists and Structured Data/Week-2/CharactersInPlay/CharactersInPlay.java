import java.util.ArrayList;
import edu.duke.FileResource;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;

    public void update(String person) {
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            counts.add(1);
        } else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }

    public void findAllCharacters() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
        FileResource resource = new FileResource();
        for (String line : resource.lines()) {
            int index = line.indexOf(".");
            if (index != -1) {
                String name = line.substring(0, index);
                update(name);
            }
        }
    }

    public void tester() {
        findAllCharacters();
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) > 1) {
                System.out.println(names.get(i) + " " + counts.get(i));
            }
        }
        charactersWithNumParts(10, 15);
        CharacterMostSpeaking();
    }

    public void charactersWithNumParts(int num1, int num2) {
        int counter = 0;
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                counter += 1;
                System.out.println(names.get(i) + " " + counts.get(i));
            }
        }
        System.out.println("The number of characters with speaking parts between " + num1 + " and " + num2 + " is " + counter);
    }

    public void CharacterMostSpeaking() {
        int maxIndex = 0;
        int penultimateIndex = 0;
        int thirdUltimateIndex = 0;
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) > counts.get(maxIndex)) {
                maxIndex = i;
            }
            if (counts.get(i) < counts.get(maxIndex) && counts.get(i) > counts.get(penultimateIndex)) {
                penultimateIndex = i;
            }
            if (counts.get(i) < counts.get(penultimateIndex) && counts.get(i) > counts.get(thirdUltimateIndex)) {
                thirdUltimateIndex = i;
            }
        }
        System.out.println("The most speaking character is " + names.get(maxIndex) + " " + counts.get(maxIndex));
        System.out.println("The second most speaking character is " + names.get(penultimateIndex) + " " + counts.get(penultimateIndex));
        System.out.println("The third most speaking character is " + names.get(thirdUltimateIndex) + " " + counts.get(thirdUltimateIndex));
    }

    public static void main(String[] args) {
        CharactersInPlay charactersInPlay = new CharactersInPlay();
        charactersInPlay.tester();
    }
}
