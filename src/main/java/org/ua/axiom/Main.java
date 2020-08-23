package org.ua.axiom;

//  -c delay between captures
//  -b delay before captures
//  -x x-size of caption, wight
//  -y y-size of caption, height
//  -p path to save result file(s)
//  -w amount of mouse wheel scrolls per shot
//  todo
//  -q x-size of shift selection window
//  -w y-size of shift selection window

//  todo image pair switch

public class Main {

    public static void main(String[] args) {
        new Controller(args).run();
    }
}
