package java_assignments.coursera1.Batch_Grayscale;


import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchGrayscale {
    // This method is used to convert the given input image to gray image and returns it.
    public ImageResource convertToGray(ImageResource inputImg) {
        // creates the outputImg with same dimensions
        ImageResource outputImg = new ImageResource(inputImg.getWidth(), inputImg.getHeight());
        //for each pixel in outputImg
        for (Pixel pixel : outputImg.pixels()) {
            Pixel inputImgPixel = inputImg.getPixel(pixel.getX(), pixel.getY());
            int avg = (inputImgPixel.getRed()+inputImgPixel.getBlue()+inputImgPixel.getGreen())/3;
            // This is used to set all pixels to average
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outputImg;
    }


    // This method is used to test the BatchGrayscale() method
    public void testBatchGrayscale() {
        // Used to select multiple files
        DirectoryResource dr = new DirectoryResource();
        // For each file in the selected files
        for (File f : dr.selectedFiles()) {
            ImageResource inputImg = new ImageResource(f);
            // Converting image in the input file to gray image
            ImageResource outputImg = convertToGray(inputImg);
            // Used to get the name of the file selected
            String inputImgFileName = inputImg.getFileName();
            String outputImgFileName = "gray-" + inputImgFileName;
            // Creating file name to the outputImg with the help of absolute path of the inputImgFileName
            outputImg.setFileName("src/java_assignments/coursera1/Batch_Grayscale/"+outputImgFileName);
            outputImg.draw();
            outputImg.save();
        }
    }


    public static void main(String[] args) {
        BatchGrayscale batchGrayscale = new BatchGrayscale();
        batchGrayscale.testBatchGrayscale();
    }

}
