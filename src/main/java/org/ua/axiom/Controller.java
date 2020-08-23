package org.ua.axiom;

import org.ua.axiom.controller.*;
import org.ua.axiom.model.AppSettings;

import java.awt.image.BufferedImage;
import java.util.List;

public class Controller {
    private final AppSettings settings;

    public Controller(String[] args) {
        settings = new AppSettings(args);
    }

    public void run() {
        List<BufferedImage> screenCaps = new CaptureController(settings).run();
        int shift = settings
                .getPixelShift()
                .orElseGet(
                        () -> new CaptionsShiftViewController(screenCaps, settings)
                                .run());
        List<BufferedImage> concatenatedCaptions = new ConcatenateCapsController(screenCaps, shift).run();
        new CaptionsSaveController(concatenatedCaptions, settings.getPathToSave()).run();

        AdviceProviderController.createAdvice(settings, shift);
    }
}
