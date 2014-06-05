package com.insidion.processing.framework;

import processing.core.PApplet;

/**
 * Created by mitchell on 6/5/2014.
 */
public abstract class Assignment extends PApplet {
    /**
     * Force assignments to implement a toString method for the list text in the launcher
     * @return Name of assignment
     */
    public abstract String toString();

    /**
     * Make Assignment applets Launchable
     */
    public void launch() {
        PApplet.main(this.getClass().getName());

    }

}
