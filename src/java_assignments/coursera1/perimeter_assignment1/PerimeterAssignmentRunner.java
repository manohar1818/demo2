package java_assignments.coursera1.perimeter_assignment1;

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
        int count =0;
        for(Point point :s.getPoints()){
            count = count +1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // this is used to get total perimeter
        double totalPerim = getPerimeter(s);
        // this is used to get total points
        double totalPoints = getNumPoints(s);
        double averageLength = totalPerim/totalPoints;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
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
        // Put code here
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null;
        return temp.getName();
    }

    public void testPerimeter () {
        // Used to select file
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
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
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
        pr.testPerimeter();
    }
}
