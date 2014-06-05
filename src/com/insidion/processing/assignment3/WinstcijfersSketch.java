package com.insidion.processing.assignment3;

import com.insidion.processing.framework.Assignment;
import processing.core.PApplet;
import processing.data.Table;

/**
 * Created by mitchell on 6/5/2014.
 */
public class WinstcijfersSketch extends PApplet implements Assignment {

    Table table;

    public void setup() {

        table = loadTable("http://web.insidion.com/processing/o3/Winstcijfers.tsv", "tsv");
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public String toString() {
        return "Assignment Three - Winstcijfers";
    }

    @Override
    public void launch() {
        PApplet.main(this.getClass().getName());

    }
}
