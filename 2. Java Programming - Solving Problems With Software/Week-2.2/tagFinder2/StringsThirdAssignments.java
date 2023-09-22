import edu.duke.*;
import java.io.*;

public class StringsThirdAssignments {
    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testProcessGenes();
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

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }

    public float cgRatio(String dna) {
        int count = 0;
        for (char c : dna.toCharArray()) {
            if (c == 'C' || c == 'G') {
                count++;
            }
        }
        return (float) count / dna.length();
    }

    public int countCTG(String dna) {
        int count = 0;
        int startIndex = dna.indexOf("CTG");
        while (startIndex != -1) {
            count++;
            startIndex = dna.indexOf("CTG", startIndex + 3);
        }
        return count;
    }

    public void processGenes(StorageResource sr) {
        System.out.println("number of genes: " + sr.size());
        int nineCount = 0;
        int cgRatioCount = 0;
        int geneLength = 0;
        String geneId = "";
        int sixtyCount = 0;
        for (String gene : sr.data()) {
            if (gene.length() > 9) {
                System.out.println(gene);
                nineCount++;
            }
            if (cgRatio(gene) > 0.35) {
                System.out.println(gene);
                cgRatioCount++;
            }
            if (gene.length() > geneLength) {
                geneLength = gene.length();
                geneId = gene;
            }
            if (gene.length() > 60) {
                System.out.println(gene);
                sixtyCount++;
            }
        }
        System.out.println("number of genes longer than 9 characters: " + nineCount);
        System.out.println("number of genes with cg ratio greater than 0.35: " + cgRatioCount);
        System.out.printf("length of the longest gene: %d, gene id: %s\n", geneLength, geneId);
        System.out.println("number of genes longer than 60 characters: " + sixtyCount);
    }

    public void testProcessGenes() {
        //FileResource fr = new FileResource("brca1line.fa");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource geneList = getAllGenes(dna);
        System.out.println("Genes in storage resource: " + geneList.size());
        processGenes(geneList);
        System.out.println("CTG count: " + countCTG(dna));
    }
}

