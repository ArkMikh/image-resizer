package com.arkmikh.reziser.imageresizer.resizer;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ImageResizer {

    private Map<String, String> extensions; //= {"png", "jpg", "bmp", "wbmp", "gif"};


    /**
     * Resizes an image to a absolute width and height
     *
     * @param inputFolderPath  Path of the original images
     * @param outputImagePath Path to save the resized images
     * @param scaledWidth     absolute width in pixels
     * @param scaledHeight    absolute height in pixels
     * @throws IOException
     */
    public void resize(String inputFolderPath,
                       String outputImagePath, int scaledWidth, int scaledHeight, String extension)
            throws IOException {
        // reads input image
        File inputFolder = new File(inputFolderPath);
        File[] listOfFiles = inputFolder.listFiles();

        for (File file : listOfFiles) {
            System.out.println("file.getName() "+file.getName());
            BufferedImage inputImage = ImageIO.read(file);
            // creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                    scaledHeight, inputImage.getType());
            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();
            // get file name without extension
            String fileNameWithoutExtension = FilenameUtils.getBaseName(file.getName());
            // save file to outputPath with specific extension
            ImageIO.write(outputImage, extension,
                    new File(outputImagePath+"\\"+fileNameWithoutExtension+"." + extension));

        }

    }

}