package com.insidion.processing.assignment3;

import com.insidion.processing.framework.Assignment;
import processing.core.PApplet;
import processing.data.Table;

/**
 * Created by mitchell on 6/5/2014.
 */
public class WinstcijfersSketch extends Assignment {

    Table table;

    public void setup() {
        size(800, 500);
        resize(800,500);
        table = loadTable("http://web.insidion.com/processing/o3/Winstcijfers.tsv", "tsv");
    }

    @Override
    public void draw() {
        clear();
        drawFrames();
        drawLabels();
        drawData();
    }

    private void drawFrames() {

    }

    private void drawLabels() {

    }

    private void drawData() {

    }

    @Override
    public String toString() {
        return "Assignment Three - Winstcijfers";
    }
}
