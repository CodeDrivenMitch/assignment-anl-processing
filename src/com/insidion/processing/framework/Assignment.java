package com.insidion.processing.framework;

import processing.core.PApplet;

/**
 * Created by mitchell on 6/5/2014.
 */
public abstract class Assignment extends PApplet {
    protected int[] colors;
    /**
     * Force assignments to implement a toString method for the list text in the launcher
     * @return Name of assignment
     */
    public abstract String toString();

    @Override
    public void setup() {
        size(800, 500);
        resize(800,500);
        frame.setTitle(this.toString());
        initColors();
    }
    /**
     * Make Assignment applets Launchable
     */
    public void launch() {
        PApplet.main(this.getClass().getName());

    }

    private void initColors() {
        this.colors = new int[10];

        this.colors[0] = color(255, 0, 0);
        this.colors[1] = color(0, 255, 0);
        this.colors[2] = color(0, 0, 255);
        this.colors[3] = color(255, 0, 255);
        this.colors[4] = color(255, 255, 0);
        this.colors[5] = color(0, 255, 255);

    }

}
