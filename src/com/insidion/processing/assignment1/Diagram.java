package com.insidion.processing.assignment1;

import processing.core.PApplet;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by mitchell on 5/14/2014.
 */
public abstract class Diagram {
    public static final double PI = 3.14159265359;

    protected int wdt, hgt;
    protected HashMap<String, Integer> dataToDraw;
    protected int xpos, ypos;
    protected String file;
    PApplet applet;

    public Diagram(PApplet applet, String file) throws IOException {
        this.applet = applet;
        this.file = file;
        loadData();
    }

    public void setDimensions(int wdt, int hgt) {
        this.wdt = wdt;
        this.hgt = hgt;
    }

    public void setPosition(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void loadData() throws IOException {
        this.dataToDraw = new HashMap<String, Integer>();
        BufferedReader in = new BufferedReader(new FileReader(this.file));

        String line;
        String parts[];

        while ((line = in.readLine()) != null) {
            parts = line.split("\t");
            this.dataToDraw.put(parts[0], Integer.parseInt(parts[1]));
        }

    }

    public abstract void drawDiagram();
}
