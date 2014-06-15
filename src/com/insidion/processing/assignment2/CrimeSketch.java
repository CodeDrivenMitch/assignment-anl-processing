package com.insidion.processing.assignment2;

import com.insidion.processing.framework.Assignment;
import processing.core.PShape;
import processing.data.Table;
import processing.data.TableRow;

public class CrimeSketch extends Assignment {
    private PShape usa;
    private Table table;
    private float scale;

    /**
     * Set up the plot.
     */
    public void setup() {
        super.setup();

        // Load data
        usa = loadShape("http://upload.wikimedia.org/wikipedia/commons/3/32/Blank_US_Map.svg");
        table = loadTable("http://web.insidion.com/wapenbezit.tsv", "tsv");

        // Set some defaults
        this.scale = width / usa.getWidth();
        colorMode(HSB, 100);
        noLoop();
    }


    /**
     * Draws the SVG
     */
    public void draw() {
        // Set stroke to black for shape outlines
        stroke(0, 0, 0);
        for (TableRow row : table.rows()) {

            // Get the shape, disable the default styling and set the scale/position
            PShape state = usa.getChild(row.getString(0));
            state.disableStyle();
            state.scale(this.scale);

            // fill the shape and draw it on the screen
            fill(100, (float) 1.8 * row.getFloat(1), 100);
            shape(state, 0, 0);
        }

        // Draw legend and title
        drawLegend();
        drawTitle();
    }

    /**
     * Draws the Legend
     */
    private void drawLegend() {
        // Draw the legend text
        stroke(0, 0, 0);
        fill(0, 0, 0);
        textAlign(RIGHT);
        text("Less Crime", width - 35, height - 120);
        text("More Crime", width - 35, height - 10);

        // Draw the legend shape. Uses a self-made gradient
        rect(width - 30, height - 130, 20, 121);
        for (int i = 0; i < 120; i++) {
            stroke(100, (float) 1.8 * i / 2, 100);
            line(width - 29, height - 129 + i, width - 11, height - 129 + i);
        }

    }

    /**
     * Draws the title in the top middle of the screen
     */
    private void drawTitle() {
        stroke(0, 0, 0);
        fill(0, 0, 0);

        textAlign(CENTER);
        textSize(20);
        text("Crime in the U.S.A.", width / 2, 30);
    }


    /**
     * toString method for use in the title and in the launcher
     * @return title
     */
    @Override
    public String toString() {
        return "Assignment Two - Crime";
    }

}
