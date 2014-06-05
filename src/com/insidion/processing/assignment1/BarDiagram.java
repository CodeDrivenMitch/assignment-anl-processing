package com.insidion.processing.assignment1;

import processing.core.PApplet;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mitchell on 5/15/2014.
 */
public class BarDiagram extends Diagram {
    private int verticalScale;
    private int highestValue;
    private int noVerticalLines = 10;

    public BarDiagram(PApplet applet, String file) throws IOException {
        super(applet, file);
    }

    @Override
    public void drawDiagram() {
        calculateVerticalScale();

        applet.fill(applet.color(0, 0, 255));
        applet.stroke(applet.color(0, 0, 255));


        int beginOfDraw = xpos + 50;
        int endOfDraw = xpos + wdt;
        int drawAreaPerBar = (endOfDraw - beginOfDraw) / dataToDraw.size();

        applet.line(xpos + 50, ypos, xpos + 50, ypos + hgt - 20);
        for (int i = 0; i <= noVerticalLines; i++) {
            // Scale
            applet.text(Integer.toString(verticalScale * noVerticalLines - verticalScale * i),
                    20,
                    ypos + 5 + ((hgt - 20) / noVerticalLines) * i);

            applet.line(45,
                    ypos + ((hgt - 20) / noVerticalLines) * i,
                    endOfDraw,
                    ypos + ((hgt - 20) / noVerticalLines) * i);

        }
        int i = 0;


        for (Map.Entry<String, Integer> entry : dataToDraw.entrySet()) {
            int begin = beginOfDraw + drawAreaPerBar * i;
            int end = begin + drawAreaPerBar;

            applet.fill(applet.color(0, 0, 255));
            applet.text(entry.getKey(), beginOfDraw + 5 + drawAreaPerBar * i, ypos + hgt);
            applet.fill(applet.color(100, 255, 255));
            applet.rect(begin + 5,
                    ypos + hgt - 20,
                    end - begin - 10,
                    -1 * (hgt - 20) * entry.getValue() / highestValue);
            i++;
        }

    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void calculateVerticalScale() {
        int scale = 1;

        for (Map.Entry<String, Integer> entry : dataToDraw.entrySet()) {
            if (entry.getValue() > highestValue) highestValue = entry.getValue();
        }

        while (true) {
            if (highestValue / noVerticalLines == scale) {
                this.verticalScale = scale;
                return;
            } else {
                scale += 1;
            }
        }
    }
}
