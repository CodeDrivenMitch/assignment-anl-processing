package com.insidion.processing.assignment2;

import com.insidion.processing.framework.Assignment;
import processing.core.PShape;
import processing.data.Table;
import processing.data.TableRow;

/**
 * Created by mitchell on 5/21/2014.
 */
public class CrimeSketch extends Assignment {
    private float scale;

    PShape usa;
    Table table;

    public void setup() {
        size(800, 500);
        frame.setTitle(this.toString());
        usa = loadShape("http://upload.wikimedia.org/wikipedia/commons/3/32/Blank_US_Map.svg");

        table = loadTable("http://web.insidion.com/wapenbezit.tsv", "tsv");
        resize(800, 500);
        this.scale = width / usa.getWidth();
        colorMode(HSB, 100);
        noLoop();

    }


    public void draw() {
        for (TableRow row : table.rows()) {

            float intensity = row.getFloat(1);

            PShape state = usa.getChild(row.getString(0));
            state.disableStyle();
            state.scale(this.scale);

            fill(100, (float) 1.8 * intensity, 100);
            shape(state, 0, 0);
            stroke(0, 0, 0);

            legend();

        }

    }

    public void legend() {


        fill(0, 0, 100);
        stroke(0,0,0);
        rect(50, 40, 75, 75);



    }


    @Override
    public String toString() {
        return "Assignment Two - Crime";
    }

}
