package com.insidion.processing.assignment3;

/**
 * Created by mitchell on 6/5/2014.
 */
public enum Country {
    Aboria(1),
    Bogetie(2),
    Chani(3),
    Daggerland(4),
    Emiran(0);

    private final int index;

    Country(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }
}
