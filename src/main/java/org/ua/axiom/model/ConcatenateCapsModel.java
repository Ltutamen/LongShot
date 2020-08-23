package org.ua.axiom.model;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class ConcatenateCapsModel {
    private int upCaptionNumber;
    private int shift;

    private final List<BufferedImage> captions;

    public ConcatenateCapsModel(List<BufferedImage> captions) {
        this.captions = captions;
        this.upCaptionNumber = 0;
        this.shift = 0;
    }

    public BufferedImage getTopImage() {
        return captions.get(upCaptionNumber);
    }

    public BufferedImage getBottomImage() {
        return captions.get(upCaptionNumber + 1);
    }

    public void nextCaptionPair() {
        if(upCaptionNumber + 2 == captions.size()) {
            return;
        }
        upCaptionNumber++;
    }

    public void prevCaptionPair() {
        if(upCaptionNumber == 0) {
            return;
        }
        upCaptionNumber--;
    }

    public void shiftUp(int shift) {
        this.shift += shift;
    }

    public void shiftDown(int shift) {
        this.shift -= shift;
    }

    public void emptyAction() { }

    public synchronized int getShift() {
        return shift;
    }

    public List<BufferedImage> getCurrentCaptionsPair() {
        return Arrays.asList(captions.get(upCaptionNumber), captions.get(upCaptionNumber + 1));
    }
}
