
/**
 * Write a description of Parser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class mainParser {
    public static void main(String[] args) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Parser p = new Parser();
        p.bigExporters(parser, "$999,999,999,999");
    }
}

class Parser {

    public void tester() {
        // create a FileResource obkect
        FileResource fr = new FileResource();
        // create a CSVParser object
        CSVParser parser = fr.getCSVParser();
    }

    public void countryInfo(CSVParser parser, String country) {
        // iterate over the records in the CSVParser
        for (CSVRecord record : parser) {
            // look at the "Country" column
            String countryName = record.get("Country");
            // check if it contains the country
            if (countryName.contains(country)) {
                // format the print with country name, exports, and export value
                System.out.println(countryName + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        int exportCount = 0;
        // iterate over the records in the CSVParser
        for (CSVRecord record : parser) {
            // look at the "Exports" column
            String exportItems = record.get("Exports");
            // check if it contains the exportItem1 and exportItem2
            if (exportItems.contains(exportItem1) && exportItems.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int itemCounter = 0;
        for (CSVRecord record : parser) {
            String column = record.get("Exports");
            if (column.contains(exportItem)) {
                itemCounter++;
            }
        }
        return itemCounter;
    }

    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }
}