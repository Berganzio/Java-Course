import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            // For each line, create a LogEntry and store it in the records ArrayList.
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
        System.out.println(filename + " has been read.");
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            String accessTimeStr = le.getAccessTime().toString();
            if (!uniqueIPs.contains(ipAddr) && accessTimeStr.contains(someday)) {
                uniqueIPs.add(ipAddr);
            }
        }
        System.out.println("There are " + uniqueIPs.size() + " unique IPs on " + someday + " date.");
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            int statusCode = le.getStatusCode();
            if (!uniqueIPs.contains(ipAddr) && statusCode >= low && statusCode <= high) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public ArrayList<Integer> printAllHigherThanNum(int num) {
        ArrayList<Integer> higherThanNum = new ArrayList<Integer>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (!higherThanNum.contains(statusCode) && statusCode > num) {
                higherThanNum.add(statusCode);
            }
        }
        return higherThanNum;
    }

    public HashMap<String,Integer> countVisitPerIP() {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!counts.containsKey(ipAddr)) {
                counts.put(ipAddr, 1);
            } else {
                counts.put(ipAddr, counts.get(ipAddr) + 1);
            }
        }
        return counts;    
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
        int max = 0;
        for (String ipAddr : counts.keySet()) {
            int visits = counts.get(ipAddr);
            if (visits > max) {
                max = visits;
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
        ArrayList<String> mostVisits = new ArrayList<String>();
        int max = mostNumberVisitsByIP(counts);
        for (String ipAddr : counts.keySet()) {
            int visits = counts.get(ipAddr);
            if (visits == max) {
                mostVisits.add(ipAddr);
            }
        }
        return mostVisits;
    }

    public HashMap<String,ArrayList<String>> iPsForDays() {
        HashMap<String,ArrayList<String>> ipsForDays = new HashMap<String,ArrayList<String>>();
        for (LogEntry le : records) {
            String accessTimeStr = le.getAccessTime().toString();
            String date = accessTimeStr.substring(4, 10);
            String ipAddr = le.getIpAddress();
            /*
             * If the date key is not in the HashMap, create a new ArrayList of Strings
             * with this ipAddr, and put the date and ArrayList into the HashMap.
             */ 
            if (!ipsForDays.containsKey(date)) {
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(ipAddr);
                ipsForDays.put(date, ips);
            /*
             * If the date key is in the HashMap, get the ArrayList of IPs associated with it,
             * and add the new ipAddr to the ArrayList.
             */
            } else {
                ArrayList<String> ips = ipsForDays.get(date);
                ips.add(ipAddr);
                ipsForDays.put(date, ips);
            }
        }
        return ipsForDays;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipsForDays) {
        String dayWithMostIPVisits = "";
        int max = 0;
        for (String date : ipsForDays.keySet()) {
            int visits = ipsForDays.get(date).size();
            if (visits > max) {
                max = visits;
                dayWithMostIPVisits = date;
            }
        }
        return dayWithMostIPVisits;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipsForDays, String date) {
        ArrayList<String> ipsWithMostVisitsOnDay = new ArrayList<String>();
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (String ipAddr : ipsForDays.get(date)) {
            if (!counts.containsKey(ipAddr)) {
                counts.put(ipAddr, 1);
            } else {
                counts.put(ipAddr, counts.get(ipAddr) + 1);
            }
        }
        int max = mostNumberVisitsByIP(counts);
        for (String ipAddr : counts.keySet()) {
            int visits = counts.get(ipAddr);
            if (visits == max) {
                ipsWithMostVisitsOnDay.add(ipAddr);
            }
        }
        return ipsWithMostVisitsOnDay;
    }
}
