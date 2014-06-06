package com.insidion.processing.assignment3;

import com.insidion.processing.framework.Assignment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mitchell on 6/5/2014.
 */
public class LifeExpectancySketch extends Assignment {

    private static final int MARGIN = 50;
    private static final int VALUE_SCALE = 20;
    private static final int VALUE_SEP_WIDTH = 5;
    private static final int MAX_LIFE_EXPECTANCY = 100;
    private static final int YEAR_SEP_HEIGHT = 5;
    private static final int MAX_INCOME = 50000;
    private static final double YEAR_SPEED = 0.2f;

    private double currentYear = 1900f;


    @Override
    public void setup() {
        size(800, 500);
        resize(800, 500);
        frame.setTitle(this.toString());
    }

    @Override
    public void draw() {
        fill(0, 0, 0);
        clear();
        drawFrames();
        drawVertLabels();
        drawHorLabels();
        drawData();

        this.currentYear += YEAR_SPEED;

        if (this.currentYear > 2000) {
            this.currentYear = 1900;
        }
    }

    private void drawFrames() {
        stroke(255, 255, 255);
        fill(255, 255, 255);

        line(MARGIN, MARGIN, MARGIN, height - MARGIN);
        line(MARGIN,
                height - MARGIN,
                width - MARGIN,
                height - MARGIN);
    }

    private void drawVertLabels() {
        int length = MAX_LIFE_EXPECTANCY / VALUE_SCALE;
        int space = height - 2 * MARGIN;
        int spacePerThing = space / length;

        for (int i = 1; i <= length; i++) {
            int lineX = MARGIN;
            int lineY = height - MARGIN - i * spacePerThing;

            line(lineX - VALUE_SEP_WIDTH, lineY, lineX, lineY);
            textAlign(CENTER);
            text(Integer.toString(i * VALUE_SCALE), lineX - 20, lineY + 5);

        }

    }

    private void drawHorLabels() {
        int space = width - 2 * MARGIN;
        int w = space / 4;
        int valueAddition = MAX_INCOME / 4;

        for (int i = 0; i <= 4; i++) {
            float lineX = MARGIN + w * i;
            float lineY = height - MARGIN;
            line(lineX, lineY, lineX, lineY + YEAR_SEP_HEIGHT);

            float labelX = round(MARGIN + (w * i));
            float labelY = height - MARGIN + 10;
            textAlign(LEFT);
            pushMatrix();
            translate(labelX, labelY);
            rotate(radians(45));
            text(Integer.toString(i * valueAddition), 0, 0);
            popMatrix();


        }
    }

    private void drawData() {
        textAlign(CENTER);

        for (Country c : Country.values()) {
            Map<String, Integer> data = getData(c);

            if (data != null) {
                int x = round(map(data.get("INC"), 0, MAX_INCOME, MARGIN, width - MARGIN));
                int y = round(map(data.get("LE"), 0, MAX_LIFE_EXPECTANCY, height - MARGIN, MARGIN));
                int w = data.get("POP");
                ellipse(x,y, w , w);
                text(c.toString(), x, y - 10);
            }
        }

    }

    @Override
    public String toString() {
        return "Assignment Three - Life Expectancy";
    }

    private Map<String, Integer> getData(Country country) {
        Map<String, Integer> data = null;
        switch (country) {
            case Aboria:
                data = getDataArboria();
                break;
            case Bogetie:
                data = getDataBogetie();
                break;
            case Chani:
                break;
            case Daggerland:
                break;
            case Emiran:
                break;
        }

        return data;
    }

    private Map<String, Integer> getDataArboria() {
        Map<String, Integer> data = new HashMap<String, Integer>();

        data.put("LE", (int) (30 + 0.1 * (this.currentYear - 1900)));
        data.put("INC", (int) (5000 + 10f * (this.currentYear - 1900)));
        data.put("POP", (int) (1 * Math.pow(1.01, (this.currentYear - 1900))));

        return data;
    }

    private Map<String, Integer> getDataBogetie() {
        Map<String, Integer> data = new HashMap<String, Integer>();

        data.put("LE", (int) (30 + 0.3 * (this.currentYear - 1900)));
        data.put("INC", (int) (1000 + (9000 / 100) * (this.currentYear - 1900)));
        data.put("POP", (int) (4 * Math.pow(1.02, (this.currentYear - 1900))));

        return data;
    }


}
