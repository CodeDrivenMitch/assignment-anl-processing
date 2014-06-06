package com.insidion.processing.assignment4;

import com.insidion.processing.framework.Assignment;
import processing.data.Table;
import processing.data.TableRow;

/**
 * Created by mitchell on 6/6/2014.
 */
public class ScatterMatrix extends Assignment {

    private static final int MARGIN_LEFT = 5;
    private static final int MARGIN_RIGHT = 70;
    private static final int MARGIN_TOP = 5;
    private static final int MARGIN_BOTTOM = 50;

    private Table table;


    private void drawScatter(int x, int y, int w, int h, int xCol, int yCol) {
        stroke(255, 255, 255);
        fill(0, 0, 0);


        double yMin = getMin(yCol);
        double yMax = getMax(yCol);
        double xMin = getMin(xCol);
        double xMax = getMax(xCol);
        double xScale = w / (xMax - xMin);
        double yScale = h / (yMax - yMin);

        rect(x, y, w, h);
        if (xCol == yCol) {
            fill(255, 255, 255);
            textAlign(CENTER);
            textSize(20);
            text(table.getColumnTitle(xCol), (float) (x + 0.5 * w), (float) (y + 0.5 * h));

            textSize(8);
            // Draw X label
            float steps = (float) ((xMax - xMin) / 4);
            float ySteps = (float) ((yMax - yMin) /4);
            float disStep = (float) w / 4;
            float yDisStep = (float) h / 4;
            for(int i = 1; i < 4; i++) {
                textAlign(CENTER);
                stroke(colors[xCol]);
                fill(colors[yCol]);
                line(x + disStep * i, height - MARGIN_BOTTOM, x + disStep * i, height - MARGIN_BOTTOM + 4);
                text(Integer.toString(round((float) (xMin + steps * i))), x + disStep * i , height - MARGIN_BOTTOM + 15);
                stroke(colors[yCol]);
                fill(colors[yCol]);
                textAlign(LEFT);
                line(width - MARGIN_RIGHT, y + yDisStep * i, width- MARGIN_RIGHT + 5, y + yDisStep * i);
                text(Integer.toString(round((float)(yMin + ySteps * i))), width - MARGIN_RIGHT + 6, y + yDisStep * i + 2);
            }
            return;
        }



        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow r = table.getRow(i);

            double xD = x + xScale * (r.getDouble(xCol) - xMin);
            double yD = y + yScale * (r.getDouble(yCol) - yMin);
            fill(colors[xCol]);
            stroke(colors[yCol]);
            ellipse((float) xD, (float) yD, 5, 5);
        }
    }

    private double getMax(int column) {
        double[] data = table.getDoubleColumn(column);
        double max = -1;

        for (double d : data) {
            if (d > max) max = d;
        }

        return max * 1.05;
    }

    private double getMin(int column) {
        double[] data = table.getDoubleColumn(column);
        double min = 1000000000;

        for (double d : data) {
            if (d < min) min = d;
        }
        return min * 0.95;
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
        int xSpacePerPlot = (width - MARGIN_LEFT - MARGIN_RIGHT) / table.getColumnCount();
        int ySpacePerPlot = (height - MARGIN_TOP - MARGIN_BOTTOM) / table.getColumnCount();

        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                drawScatter(MARGIN_LEFT + xSpacePerPlot * i, MARGIN_TOP + ySpacePerPlot * j, xSpacePerPlot, ySpacePerPlot, i, j);
            }
        }
    }

    @Override
    public String toString() {
        return "Assignment Four - Scattermatrix";
    }
}
