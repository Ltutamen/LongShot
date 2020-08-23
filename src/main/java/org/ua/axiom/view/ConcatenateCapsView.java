package org.ua.axiom.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class ConcatenateCapsView extends JFrame {
    private JLabel topImage;
    private JLabel bottomImage;

    private int windowWight;
    private int windowHeight;

    public ConcatenateCapsView(KeyListener keyListener, int windowWight, int windowHeight) throws HeadlessException {
        super("LongShot");
        this.setSize(windowWight, windowHeight);
        this.setResizable(false);
        this.setVisible(true);

        this.windowHeight = windowHeight;
        this.windowWight = windowWight;

        this.addKeyListener(keyListener);

    }

    public void render(BufferedImage topImageB, BufferedImage bottomImageB) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        {
            topImage = new JLabel(new ImageIcon(topImageB));
            topImage.setBounds(0, 0, Integer.min(windowWight, topImageB.getWidth()), Integer.min(windowHeight, topImageB.getHeight()));
            mainPanel.add(topImage);


            bottomImage = new JLabel(new ImageIcon(bottomImageB));  //  plus 2 pixel to make visible gap
            bottomImage.setBounds(0, topImage.getHeight() + 2, bottomImageB.getWidth(), bottomImageB.getHeight());
            mainPanel.add(bottomImage);
        }

        this.add(mainPanel);
    }

    public void update(int shift) {
        bottomImage.setBounds(0, topImage.getHeight() - shift, bottomImage.getWidth(), bottomImage.getHeight());
    }

    public void update(java.util.List<BufferedImage> currImagePair) {
        topImage.setIcon(new ImageIcon(currImagePair.get(0)));
        bottomImage.setIcon(new ImageIcon(currImagePair.get(1)));
    }

}
