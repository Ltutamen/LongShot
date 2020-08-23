package org.ua.axiom.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class AppSettings {
    private final Map<Character, Consumer<String>> ARGUMENT_TO_PARAM_MAPPING = new HashMap<>();

    private int delayBeforeCaptureMsecs = 4 * 1000;
    private int delayBetweenCapturesMsecs = 3 * 1000;

    private int captureSizeX = 1280;
    private int captureSizeY = 800;

    private int shiftSelectWindowSizeX = 1280;
    private int shiftSelectWindowSizeY = 1024;

    private Optional<Integer> pixelShift = Optional.empty();
    private int captionsLimit = Integer.MAX_VALUE;

    private int wheelScrollsPerShot = 1;

    private String pathToSave = "";

    {
        ARGUMENT_TO_PARAM_MAPPING.put('c', this::setDelayBeforeCaptureMsecs);
        ARGUMENT_TO_PARAM_MAPPING.put('d', this::setDelayBetweenCapturesMsecs);
        ARGUMENT_TO_PARAM_MAPPING.put('x', this::setCaptureSizeX);
        ARGUMENT_TO_PARAM_MAPPING.put('y', this::setCaptureSizeY);
        ARGUMENT_TO_PARAM_MAPPING.put('p', this::setPathToSave);
        ARGUMENT_TO_PARAM_MAPPING.put('w', this::setWheelScrollsPerCapture);
        ARGUMENT_TO_PARAM_MAPPING.put('f', this::setShiftSelectWindowSizeX);
        ARGUMENT_TO_PARAM_MAPPING.put('g', this::setShiftSelectWindowSizeY);
        ARGUMENT_TO_PARAM_MAPPING.put('s', this::setPixelShift);
        ARGUMENT_TO_PARAM_MAPPING.put('l', this::setCaptionsLimit);

    }

    public AppSettings(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            if(args[i].length() != 2) {
                throw new IllegalArgumentException("Program argument flags must have length of 2 characters, got <" + args[i] + "> instead.");
            }
            if(args[i].charAt(0) != '-') {
                throw new IllegalStateException("Argument flag must start from '-', got <" + args[i].charAt(0) + "> in string <" + args[i] + "> instead.");
            }
            ARGUMENT_TO_PARAM_MAPPING.get(args[i].charAt(1)).accept(args[i+1]);
        }
    }

    private void setDelayBeforeCaptureMsecs(String delayBeforeCaptureMsecs) {
        this.delayBeforeCaptureMsecs = Integer.parseInt(delayBeforeCaptureMsecs);
    }

    private void setDelayBetweenCapturesMsecs(String delayBetweenCapturesMsecs) {
        this.delayBetweenCapturesMsecs = Integer.parseInt(delayBetweenCapturesMsecs);
    }

    private void setCaptureSizeX(String captureSizeX) {
        this.captureSizeX = Integer.parseInt(captureSizeX);
    }

    private void setCaptureSizeY(String captureSizeY) {
        this.captureSizeY = Integer.parseInt(captureSizeY);
    }

    private void setPathToSave(String pathToSave) {
        this.pathToSave = pathToSave;
    }

    public int getDelayBeforeCaptureMsecs() {
        return delayBeforeCaptureMsecs;
    }

    public int getDelayBetweenCapturesMsecs() {
        return delayBetweenCapturesMsecs;
    }

    public int getCaptureSizeX() {
        return captureSizeX;
    }

    public int getCaptureSizeY() {
        return captureSizeY;
    }

    public String getPathToSave() {
        return pathToSave;
    }

    public int getWheelScrollsPerCapture() {
        return wheelScrollsPerShot;
    }

    public void setWheelScrollsPerCapture(String wheelScrollsPerShot) {
        this.wheelScrollsPerShot = Integer.parseInt(wheelScrollsPerShot);
    }

    public int getShiftSelectWindowSizeX() {
        return shiftSelectWindowSizeX;
    }

    public void setShiftSelectWindowSizeX(String shiftSelectWindowSizeX) {
        this.shiftSelectWindowSizeX = Integer.parseInt(shiftSelectWindowSizeX);
    }

    public int getShiftSelectWindowSizeY() {
        return shiftSelectWindowSizeY;
    }

    public void setShiftSelectWindowSizeY(String shiftSelectWindowSizeY) {
        this.shiftSelectWindowSizeY = Integer.parseInt(shiftSelectWindowSizeY);
    }

    public Optional<Integer> getPixelShift() {
        return pixelShift;
    }

    public void setPixelShift(String pixelShift) {
        this.pixelShift = Optional.of(Integer.parseInt(pixelShift));
    }

    public int getCaptionsLimit() {
        return captionsLimit;
    }

    public void setCaptionsLimit(String captionsLimit) {
        this.captionsLimit = Integer.parseInt(captionsLimit);
    }
}
