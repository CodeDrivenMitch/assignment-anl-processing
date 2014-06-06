package com.insidion.processing.assignment4;

import com.insidion.processing.framework.Assignment;
import processing.data.Table;
import processing.data.TableRow;

/**
 * Created by mitchell on 6/6/2014.
 */
public class ScatterMatrix extends Assignment {

    private Table table;


    private void drawScatter(int x, int y, int w, int h, int xCol, int yCol) {
        stroke(255, 255, 255);
        fill(0, 0,0);


        rect(x, y, w, h);
        if(xCol == yCol) {
            fill(255, 255, 255);
            textAlign(CENTER);

            text(table.getColumnTitle(xCol), (float) (x + 0.5*w), (float) (y + 0.5*h));
            return;
        }

        double yMin = getMin(yCol);
        double yMax = getMax(yCol);
        double xMin = getMin(xCol);
        double xMax = getMax(xCol);
        double xScale = w / (xMax - xMin);
        double yScale = h / (yMax - yMin);

        for(int i = 0; i < table.getRowCount(); i++) {
            TableRow r = table.getRow(i);

            double xD = x + xScale * (r.getDouble(xCol) - xMin);
            double yD = y + yScale * (r.getDouble(yCol) - yMin);
            ellipse((float) xD, (float) yD, 2, 2);
        }
    }

    private double getMax(int column) {
        double[] data = table.getDoubleColumn(column);
        double max = -1;

        for(double d : data) {
            if( d > max) max = d;
        }

        return max;
    }

    private double getMin(int column) {
        double[] data = table.getDoubleColumn(column);
        double min = 1000000000;

        for(double d: data) {
            if ( d < min) min = d;
        }
        return min;
    }

    @Override
    public void setup() {
        // Run setup of super.
        super.setup();


        this.table = loadTable("http://web.insidion.com/processing/o4/scattermatrix.tsv", "header, tsv");

    }

    @Override
    public void draw() {
        fill(0, 0, 0);
        clear();
        for(int i = 0; i < table.getColumnCount(); i++) {
            for(int j = 0; j < table.getColumnCount(); j++) {
                drawScatter(100 * i, 100 * j, 90, 90, i, j);
            }
        }
    }

    @Override
    public String toString() {
        return "Assignment 4 - Scattermatrix";
    }
}
