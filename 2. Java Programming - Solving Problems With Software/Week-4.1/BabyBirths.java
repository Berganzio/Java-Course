/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int boysNames = 0;
		int girlsNames = 0;
		int totalNames = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) { // false means no header
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				boysNames++;
				totalNames++;
			}
			else {
				totalGirls += numBorn;
				girlsNames++;
				totalNames++;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("Boys' names: " + boysNames);
		System.out.println("Girls' names: " + girlsNames);
		System.out.println("male boys = " + totalBoys);
		System.out.println("total names = " + totalNames);
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("data/yob1905.csv");
		totalBirths(fr);
	}

	public Integer getRank(int year, String name, String gender) {
		FileResource fr = new FileResource("data/yob" + year + ".csv");
		int rank = 0;
		for (CSVRecord record : fr.getCSVParser(false)) {
			if (record.get(1).equals(gender)) {
				rank++;
				if (record.get(0).equals(name)) {
					return rank;
				}
			}
		}
		return -1;
	}

	public void testRank() {
		System.out.println(getRank(1971, "Frank", "M"));
	}

	public String getName(int year, int rank, String gender) {
		FileResource fr = new FileResource("data/yob" + year + ".csv");
		int currentRank = 0;
		for (CSVRecord record : fr.getCSVParser(false)) {
			if (record.get(1).equals(gender)) {
				currentRank++;
				if (currentRank == rank) {
					return record.get(0);
				}
			}
		}
		return "NO NAME";
	}

	public void testName() {
		System.out.println(getName(1982, 450, "M"));
	}

	public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		FileResource fr = new FileResource("data/yob" + year + ".csv");
		FileResource fr2 = new FileResource("data/yob" + newYear + ".csv");
		int rank = 0;
		int rank2 = 0;
		int newRank = 0;
		for (CSVRecord record : fr.getCSVParser(false)) {
			if (record.get(1).equals(gender)) {
				rank++;
				if (record.get(0).equals(name)) {
					for (CSVRecord record2 : fr2.getCSVParser(false)) {
						if (record2.get(1).equals(gender)) {
							rank2++;
							if (rank2 == rank) {
								System.out.println(name + " born in " + year + " would be " + record2.get(0) + " if she was born in " + newYear);
							}
						}
					}
				}
			}
		}
	}

	public void testnameInYear() {
		whatIsNameInYear("Owen", 1974, 2014, "M");
	}

	public Integer yearOfHighestRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int rank = 0;
		int highestRank = Integer.MAX_VALUE;
		int year = -1;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			int currentYear = Integer.parseInt(f.getName().substring(3, 7));
			for (CSVRecord record : fr.getCSVParser(false)) {
				if (record.get(1).equals(gender)) {
					rank++;
					if (record.get(0).equals(name) && rank < highestRank) {
						highestRank = rank;
						year = currentYear;
					}
				}
			}
			rank = 0;
		}
		if (year == -1) {
			return -1;
		}
		return year;
	}

	public void testyearOfHighestRank() {
		System.out.println(yearOfHighestRank("Mich", "M"));
	}

	public Double getAverageRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int rank = 0;
		int totalRank = 0;
		int year = 0;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			boolean foundName = false;
			for (CSVRecord record : fr.getCSVParser(false)) {
				if (record.get(1).equals(gender)) {
					rank++;
					if (record.get(0).equals(name)) {
						totalRank += rank;
						foundName = true;
						break;
					}
				}
			}
			if (foundName) {
				year++;
			}
			rank = 0;
		}
		if (year == 0) {
			return -1.0;
		}
		return (double) totalRank / year;
	}

	public void testAverageRank() {
		System.out.println(getAverageRank("Robert", "M"));
	}

	public Integer getTotalBirthsRankedHigher(String name, int year, String gender) {
		int totalBirths = 0;
		FileResource fr = new FileResource("data/yob" + year + ".csv");
		for (CSVRecord record : fr.getCSVParser(false)) {
			if (record.get(0).equals(name) && record.get(1).equals(gender)) {
				return totalBirths;
			}
			if (record.get(1).equals(gender)) {
				totalBirths += Integer.parseInt(record.get(2));
			}
		}
		return -1;
	}

	public void testGetTotalBirthsRankedHigher() {
		System.out.println(getTotalBirthsRankedHigher("Drew", 1990, "M"));
	}
}

