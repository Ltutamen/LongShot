package org.ua.axiom.controller;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Collections;
import java.util.List;

public class ConcatenateCapsController {
    private final List<BufferedImage> captions;
    private final int shift;

    public ConcatenateCapsController(List<BufferedImage> captions, int shift) {
        this.captions = captions;
        this.shift = shift;
    }

    //  todo what if resulting image is bigger that 2^16?
    public List<BufferedImage> run() {
        return Collections.singletonList(
                captions
                .stream()
                .reduce(null, (acc, i) -> {
                    if(acc == null) {
                        return i;
                    }
                    return concatImageTopOnDown(acc, i);
                }));
    }

    private BufferedImage concatImageTopOnDown(BufferedImage top, BufferedImage bottom) {
        BufferedImage result = new BufferedImage(top.getWidth(), top.getHeight() + bottom.getHeight() - shift, top.getType());
        WritableRaster raster = result.getRaster();

        raster.setPixels(0, top.getHeight() - shift, bottom.getWidth(), bottom.getHeight(),
                unpackPixelArray(bottom));
        raster.setPixels(0, 0, top.getWidth(), top.getHeight(),
                unpackPixelArray(top));

        return result;
    }

    private int[] unpackPixelArray(BufferedImage image) {
        int[] inArray = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        int[] result = new int[inArray.length * 3];

        ColorModel colorModel = image.getColorModel();

        for(int i = 0; i < result.length ; i += 3) {
            result[i] = colorModel.getRed(inArray[i/3]) ;
            result[i+1] = colorModel.getGreen(inArray[i/3]);
            result[i+2] = colorModel.getBlue(inArray[i/3]);
        }

        return result;
    }
}
