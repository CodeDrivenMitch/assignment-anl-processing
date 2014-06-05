package com.insidion.processing.assignment2;

import com.insidion.processing.framework.Assignment;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.Table;
import processing.data.TableRow;

import java.awt.*;

/**
 * Created by mitchell on 5/16/2014.
 */
public class VolcanicSketch extends Assignment {

    private PImage img;
    Table table;
    Point upperleft = new Point(102, 151);
    Point downright = new Point(790, 370);

    public void setup() {
        colorMode(HSB, 360, 100, 100);
        size(900, 500);
        img = loadImage("http://i.imgur.com/4Hg4sa6.jpg", "jpg");
        loadData();
        colorMode(HSB, 100);

        size(900, 500);


    }

    public void draw() {
        img.resize(width, height);
        image(img, 0, 0);

        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow row = table.getRow(i);
            int y = (int) map((float) row.getDouble("lat"), 66, 64, upperleft.y, downright.y);
            int x = (int) map((float) row.getDouble("lng"), 24, 14, upperleft.x, downright.x);

            int schaal = (int) row.getDouble("Kracht");

            fill(98, (float) (10 + 0.60 * row.getDouble("Duur")), 90);
            ellipse(x, y, schaal * 5, schaal * 5);
        }
    }


    private void loadData() {

        table = loadTable("http://web.insidion.com/data.csv", "header");
        table.addColumn("lat");
        table.addColumn("lng");

        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow row = table.getRow(i);

            double lat = row.getDouble("graden") + (row.getDouble("minuten") / 60) + (row.getDouble("seconden") / 3600);
            row.setDouble("lat", lat);
            double lng = row.getDouble("b_graden") + (row.getDouble("b_minuten") / 60) + (row.getDouble("b_seconden") / 3600);
            row.setDouble("lng", lng);
        }


    }

    @Override
    public String toString() {
        return "Assignment Two - Volcanic Activity";
    }

}
