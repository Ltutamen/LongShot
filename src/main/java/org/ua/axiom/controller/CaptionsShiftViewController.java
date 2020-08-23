package org.ua.axiom.controller;

import org.ua.axiom.model.ConcatenateCapsModel;
import org.ua.axiom.view.ConcatenateCapsView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaptionsShiftViewController {
    private ConcatenateCapsModel model;
    private ConcatenateCapsView view;

    public CaptionsShiftViewController(List<BufferedImage> screenCaps) {
        model = new ConcatenateCapsModel(screenCaps);

        KeyAdapter keyAdapter = new KeyAdapter() {
            private final Map<Integer, Runnable> keyCodeToActionMap = new HashMap<>();
            private final Map<Integer, Runnable> shiftAndKeyCodeToActionMap = new HashMap<>();
            {
                keyCodeToActionMap.put(38, () -> shiftUpAndUpdate(1));
                keyCodeToActionMap.put(40, () -> shiftDownAndUpdate(1));
                keyCodeToActionMap.put(32, () -> view.dispose());

                shiftAndKeyCodeToActionMap.put(38, () -> shiftUpAndUpdate(5));
                shiftAndKeyCodeToActionMap.put(40, () -> shiftDownAndUpdate(5));
                shiftAndKeyCodeToActionMap.put(88, () -> nextImgPairAndUpdate());
                shiftAndKeyCodeToActionMap.put(90, () -> prevImgPairAndUpdate());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.isShiftDown()) {
                    shiftAndKeyCodeToActionMap.getOrDefault(e.getKeyCode(), model::emptyAction).run();
                } else {
                    keyCodeToActionMap.getOrDefault(e.getKeyCode(), model::emptyAction).run();
                }

            }
        };

        //  todo read size from appSettings
        view = new ConcatenateCapsView(keyAdapter, 1280, 800);
        render();
    }

    public int run() {
        while (view.isVisible()) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return model.getShift();
    }

    private void render() {
        view.render(model.getTopImage(), model.getBottomImage());
    }

    private void shiftUpAndUpdate(int shift) {
        model.shiftUp(shift);
        view.update(model.getShift());
    }

    private void shiftDownAndUpdate(int shift) {
        model.shiftDown(shift);
        view.update(model.getShift());
    }

    private void nextImgPairAndUpdate() {
        model.nextCaptionPair();
        view.update(model.getCurrentCaptionsPair());
    }

    private void prevImgPairAndUpdate() {
        model.prevCaptionPair();
        view.update(model.getCurrentCaptionsPair());
    }

}
