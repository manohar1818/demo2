package java_assignments.coursera1.perimeter_assignment2;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // this is used to get number of points in the shape
        int count =0;
        for(Point point :s.getPoints()){
            count = count +1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // this is used to get total perimeter
        double totalPerim = getPerimeter(s);
        // this is used ot get total number of points in the shape
        double totalPoints = getNumPoints(s);
        double averageLength = totalPerim/totalPoints;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update largestSide if currDist is greater than it
            if(currDist> largestSide){
                largestSide= currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // this is used to get x-coordinate of last point
        double largestX = s.getLastPoint().getX();
        // for each point in the Shape
        for(Point currPoint : s.getPoints()){
            // this is used to get X-coordinate of every point
            double currX = currPoint.getX();
            // if current point X-coordinate is greater than largest then update
            if(currX > largestX){
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // This function is used to get the largest perimeter of all the shapes present in different files
        double largestPerim = 0.0;
        // Used to select multiple files
        DirectoryResource dr = new DirectoryResource();
        // for each file in the selected file
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            // this is used to get the perimeter of the shape which is in the current file
            double currPerim = getPerimeter(s);
            // if the currrent perimeter is greater than largest than update largest perimeter
            if(currPerim > largestPerim){
                largestPerim = currPerim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // This is used to get the file name that has the largest perimeter
        // Initialize the temp file to null
        File temp = null;
        double largestPerim = 0.0;
        // used to select multiple files
        DirectoryResource dr = new DirectoryResource();
        // for each file in the selected file
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            // while updating largestPerimeter, also update the temp file to file which has largest perimeter
            if(currPerim > largestPerim){
                largestPerim = currPerim;
                temp = f;
            }
        }

        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int result = getNumPoints(s);
        System.out.println("Total Points "+ result);
        double averageLength = getAverageLength(s);
        System.out.println("AverageLength "+averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("LargestSide "+largestSide);
        double largestX = getLargestX(s);
        System.out.println("LargestX "+largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPeri = getLargestPerimeterMultipleFiles();
        System.out.println("LargestPerimeter " + largestPeri);
    }

    public void testFileWithLargestPerimeter() {
        String fileName = getFileWithLargestPerimeter();
        System.out.println("FileWithLargestPerimeter " + fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
