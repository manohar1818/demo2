package java_assignments.coursera1.csv_assignment1;

import edu.duke.FileResource;
import edu.duke.StorageResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class WhichCountriesExport {

    // This function is used to test the below functions
    public void tester(){
        // Used to select file
        FileResource fr= new FileResource();
        // For every method call new parser
        CSVParser parser = fr.getCSVParser();
        String s=countryInfo(parser,"Germany");
        System.out.println(s);
        CSVParser parser1 = fr.getCSVParser();
        listExportersTwoProducts(parser1,"gold","diamonds");
        CSVParser parser2 = fr.getCSVParser();
        int count = numberOfExporters(parser2,"gold");
        System.out.println(count);
        CSVParser parser3 = fr.getCSVParser();
        bigExporters(parser3,"$400,000,000");

    }


    //This method returns a string of information about the country or returns “NOT FOUND” if there is no information about the country.
    //The format of the string returned is the country, followed by “: “, followed by a list of the countries’ exports, followed by “: “, followed by the countries export value.
    public String countryInfo(CSVParser parser,String country){
        // For each row in the csv file
        for(CSVRecord record : parser){
            String c = record.get("Country");
            if(c.equals(country)){
                String s = country+":"+record.get("Exports")+":"+record.get("Value (dollars)");
                return s;
            }
        }
        return "NOT FOUND";
    }



    //This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }


    //This method returns the number of countries that export exportItem.
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count=0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                count = count + 1;
            }
        }
        return count;
    }


    //This method prints the names of countries and their Value amount for all countries whose Value (dollars) string is longer than the amount string.
    public void bigExporters(CSVParser parser, String amount){
        // for every row
        for(CSVRecord record : parser){
            String currAmount = record.get("Value (dollars)");
            if(currAmount.length() > amount.length()){
                String country = record.get("Country");
                String money = record.get("Value (dollars)");
                System.out.println(country+" "+money);
            }
        }
    }



    public static void main(String[] args) {
        WhichCountriesExport wce = new WhichCountriesExport();
        wce.tester();
    }
}
