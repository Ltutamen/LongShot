package org.ua.axiom.controller;

import org.ua.axiom.model.AppSettings;

public class AdviceProviderController {
    public static void createAdvice(AppSettings settings, int actualShift) {
        int wheelScrolls = settings.getWheelScrollsPerCapture();

        System.out.format("Looks like in your system %d scrolls equal to %d screen pixels\n", wheelScrolls, settings.getCaptureSizeY() - actualShift);

        int recommendedScrolls = settings.getCaptureSizeY() / ((settings.getCaptureSizeY() - actualShift) / wheelScrolls);

        if(recommendedScrolls != settings.getWheelScrollsPerCapture()) {
            System.out.format("Use -w %d as an command line argument to minimise captures quantity", recommendedScrolls);
        }
    }
}
