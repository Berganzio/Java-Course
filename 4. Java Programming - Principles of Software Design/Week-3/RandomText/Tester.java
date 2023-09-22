import java.util.ArrayList;
import edu.duke.*;

public class Tester {

    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows);
    }

    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println("Size: " + follows.size());
    }
}
