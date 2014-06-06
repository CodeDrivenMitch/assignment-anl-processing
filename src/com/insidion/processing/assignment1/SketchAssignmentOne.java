package com.insidion.processing.assignment1;

import com.insidion.processing.framework.Assignment;

import java.io.IOException;

/**
 * Created by mitchell on 5/14/2014.
 */
public class SketchAssignmentOne extends Assignment {
    private Diagram cirkel;
    private Diagram bar;
    private static final String workingdir = "C:\\Users\\mitchell\\IdeaProjects\\DEVopdracht1\\";

    public void setup() {
        colorMode(HSB, 360, 100, 100);
        size(400, 700);
        background(0, 0, 255);

        frame.setTitle(this.toString());
        try {
            createDiagrams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDiagrams() throws IOException {
        cirkel = new CirkelDiagram(this, workingdir + "cirkel.txt");
        cirkel.setPosition(0, 100);
        cirkel.setDimensions(350, 200);
        bar = new BarDiagram(this, workingdir + "bar.txt");
        bar.setPosition(0, 400);
        bar.setDimensions(300, 250);
    }

    public void draw() {
        this.clear();
        this.noLoop();
        cirkel.drawDiagram();
        bar.drawDiagram();
    }

    @Override
    public String toString() {
        return "Assignment One: Getting Started";
    }


}
