package java_assignments.coursera1.babynames;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;


// CSV data format , it has no header
// Name(0)   Gender(1)  totalno(2)

public class BabyBirths {

    public void totalBirths(){
        int girlnames=0;
        int boysnames=0;
        int totalnames=0;
        FileResource fr = new FileResource();
        // for every row in the selected file
        // here inside getCSVParser(false) there is false , which indicates no header
        for(CSVRecord currRow : fr.getCSVParser(false)){
            if(currRow.get(1).equals("F")){
                girlnames = girlnames +1;
            }
            else{
                boysnames = boysnames +1;
            }
        }
        totalnames = girlnames+boysnames;
        System.out.println("Total Girl names "+ girlnames);
        System.out.println("Total Boys names "+ boysnames);
        System.out.println("Total names "+totalnames);

    }

    public int getRank(int year,String name , String gender){
        // Getting complete file name by using the year parameter
        String s ="src/java_assignments/coursera1/babynames/us_babynames_small/testing/yob"+year+"short.csv";
        File f = new File(s);
        FileResource fr = new FileResource(f);
        // count is used to find out the rank
        int count=0;
        for(CSVRecord currRow : fr.getCSVParser(false)){
            // for both genders the rank will be different , so first they are  categorised based on gender
            if(currRow.get(1).equals(gender)) {
                // count is increased for a particular gender only , thus we can get the rank of that particular gender only
                count = count + 1;
                // if name is matched with row name then count will be rank for that name,so it is returned
                if (currRow.get(0).equals(name)) {
                    return count;
                }
            }
        }
        return -1;
    }

    public String getName(int year, int rank, String gender){
        String s ="src/java_assignments/coursera1/babynames/us_babynames_small/testing/yob"+year+"short.csv";
        File f = new File(s);
        FileResource fr = new FileResource(f);
        int count=0;
        // For every row
        for(CSVRecord currRow : fr.getCSVParser(false)){
            if(currRow.get(1).equals(gender)) {
                count = count + 1;
                if (count == rank) {
                    // return name when count equals rank
                    return currRow.get(0);
                }
            }
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        // this is used to get the rank for the given year,name and gender
        int rank = getRank(year,name , gender);
        // this is used to get the name based on rank(which is obtained from the above),newYear, gender
        String newname = getName(newYear, rank, gender);
        System.out.println(name +" born in "+year+" would be " +newname+" if she was born in "+newYear);
    }

    public int yearOfHighestRank(String name , String gender){
        // to select multiple files
        DirectoryResource dr = new DirectoryResource();
        int year=-1;
        // highest rank of all files
        int highestRank=Integer.MAX_VALUE;
        // initialization of file containing the answer i.e ansf
        File ansf = null;
        // for every file in selected files
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            int count = 0;
            int rank = 0;
            // for every row in the file
            for (CSVRecord currRow : fr.getCSVParser(false)) {
                if (currRow.get(1).equals(gender)) {
                    count = count + 1;
                    // when both gender and name are matched then count will be same as rank
                    if (currRow.get(0).equals(name)) {
                        rank = count;
                        // once we got rank of name in file no need to search for others rows
                        break;
                    }

                }
            }
            // update rank after every file is iterated
            // 2 ,3 , 4 , INT_MAXVALUE the answer will be 2
            if(rank < highestRank){
                highestRank = rank;
                // update ansf file with the current file that has the highest rank
                ansf = f;

            }
        }
        // Converting answerFile to String
        String nameOfFile = ansf.getName();
        // String slicing to get year from yob2012short.csv and converting it to string
        year = Integer.parseInt(nameOfFile.substring(3,7));
        return year;
    }


    public double getAverageRank(String name, String gender){
        // to select multiple files
        DirectoryResource dr = new DirectoryResource();
        int avgRank=0;
        int totalRank=0;
        int year=-1;
        int numFiles=0;
        // highest rank of all files
        int highestRank=Integer.MAX_VALUE;
        // initialization of file containing the answer i.e ansf
        File ansf = null;
        // for every file in selected files
        for(File f : dr.selectedFiles()) {
            numFiles = numFiles + 1;
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            int count = 0;
            int rank = 0;
            // for every row in the file
            for (CSVRecord currRow : fr.getCSVParser(false)) {
                if (currRow.get(1).equals(gender)) {
                    count = count + 1;
                    // when both gender and name are matched then count will be same as rank
                    if (currRow.get(0).equals(name)) {
                        rank = count;
                        // once we got rank of name in file no need to search for others rows
                        break;
                    }

                }
            }
            totalRank = rank + totalRank;
        }
        avgRank = totalRank/numFiles;
        if(avgRank!= 0)
            return avgRank;
        else
            return -1.0;
    }

    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        // Getting complete file name by using the year parameter
        String s ="src/java_assignments/coursera1/babynames/us_babynames_small/testing/yob"+year+"short.csv";
        File f = new File(s);
        FileResource fr = new FileResource(f);
        // count
        int count=0;
        int totalBirths = 0;
        for(CSVRecord currRow : fr.getCSVParser(false)){
            // for both genders the name may coincide so we are differentiating them on gender first
            if(currRow.get(1).equals(gender)) {
                // if name is matched with row name then no need to iterate further
                if (currRow.get(0).equals(name)) {
                    break;
                }
                else {
                    // this is used to add no of births whose rank is higher than the name given
                    totalBirths = Integer.parseInt(currRow.get(2))+totalBirths;
                }
            }
        }

        return totalBirths;
    }






    public static void main(String[] args) {
        BabyBirths bb = new BabyBirths();
        //bb.totalBirths();
        //int rank = bb.getRank(2012,"Mason","M");
        //System.out.println(rank);
        //String name = bb.getName(2012,2,"M");
        //System.out.println(name);
        //bb.whatIsNameInYear("Isabella",2012,2014,"F");
        //int year = bb.yearOfHighestRank("Mason","M");
        //System.out.println(year);
        //double avgRank = bb.getAverageRank("Mason","M");
        //System.out.println("The average rank is "+avgRank);
        int totalbirths = bb.getTotalBirthsRankedHigher(2012,"Ethan","M");
        System.out.println("The number of births based on given data are "+totalbirths);
    }
}
