package java_assignments.coursera1.StringsFirstAssignments;

public class Part1 {
    public String findSimpleGene(String dna) {
        // index position of the start codon “ATG”
        int startCodonIndex = dna.indexOf("ATG");
        // if startCodenIndex == -1 , then it means there is no start codon "ATG"
        if(startCodonIndex == -1) {
            return "";
        }
        // index position of first stop codon
        int stopCodonIndex = dna.indexOf("TAA", startCodonIndex+3);
        // if stopCodonIndex == -1 , then it means there is no stop codon "TAA"
        if(stopCodonIndex == -1) {
            return "";
        }
        //If the length of the substring between the “ATG” and “TAA” is a multiple of 3,
        // then it returns the substring that starts with that “ATG” and ends with that “TAA”.
        if((stopCodonIndex-startCodonIndex) % 3 == 0){
            String gene = dna.substring(startCodonIndex, stopCodonIndex + 3);
            return gene;
        }
        else
            return "";
    }


    // This is used to test findSimpleGene() method
    public void testSimpleGene() {
        // Possible letters in Dna are :  A   C   G  T
        // DNA with no “ATG”
        // DNA with no “TAA”
        // DNA with no “ATG” or “TAA”
        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene),
        // DNA with ATG, TAA and the substring between them is not a multiple of 3
        String [] dna_s = {"AACCACGAGAT","ATGAGACTACAGT","AGCTTGACCTAG","AATGACGTTGCAGTAAG","ATGAGTCGATACTTAA"};
        // for each dna String
        for(String dna : dna_s) {
            System.out.println(dna + " : " + findSimpleGene(dna));
        }
    }

    public static void main(String[] args){
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }

}