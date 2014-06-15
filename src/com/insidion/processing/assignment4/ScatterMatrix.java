package com.insidion.processing.assignment4;

import com.insidion.processing.framework.Assignment;
import processing.data.Table;
import processing.data.TableRow;

public class ScatterMatrix extends Assignment {

    // Margin constants to position and determine size of the scatter matrix
    private static final int MARGIN_LEFT = 5;
    private static final int MARGIN_RIGHT = 70;
    private static final int MARGIN_TOP = 50;
    private static final int MARGIN_BOTTOM = 50;

    // Table with data
    private Table table;


    /**
     * Draws a scatter plot, which is part of the total matrix.
     * @param x Begin position X of the plot
     * @param y Begin position Y of the plot
     * @param w Width of the plot
     * @param h Height of the plot
     * @param xCol Column number on X axis
     * @param yCol Column number on Y axis
     */
    private void drawScatter(int x, int y, int w, int h, int xCol, int yCol) {
        // Reset colors for this run
        stroke(255, 255, 255);
        fill(0, 0, 0);

        // Determine lowest and highest values of the data points
        double yMin = getMin(yCol);
        double yMax = getMax(yCol);
        double xMin = getMin(xCol);
        double xMax = getMax(xCol);

        // Determine scale (value/pixel) at which to draw the data points
        double xScale = w / (xMax - xMin);
        double yScale = h / (yMax - yMin);

        // Draw the rectangle which contains the area
        rect(x, y, w, h);

        // If the x column and y column are the same, draw a label there
        if (xCol == yCol) {

            //Draw the label
            fill(colors[xCol]);
            textAlign(CENTER);
            textSize(20);
            text(table.getColumnTitle(xCol), (float) (x + 0.5 * w), (float) (y + 0.5 * h));

            // Set font size for labels
            textSize(8);

            // Draw X label
            // First calculate the steps for drawing them.
            float xVarSteps = (float) ((xMax - xMin) / 4);
            float yVarSteps = (float) ((yMax - yMin) /4);
            float xWidthSteps = (float) w / 4;
            float yWidthSteps = (float) h / 4;

            // Now draw 3 labels per plot, on both x and y
            for(int i = 1; i < 4; i++) {
                // X Labels
                textAlign(CENTER);
                stroke(colors[xCol]);
                fill(colors[yCol]);
                // Draw line
                line(x + xWidthSteps * i, height - MARGIN_BOTTOM, x + xWidthSteps * i, height - MARGIN_BOTTOM + 4);
                // Set text
                text(Integer.toString(round((float) (xMin + xVarSteps * i))), x + xWidthSteps * i , height - MARGIN_BOTTOM + 15);

                // Y labels
                stroke(colors[yCol]);
                fill(colors[yCol]);
                textAlign(LEFT);
                // Draw line
                line(width - MARGIN_RIGHT, y + yWidthSteps * i, width- MARGIN_RIGHT + 5, y + yWidthSteps * i);
                // Set text
                text(Integer.toString(round((float)(yMin + yVarSteps * i))), width - MARGIN_RIGHT + 6, y + yWidthSteps * i + 2);
            }

            // Skip the rest of the method
            return;
        }



        // If this plot is not a label, just draw the data points.
        // the inner color of a data point belongs to the data on the X axis, and the outer color to the data
        // on the Y axis.
        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow r = table.getRow(i);

            // Calculate data point position
            double xD = x + xScale * (r.getDouble(xCol) - xMin);
            double yD = y + yScale * (r.getDouble(yCol) - yMin);

            // Set colors and draw the data point
            fill(colors[xCol]);
            stroke(colors[yCol]);
            ellipse((float) xD, (float) yD, 5, 5);
        }
    }

    /**
     * Gets the maximum value for a column number
     * @param column Number of the column
     * @return maximum value
     */
    private double getMax(int column) {
        double[] data = table.getDoubleColumn(column);
        double max = -1;

        for (double d : data) {
            if (d > max) max = d;
        }

        // Add a bit to the value for spacing
        return max * 1.05;
    }

    /**
     * Gets the minimum value for a column number
     * @param column Number of the column
     * @return minimum value
     */
    private double getMin(int column) {
        double[] data = table.getDoubleColumn(column);
        double min = 1000000000;

        for (double d : data) {
            if (d < min) min = d;
        }
        // remove a bit to the value for spacing
        return min * 0.95;
    }

    /**
     * Setup the window and load the table
     */
    @Override
    public void setup() {
        // Run setup of super.
        super.setup();
        this.table = loadTable("http://web.insidion.com/processing/o4/scattermatrix.tsv", "header, tsv");
    }

    /**
     * Draws the frames.
     */
    @Override
    public void draw() {
        // Clear the frame
        fill(0, 0, 0);
        clear();
        // Calulate the space available per plot.
        int xSpacePerPlot = (width - MARGIN_LEFT - MARGIN_RIGHT) / table.getColumnCount();
        int ySpacePerPlot = (height - MARGIN_TOP - MARGIN_BOTTOM) / table.getColumnCount();

        // Draw all the plots. As you can see, this becomes pretty easy
        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                drawScatter(MARGIN_LEFT + xSpacePerPlot * i, MARGIN_TOP + ySpacePerPlot * j, xSpacePerPlot, ySpacePerPlot, i, j);
            }
        }

        // Draw the title
        drawTitle();
    }

    /**
     * Draws the title
     */
    private void drawTitle() {
        // Create the title text
        StringBuilder builder = new StringBuilder("Matrix plot of ");
        for(int i = 0; i < table.getColumnCount(); i++) {
            builder.append(table.getColumnTitle(i));
            // Add a comma if it isn't the last one
            if(i != table.getColumnCount() -1) {
                builder.append(", ");
            }
        }

        // Set color/size etc
        fill(color(255, 255, 255));
        textAlign(CENTER);
        textSize(MARGIN_TOP / 2.5f);

        // Create the text
        text(builder.toString(), width/2, MARGIN_TOP /2);
    }

    /**
     * toString method for the name of the application in title and launcher
     * @return
     */
    @Override
    public String toString() {
        return "Assignment Four - Scattermatrix";
    }
}
