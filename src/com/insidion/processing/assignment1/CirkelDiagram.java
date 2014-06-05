package com.insidion.processing.assignment1;

import com.insidion.processing.assignment1.Diagram;
import processing.core.PApplet;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mitchell on 5/14/2014.
 */
public class CirkelDiagram extends Diagram {
    private static double diagram_width = 0.6;
    private static double legenda_width = 0.4;
    private static int margin = 10;

    private int[] colors;

    float start, end;

    public CirkelDiagram(PApplet applet, String file) throws IOException {
        super(applet, file);
        this.colors = getRandomColors();
    }



    @Override
    public void drawDiagram() {

        start = 0;
        end = 0;

        int i = 0;
        double percentage;

        int middle_x = (int) (this.xpos + (this.wdt * diagram_width ) / 2 );
        int middle_y = (int) (this.ypos + (this.hgt / 2));
        int radius_x = (int) (this.wdt * diagram_width - 2 * margin);
        int radius_y = (int) (this.hgt - 2 * margin);

        // Calculate radius en position of circle

        for (Map.Entry entrySet : dataToDraw.entrySet()) {
            // calculate needed data
            percentage = getPercentage((Integer) entrySet.getValue());
            end = (float) ((float) start + percentage * (2 * PI));

            applet.fill(this.colors[i], 255, 255);
            applet.arc(middle_x, middle_y, radius_x, radius_y, start, end);


            // Draw legenda entry

            applet.rect((int)(xpos + this.wdt - this.wdt * legenda_width + margin), ypos + margin + 30 * i, 20, 20);

            applet.fill(applet.color(0, 0, 255));

            applet.text((String) entrySet.getKey(), (int)(xpos + this.wdt - this.wdt * legenda_width + margin) + 30, 15 + ypos + margin + 30 * i);

            // Prepare variables for next round
            start = end;
            i += 1;
        }
    }

    private int getSumOfValues() {
        int total = 0;
        for (Map.Entry me : dataToDraw.entrySet()) {
            total += (Integer) me.getValue();
        }
        return total;
    }

    private float getPercentage(Integer value) {
        return (float) ((Integer) value) / getSumOfValues();
    }

    private int[] getRandomColors() {

        int[] colorsToBe = new int[this.dataToDraw.size()];
        int old_color = 0;

        for(int i = 0; i < this.dataToDraw.size(); i++ ) {
            colorsToBe[i] = (int) old_color +  (360 / this.dataToDraw.size());
            old_color = colorsToBe[i];
        }

        return colorsToBe;
    }
}
