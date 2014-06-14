package com.insidion.processing.assignment6;

/**
 * Created by mitchell on 6/14/2014.
 */
public class WaterCoord {
    private int x;
    private int y;

    public WaterCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) {
            return false;
        }

        boolean equal = true;

        if(this.getX() != ((WaterCoord) obj).getX()) {
            equal = false;
        }

        if(this.getY() != ((WaterCoord) obj).getY()) {
            equal = false;
        }

        return equal;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isValid() {
        if(x > -1 && x < 1000 && y > -1 && y < 1000) {
            return true;
        }
        return false;
    }
}
