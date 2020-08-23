package org.ua.axiom;

//  'c', delay before first capture, so you can place cursor on place
//  'd', delay between captures
//  'x', wight of single capture
//  'y', height of single capture
//  'p', path to save the result
//  'w', amount of wheel scroll per capture
//  'f', wight of shift-selection window
//  'g', height of shift-selection window
//  's', sets default pixel shift, if you know amount of pixels oer set amount of wheel scrolls
//  'l', use this to limit quantity of captions made

    /*  Controls    */
//  UP-DOWN arrow keys -> to shift one pixel respectively
//  SHIFT+up-down arrow keys -> to  shift 5 pixels respectively
//  SHIFT+z-x   ->  switch between pairs of captions
//  SPACE -> stop adjustment and continue

public class Main {
    public static void main(String[] args) {
        new Controller(args).run();
    }
}
