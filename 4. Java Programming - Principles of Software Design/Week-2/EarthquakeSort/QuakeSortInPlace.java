import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
    }

    public void onePassBuBBleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i=0; i< quakeData.size()-1; i++) {
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()) {
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qj = quakeData.get(i+1);
                quakeData.set(i,qj);
                quakeData.set(i+1,qi);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            onePassBuBBleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i=0; i< quakes.size()-1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int numSorted = 0;
        for (int i=0; i< in.size(); i++) {
            onePassBuBBleSort(in, i);
            numSorted++;
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        System.out.println("Number of passes: " + numSorted);
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numSorted = 0;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            numSorted++;
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        System.out.println("Number of passes: " + numSorted);
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "../EfficientSort/data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
}
