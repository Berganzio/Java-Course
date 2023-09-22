import edu.duke.*;
import java.io.*;

public class StringsSecondAssignments {
    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testFindStopCodon();
        part1.testFindGene();
        part1.printAllGenes("xxxATGxxxTAAxxxATGxxxTAGxxxATGxxTGAxxxATGxxxTAATAGxxxTGAxxx");

        Part2 part2 = new Part2();
        part2.testHowMany();

        Part3 part3 = new Part3();
        part3.testCountGenes();
    }
}

class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public void testFindStopCodon() {
        // create a real example of a dna
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna, 0, "TAG");
        if (dex != 26) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex)); // choose the smallest index
        if (minIndex == dna.length()) { // if no valid stop codon found
            return "";
        }
        return dna.substring(startIndex, minIndex + 3); // return the gene
    }

    public void testFindGene() {
        // create five dna strings
        String dna1 = "GTAxxxTAAxxx";
        String dna2 = "xxxATGxxxTAAxxx";
        String dna3 = "xxxATGxxxTAGxxx";
        String dna4 = "xxxATGxxTGAxxx";
        String dna5 = "xxxATGxxxTAAATGxxxTGAxxx";
        // print the five dna strings
        System.out.println("dna1: " + dna1);
        System.out.println("dna2: " + dna2);
        System.out.println("dna3: " + dna3);
        System.out.println("dna4: " + dna4);
        System.out.println("dna5: " + dna5);
        // print the genes found in the five dna strings
        System.out.println("gene in dna1: " + findGene(dna1));
        System.out.println("gene in dna2: " + findGene(dna2));
        System.out.println("gene in dna3: " + findGene(dna3));
        System.out.println("gene in dna4: " + findGene(dna4));
        System.out.println("gene in dna5: " + findGene(dna5));
    }

    public void printAllGenes(String dna) {
        while (true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
    }
}

class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            int currIndex = stringb.indexOf(stringa, startIndex);
            if (currIndex == -1) {
                break;
            }
            count++;
            startIndex = currIndex + stringa.length();
        }
        return count;
    }

    public void testHowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println("stringa: " + stringa);
        System.out.println("stringb: " + stringb);
        System.out.println("count: " + howMany(stringa, stringb));
        
        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println("stringa: " + stringa);
        System.out.println("stringb: " + stringb);
        System.out.println("count: " + howMany(stringa, stringb));
    }
}

class Part3 {
    public int countGenes(String dna) {
        int count = 0;
        Part1 part1 = new Part1();
        while (true) {
            String gene = part1.findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            count++;
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
        return count;
    }

    public void testCountGenes() {
        //             vvvvvv vvv   vvv
        String dna1 = "ATGTAAGATGCCCTAGT";
        System.out.println("dna: " + dna1);
        System.out.println("count: " + countGenes(dna1));

        //             vvvvvv vvv   vvv vvvvvv vvv   vvv
        String dna2 = "ATGTAAGATGCCCTAGTATGTAAGATGCCCTAGT";
        System.out.println("dna: " + dna2);
        System.out.println("count: " + countGenes(dna2));

        //             vvvvvv vvv   vvv vvvvvv vvv   vvv vvvvvv vvv   vvv
        String dna3 = "ATGTAAGATGCCCTAGTATGTAAGATGCCCTAGTATGTAAGATGCCCTAGT";
        System.out.println("dna: " + dna3);
        System.out.println("count: " + countGenes(dna3));

        //             vvvvvv vvv   vvv vvvvvv vvv   vvv vvvvvv vvv   vvv vvvvvv vvv   vvv
        String dna4 = "ATGTAAGATGCCCTAGTATGTAAGATGCCCTAGTATGTAAGATGCCCTAGTATGTAAGATGCCCTAGT";
        System.out.println("dna: " + dna4);
        System.out.println("count: " + countGenes(dna4));
    }
}