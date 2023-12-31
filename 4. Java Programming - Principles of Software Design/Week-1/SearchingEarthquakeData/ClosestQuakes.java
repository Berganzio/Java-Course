
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i < howMany; i++) {
            int minIndex = 0;
            for (int j = 1; j < copy.size(); j++) {
                QuakeEntry quake = copy.get(j);
                Location loc = quake.getLocation();
                if (loc.distanceTo(current) < copy.get(minIndex).getLocation().distanceTo(current)) {
                    minIndex = j;
                }
            }
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry qe = close.get(k);
            double distanceInMeters = jakarta.distanceTo(qe.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,qe); // distance in km
        }
        System.out.println("number found: "+close.size());
    }
}
