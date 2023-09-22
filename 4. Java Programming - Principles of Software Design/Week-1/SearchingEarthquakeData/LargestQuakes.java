import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        ArrayList<QuakeEntry> largest = getLargest(quakeData, 50);
        System.out.println("read data for " + quakeData.size());
        for (QuakeEntry qe : largest) {
            System.out.println(qe);
        }
        System.out.println("These are the " + largest.size() + " largest earthquakes in the data set.");
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int indexOfLargest = 0;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getMagnitude() > data.get(indexOfLargest).getMagnitude()) {
                indexOfLargest = i;
            }
        }
        return indexOfLargest;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for (int i = 0; i < howMany; i++) {
            int indexOfLargest = indexOfLargest(copy);
            ret.add(copy.get(indexOfLargest));
            copy.remove(indexOfLargest);
        }
        return ret;
    }
}
