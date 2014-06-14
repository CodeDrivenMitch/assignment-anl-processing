package com.insidion.processing.assignment6;

import com.insidion.processing.framework.Assignment;
import processing.data.Table;
import processing.data.TableRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitchell on 6/13/2014.
 */
public class HeightMap extends Assignment {
    private static final int distance = 500;
    private static final int center_x = 92796;
    private static final int center_y = 436960;

    private float[][] values = new float[1000][1000];
    private int maxValue = 12;
    private int minValue = -5;

    private float currentWaterHeight = -10f;
    private float waterSpeed = 0.05f;
    private float waterTreshold = 20f;


    Table table;

    @Override
    public void setup() {

        size(1000, 1000);
        resize(1000, 1000);
        frame.setTitle(this.toString());

        table = loadTable("http://web.insidion.com/processing/o6/processed_500.csv");
        prepareData();
    }

    private void prepareData() {
        // Loop over the table and put the values in the Array, for better access later
        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow row = table.getRow(i);
            // Convert X,Y to screen pixel format
            int x = row.getInt(0) - (center_x - distance);
            int y = row.getInt(1) - (center_y - distance);
            values[x][y] = row.getFloat(2);
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                float value = values[i][999 - j];

                if(value < currentWaterHeight) {
                    setWater(i, j);
                } else {
                    set(i, j, getColorForValue(value));
                }
            }
        }
        drawUI();
        //checkWater(900, 900);
        currentWaterHeight += waterSpeed;

        if (currentWaterHeight > waterTreshold) {
            currentWaterHeight = -10f;
        }
    }

    private void checkWater(int x, int y) {
        List<WaterCoord> list = new ArrayList<WaterCoord>(100000);
        List<WaterCoord> checkedList = new ArrayList<WaterCoord>(100000);

        list.add(new WaterCoord(x, y));


        while (list.size() > 0) {
            WaterCoord current = list.get(0);
            if (values[current.getX()][999 - current.getY()] < currentWaterHeight) {
                setWater(current.getX(), current.getY());

                WaterCoord[] coords = {
                        new WaterCoord(current.getX() - 1, current.getY() - 1),
                        new WaterCoord(current.getX() + 1, current.getY() + 1),
                        new WaterCoord(current.getX() - 1, current.getY() + 1),
                        new WaterCoord(current.getX() + 1, current.getY() - 1)
                };

                for(WaterCoord coord : coords) {
                    if(coord.isValid()) {
                        if (!checkedList.contains(coord) && !list.contains(coord)) {
                            list.add(coord);
                        }
                    }
                }



            }
            checkedList.add(current);
            list.remove(current);
        }


    }

    private void setWater(int x, int y) {
        set(x, y, color(0, 0, 100 - (int) (Math.random() * 80)));
    }

    private void drawUI() {
        textAlign(CENTER);
        fill(255, 255, 255);
        text(Float.toString(currentWaterHeight), 500, 50);
    }

    private int getColorForValue(float value) {
        int range = maxValue + Math.abs(minValue);
        float steps = 255 / range;

        value = value + Math.abs(minValue);

        return color(value * steps, 140 - value * steps, 0);
    }

    @Override
    public String toString() {
        return "Assignment 6 - Height Map";
    }
}
