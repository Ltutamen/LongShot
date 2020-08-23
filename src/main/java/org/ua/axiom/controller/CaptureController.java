package org.ua.axiom.controller;

import org.ua.axiom.model.AppSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CaptureController {
    private final int wheelScrollsPerCapture;
    private final int delay;
    private final int captureDelay;
    private final int captureWight;
    private final int captureHigh;

    public CaptureController(AppSettings settings) {
        this.delay = settings.getDelayBetweenCapturesMsecs();
        this.captureDelay = settings.getDelayBeforeCaptureMsecs();
        this.captureWight = settings.getCaptureSizeX();
        this.captureHigh = settings.getCaptureSizeY();
        this.wheelScrollsPerCapture = settings.getWheelScrollsPerCapture();
    }

    public List<BufferedImage> run() {
        List<BufferedImage> screenCaps = new LinkedList<>();

        try {
            Robot robot = new Robot();
            Thread.sleep(delay);

            Point mousePos = MouseInfo.getPointerInfo().getLocation();
            Rectangle captureRect = new Rectangle(mousePos.x, mousePos.y, captureWight, captureHigh);

            for (
                    BufferedImage prevImage = null, currentImage = robot.createScreenCapture(captureRect);
                    !bufferedImagesEquals(currentImage, prevImage);
                    currentImage = robot.createScreenCapture(captureRect)) {
                prevImage = currentImage;
                screenCaps.add(prevImage);

                robot.mouseWheel(wheelScrollsPerCapture);
                Thread.sleep(captureDelay);

            }

        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }

        screenCaps.remove(screenCaps.get(screenCaps.size() - 1));

        return screenCaps;
    }

    private static boolean bufferedImagesEquals(BufferedImage imageA, BufferedImage imageB) {
        if(imageA == null || imageB == null) {
            return false;
        }

        DataBufferInt imageABuffer = (DataBufferInt)imageA.getRaster().getDataBuffer();
        DataBufferInt imageBBuffer = (DataBufferInt)imageB.getRaster().getDataBuffer();

        for (int bank = 0; bank < imageABuffer.getNumBanks(); bank++) {
            int[] actual = imageABuffer.getData(bank);
            int[] expected = imageBBuffer.getData(bank);

            if(!Arrays.equals(actual, expected)) {
                return false;
            }
        }

        return true;
    }
}
