package java_assignments.coursera1.image_inversion;


import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;


public class BatchInversions {
    // This method takes input as image and returns a new image that is the inverse of the original image
    public ImageResource makeInversion(ImageResource inputImg) {
        // creates the outputImg with same dimensions
        ImageResource outputImg = new ImageResource(inputImg.getWidth(), inputImg.getHeight());
        //for each pixel in outputImg
        for (Pixel pixel : outputImg.pixels()) {
            Pixel inputImgPixel = inputImg.getPixel(pixel.getX(), pixel.getY());
            // r= red     g= green   b= blue
            int r,g,b;
            r= inputImgPixel.getRed();
            g=inputImgPixel.getGreen();
            b=inputImgPixel.getBlue();
            pixel.setRed(255-r);
            pixel.setGreen(255-g);
            pixel.setBlue(255-b);
        }
        return outputImg;
    }


    // This method is used to test the BatchInverions() method
    public void selectAndConvert() {
        // Used to select multiple files
        DirectoryResource dr = new DirectoryResource();
        // For each file in the selected files
        for (File f : dr.selectedFiles()) {
            ImageResource inputImg = new ImageResource(f);
            // Converting image in the input file to gray image
            ImageResource outputImg = makeInversion(inputImg);
            // Used to get the name of the file selected
            String inputImgFileName = inputImg.getFileName();
            String outputImgFileName = "inverted-" + inputImgFileName;
            // Creating file name to the outputImg with the help of absolute path of the inputImgFileName
            outputImg.setFileName("src/java_assignments/coursera1/image_inversion/"+outputImgFileName);
            outputImg.draw();
            outputImg.save();
        }
    }


    public static void main(String[] args) {
        BatchInversions batchInversions = new BatchInversions();
        batchInversions.selectAndConvert();
    }

}

