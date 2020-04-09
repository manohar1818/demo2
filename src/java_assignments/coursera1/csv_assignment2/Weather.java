package java_assignments.coursera1.csv_assignment2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;


public class Weather {

    //This method returns the CSVRecord with the coldest temperature in the file and thus all the information about the coldest temperature, such as the hour of the coldest temperature.
    public CSVRecord coldestHourInFile(CSVParser parser){
        // Initialise leastSoFar with null
        CSVRecord leastSoFar = null;
        // for every row in the CSV file
        for(CSVRecord currRow : parser){
            if(leastSoFar == null){
                leastSoFar = currRow;
            }
            else{
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double leastTemp = Double.parseDouble(leastSoFar.get("TemperatureF"));
                // Check if current row's temperature is less than leastSoFar temperature
                if(currTemp < leastTemp){
                    leastSoFar = currRow;
                }
            }
        }

        return leastSoFar;
    }



    // Used to test coldesthourInFile() method
    public void testColdesthourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println(record.get("TemperatureF")+ "   "+ record.get("TimeEST"));
    }


    //This method returns a string that is the name of the file from selected files that has the coldest temperature
    public String fileWithColdestTemperature(){
        String filename = "";
        File file = null;
        CSVRecord leastSoFar=null;
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRecord = coldestHourInFile(parser);
            if(leastSoFar == null){
                leastSoFar = currRecord;
            }
            else{
                double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));
                double leastTemp = Double.parseDouble(leastSoFar.get("TemperatureF"));
                // Check if current row's temperature is less than leastSoFar temperature
                if(currTemp < leastTemp){
                    leastSoFar = currRecord;
                    file =f;
                    filename = f.getName();
                }
            }
        }
        System.out.println("Coldest day was in file "+filename);
        System.out.println("Coldest temperature on that day was "+leastSoFar.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: ");
        FileResource fr1 = new FileResource(file);
        for(CSVRecord c:fr1.getCSVParser()){
            System.out.println(c.get("DateUTC")+" "+c.get("TemperatureF"));
        }

        return filename;
    }


    public void testFileWithColdestTemperature()
    {
        String filename = fileWithColdestTemperature();
    }


    //This method returns the CSVRecord that has the lowest humidity
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        // Initialise leastSoFar with null
        CSVRecord leastSoFar = null;
        // for every row in the CSV file
        for(CSVRecord currRow : parser){
            if(leastSoFar == null){
                leastSoFar = currRow;
            }
            else{
                if( currRow.get("Humidity")!="NA"  &&  leastSoFar.get("Humidity")!="NA" ) {
                    double currHum = Double.parseDouble(currRow.get("Humidity"));
                    double leastHum = Double.parseDouble(leastSoFar.get("Humidity"));
                    // Check if current row's temperature is less than leastSoFar temperature
                    if (currHum < leastHum) {
                        leastSoFar = currRow;
                    }
                }
            }
        }
        return leastSoFar;
    }


    // Used to test lowestHumidityInFile method
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }


    // This method returns a CSVRecord that has the lowest humidity over all the files
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord leastSoFar=null;
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = lowestHumidityInFile(parser);
            if(leastSoFar == null){
                leastSoFar = currRow;
            }
            else{
                if( currRow.get("Humidity")!="NA"  &&  leastSoFar.get("Humidity")!="NA" ) {
                    double currHum = Double.parseDouble(currRow.get("Humidity"));
                    double leastHum = Double.parseDouble(leastSoFar.get("Humidity"));
                    // Check if current row's temperature is less than leastSoFar temperature
                    if (currHum < leastHum) {
                        leastSoFar = currRow;
                    }
                }
            }
        }
        return leastSoFar;
    }


    // used to test lowestHumidityInManyFiles() method
    public void testLowestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+ record.get("Humidity")+" at "+ record.get("DateUTC"));
    }


    //This method returns a double that represents the average temperature in the file
    public double averageTemperatureInFile(CSVParser parser){
        double avgTemp = 0.0;
        int totalRows=0;
        for(CSVRecord currRow:parser){
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            avgTemp = currTemp + avgTemp;
            totalRows=totalRows+1;
        }

        return avgTemp/totalRows;
    }


    // used to test averageTemperatureInFile() method
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+avgTemp);
    }


    // This method returns a double that represents the average temperature of only those temperatures when the humidity was greater than or equal to value
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double avgTemp = 0.0;
        int totalRows=0;
        for(CSVRecord currRow:parser){
            if(  Double.parseDouble(currRow.get("Humidity")) >= value  ) {
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                avgTemp = currTemp + avgTemp;
                totalRows = totalRows + 1;
            }
        }
        if(avgTemp ==0.0 && totalRows==0){
            return Double.MAX_VALUE;
        }
        return avgTemp/totalRows;
    }


    // Used to test averageTemperatureWithHighHumidityInFile() method
    public void testaverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser,80);
        if(avgTemp == Double.MAX_VALUE){
            System.out.println("No temperature with that humidity");
        }
        else {
            System.out.println("Average temperature when high Humidity is "+avgTemp);
        }
    }


    public static void main(String[] args) {
        Weather weather = new Weather();
        //weather.testColdesthourInFile();
        //weather.testFileWithColdestTemperature();
        //weather.testLowestHumidityInFile();
        //weather.testLowestHumidityInManyFiles();
        //weather.testAverageTemperatureInFile();
        weather.testaverageTemperatureWithHighHumidityInFile();
    }
}
