package com.insidion.processing.framework;

/**
 * Created by mitchell on 6/5/2014.
 */
public interface Assignment {
    /**
     * Force assignments to implement a toString method for the list text in the launcher
     * @return Name of assignment
     */
    public String toString();

    /**
     * Make Assignment applets Launchable
     */
    public void launch();

}
