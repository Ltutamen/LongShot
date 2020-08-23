package org.ua.axiom.controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CaptionsSaveController {
    private final String filePath;
    private final List<BufferedImage> images;

    public CaptionsSaveController(List<BufferedImage> captions, String path) {
        images = captions;
        filePath = path;
    }

    public void run() {
        File fileToWrite = new File(filePath + "caption.png");
        try {
            fileToWrite.createNewFile();
            ImageIO.write(images.get(0), "PNG", fileToWrite);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
