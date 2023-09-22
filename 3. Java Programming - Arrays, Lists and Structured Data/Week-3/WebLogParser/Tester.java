import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs in the selected file.");
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.uniqueIPVisitsOnDay("Sep 24");
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        int uniqueIPs = la.countUniqueIPsInRange(low, high);
        System.out.println("There are " + uniqueIPs + " IPs for status code in the range " + low + " to " + high);
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int num = 400;
        la.printAllHigherThanNum(num);
        for (int statusCode : la.printAllHigherThanNum(num)) {
            System.out.println(statusCode);
        }
    }
    
    public void testCountVisitPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitPerIP();
        int mostNumberVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("The most number of visits by IP is " + mostNumberVisits);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitPerIP();
        ArrayList<String> IPsMostVisits = la.iPsMostVisits(counts);
        System.out.println("The IPs that have the most visits are " + IPsMostVisits);
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> IPsForDays = la.iPsForDays();
        System.out.println("The IPs for each day are " + IPsForDays);
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> IPsForDays = la.iPsForDays();
        String dayWithMostIPVisits = la.dayWithMostIPVisits(IPsForDays);
        System.out.println("The day with the most IP visits is " + dayWithMostIPVisits);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> IPsForDays = la.iPsForDays();
        String someday = "Sep 30";
        ArrayList<String> IPsMostVisitsOnDay = la.iPsWithMostVisitsOnDay(IPsForDays, someday);
        System.out.println("The IPs with most visits on " + someday + " are " + IPsMostVisitsOnDay);
    }
    
    public void testCountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs in the selected file.");
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testLogAnalyzer();
        t.testUniqueIP();
        t.testUniqueIPVisitsOnDay();
        t.testCountUniqueIPsInRange();
        t.testPrintAllHigherThanNum();
        t.testCountVisitPerIP();
        t.testMostNumberVisitsByIP();
        t.testIPsMostVisits();
        t.testIPsForDays();
        t.testDayWithMostIPVisits();
        t.testIPsWithMostVisitsOnDay();
        t.testCountUniqueIPs();
    }
}
