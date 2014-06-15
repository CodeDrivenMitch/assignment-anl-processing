package com.insidion.processing.framework;

import processing.core.PApplet;

/**
 * Base class for all assignments. Sets up some basic colors to be used, a launch method for use in the Launcher,
 * and defaults sizes and title.
 */
public abstract class Assignment extends PApplet {
    protected int[] colors = new int[]{
            color(255, 0, 0),
            color(0, 255, 0),
            color(0, 0, 255),
            color(255, 0, 255),
            color(255, 255, 0),
            color(0, 255, 255),
    };

    /**
     * Force assignments to implement a toString method for the list text in the launcher
     *
     * @return Name of assignment
     */
    public abstract String toString();

    /**
     * Setup functions. Implementing subclasses can call this using super.setup(), but they can also override
     * the method for more specialized use.
     */
    @Override
    public void setup() {
        // Set the default size. For some reason, it bugs without using the resize function also.
        size(800, 500);
        resize(800, 500);

        // Set the frame title
        frame.setTitle(this.toString());
    }

    /**
     * Make Assignment applets Launchable
     */
    public void launch() {
        PApplet.main(this.getClass().getName());

    }

}
