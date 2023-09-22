
/**
 * Write a description of coldestParser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class coldestParser {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        // iterate over the records in the CSVParser
        for (CSVRecord record : parser) {
            // check if coldestSoFar is null
            if (coldestSoFar == null) {
                // if so, initialize it to current record
                coldestSoFar = record;
            } else {
                // otherwise, compare current record to coldestSoFar
                double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                // if current record is colder
                if (currentTemp < coldestTemp) {
                    // update coldestSoFar to current record
                    coldestSoFar = record;
                }
            }
        }
        return coldestSoFar;
    }

    public void testColdestHourInFile() {
        // create a FileResource object
        FileResource fr = new FileResource();
        // create a CSVParser object
        CSVParser parser = fr.getCSVParser();
        // call coldestHourInFile on the CSVParser object
        CSVRecord coldest = coldestHourInFile(parser);
        // print the coldest temperature
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
    }

    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String coldestFileName = null;
        // iterate over files
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // create a FileResource object
            FileResource fr = new FileResource(f);
            // create a CSVParser object
            CSVParser parser = fr.getCSVParser();
            // call coldestHourInFile on the CSVParser object
            CSVRecord currentRecord = coldestHourInFile(parser);
            // check if coldestSoFar is null
            if (coldestSoFar == null) {
                // if so, initialize it to current record
                coldestSoFar = currentRecord;
                coldestFileName = f.getName();
            } else {
                // otherwise, compare current record to coldestSoFar
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                // if current record is colder
                if (currentTemp < coldestTemp) {
                    // update coldestSoFar to current record
                    coldestSoFar = currentRecord;
                    coldestFileName = f.getName();
                }
            }
        }
        return coldestFileName;
    }

    public void testFileWithColdestTemperature() {
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestFileName);
        // create a FileResource object
        FileResource fr = new FileResource("nc_weather/2013/" + coldestFileName);
        // create a CSVParser object
        CSVParser parser = fr.getCSVParser();
        // call coldestHourInFile on the CSVParser object
        CSVRecord coldest = coldestHourInFile(parser);
        // print the coldest temperature
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        // print all the temperatures on the coldest day
        System.out.println("All the temperatures on the coldest day were:");
        // create a CSVParser object
        parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        // iterate over the records in the CSVParser
        for (CSVRecord record : parser) {
            // check if lowestSoFar is null
            if (lowestSoFar == null) {
                // if so, initialize it to current record
                lowestSoFar = record;
            } else {
                // otherwise, compare current record to lowestSoFar
                String currentHumidity = record.get("Humidity");
                String lowestHumidity = lowestSoFar.get("Humidity");
                // if current record is lower
                if (!currentHumidity.equals("N/A") && !lowestHumidity.equals("N/A")) {
                    if (Integer.parseInt(currentHumidity) < Integer.parseInt(lowestHumidity)) {
                        // update lowestSoFar to current record
                        lowestSoFar = record;
                    }
                }
            }
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // create a FileResource object
            FileResource fr = new FileResource(f);
            // create a CSVParser object
            CSVParser parser = fr.getCSVParser();
            // call lowestHumidityInFile on the CSVParser object
            CSVRecord currentRecord = lowestHumidityInFile(parser);
            // check if lowestSoFar is null
            if (lowestSoFar == null) {
                // if so, initialize it to current record
                lowestSoFar = currentRecord;
            } else {
                // otherwise, compare current record to lowestSoFar
                String currentHumidity = currentRecord.get("Humidity");
                String lowestHumidity = lowestSoFar.get("Humidity");
                // if current record is lower
                if (!currentHumidity.equals("N/A") && !lowestHumidity.equals("N/A")) {
                    if (Integer.parseInt(currentHumidity) < Integer.parseInt(lowestHumidity)) {
                        // update lowestSoFar to current record
                        lowestSoFar = currentRecord;
                    }
                }
            }
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInManyFile() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public Double averageTemperatureInFile(CSVParser parser) {
        Double sum = 0.0;
        Integer count = 0;
        // iterate over the records in the CSVParser
        for (CSVRecord record : parser) {
            // add TemperatureF to sum
            sum += Double.parseDouble(record.get("TemperatureF"));
            // increment count
            count++;
        }
        return sum / count;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }

    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser, Integer value) {
        Double sum = 0.0;
        Integer count = 0;
        // iterate over the records in the CSVParser
        for (CSVRecord record : parser) {
            // check if Humidity is greater than or equal to value
            if (Integer.parseInt(record.get("Humidity")) >= value) {
                // if so, add TemperatureF to sum
                sum += Double.parseDouble(record.get("TemperatureF"));
                // increment count
                count++;
            }
        }
        if (count == 0) {
            return null;
        }
        return sum / count;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (average != null) {
            System.out.println("Average temperature when high Humidity is " + average);
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }
}
