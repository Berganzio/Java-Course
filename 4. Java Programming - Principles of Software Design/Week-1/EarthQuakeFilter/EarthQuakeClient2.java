import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        Filter f1 = new MagnitudeFilter(3.5, 4.5);
        Filter f2 = new DepthFilter(-55000.0, -20000.0);
        //Location tokyo = new Location(35.42, 139.43);
        //Location denver = new Location(39.7392, -104.9903);
        //Filter f3 = new DistanceFilter(denver, 1000000.0);
        //Filter f4 = new PhraseFilter("end", "a");
        //ArrayList<QuakeEntry> m7  = filter(list, f);
        //ArrayList<QuakeEntry> m7_1  = filter(list, f1);
        ArrayList<QuakeEntry> m7_1  = filter(list, f1);
        m7_1 = filter(m7_1, f2);
        for (QuakeEntry qe: m7_1) {
            System.out.println(qe);
        }
        System.out.println("Found " + m7_1.size() + " quakes that match that criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // Print all the earthquakes
        /* for (QuakeEntry qe : list) {
            System.out.println(qe);
        } */
        
        // Create a MatchAllFilter and add three filters
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        Filter f2 = new DepthFilter(-180000.0, -30000.0);
        Filter f3 = new PhraseFilter("any", "o");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        // Use filter(list, maf) to use all three filters and print out the resulting list of earthquakes
        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }

        // Print the total number of earthquakes
        System.out.println("Total earthquakes: " + filteredList.size());

        // call the getName method on maf and print out the names of filters that passed the test
        System.out.println("Filters used are: " + maf.getName());
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // Print all the earthquakes
        /* for (QuakeEntry qe : list) {
            System.out.println(qe);
        } */
        
        // Create a MatchAllFilter and add three filters
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        //Location oklahoma = new Location(36.1314, -95.9372);
        Location billund = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(billund, 3000000.0);
        Filter f3 = new PhraseFilter("any", "e");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        // Use filter(list, maf) to use all three filters and print out the resulting list of earthquakes
        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        // Print the total number of earthquakes
        System.out.println("Total earthquakes: " + filteredList.size());
    }
}
