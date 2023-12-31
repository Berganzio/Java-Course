import java.util.*;

public class DifferentSorters {
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        /*for(QuakeEntry qe: list) {
            System.out.println(qe);
        }*/
        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }    

    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        /* for(QuakeEntry qe: list) {
            System.out.println(qe);
        } */
        int position = 500;
        System.out.println("Print quake entry in position " + position);
        System.out.println(list.get(position));
    }

    public void sortByLastWordInTheTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        /* for (QuakeEntry qe : list) {
            System.out.println(qe);
        } */
        int position = 500;
        System.out.println("Print quake entry in position " + position);
        System.out.println(list.get(position));
    }

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public static void main(String[] args) {
        DifferentSorters ds = new DifferentSorters();
        //ds.sortWithCompareTo();
        //ds.sortByTitleAndDepth();
        //ds.sortByMagnitude();
        //ds.sortByDistance();
        ds.sortByLastWordInTheTitleThenByMagnitude();
    }
}
