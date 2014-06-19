package com.insidion.processing.assignment2;

import com.insidion.processing.framework.Assignment;
import processing.core.PImage;
import processing.data.Table;
import processing.data.TableRow;

import java.awt.*;

/**
 * Created by mitchell on 5/16/2014.
 */
public class VolcanicSketch extends Assignment {
    /*
     Na met een onclick method de hoeken van de kaart vast te stellen worden ze hier als variabele genoteerd
     als de meetstaaf voor de rest van de pixel co-ordinaten
    */
    private PImage img;
    Table table;
    Point upperleft = new Point(102, 151);
    Point downright = new Point(790, 370);

    /**
     * Sets up the sketch
     */
    public void setup() {
        super.setup();
        colorMode(HSB, 360, 100, 100);
        img = loadImage("http://i.imgur.com/4Hg4sa6.jpg", "jpg");
        loadData();
        colorMode(HSB, 100);
        // De kaart word hier geladen en de scherm grootte word hier bepaald
        resize(800, 500);



    }

    /**
     * Draws the sketch
     */
    public void draw() {
        img.resize(width, height);
        image(img, 0, 0);
        /*
            hier worden de daadwerkelijke globale co-ordinaten van het scandinavische ijland gelinkt aan de pixel co-ordinaten
            direct gevolgd door de code die de schaal bepaald en naar hand daarvan een ellipse kleurt. en die de formaat bepaalt door
            het verwerken van de duur van de beving.
        */
        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow row = table.getRow(i);
            int y = (int) map((float) row.getDouble("lat"), 66, 64, upperleft.y, downright.y);
            int x = (int) map((float) row.getDouble("lng"), 24, 14, upperleft.x, downright.x);

            int schaal = (int) row.getDouble("Kracht");

            fill(98, (float) (10 + 0.60 * row.getDouble("Duur")), 90);
            ellipse(x, y, schaal * 5, schaal * 5);


            drawLegend();
        }
    }


    /**
     * Loads the data from the webserver
     */
    private void loadData() {
         //dataset word geladen, latitude en longtitude tabellen worden duidelijk en apart opgeroepen.
        table = loadTable("http://web.insidion.com/processing/o2/data.csv", "header");
        table.addColumn("lat");
        table.addColumn("lng");
         // forloop gaat alle rijen af en roept de waardes op.
        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow row = table.getRow(i);
            // Formule om de long/lat te bereken van graden minuten seconden. Formule = Graden + Minuten/60 + seconden/ 3600
            double lat = row.getDouble("graden") + (row.getDouble("minuten") / 60) + (row.getDouble("seconden") / 3600);
            row.setDouble("lat", lat);
            double lng = row.getDouble("b_graden") + (row.getDouble("b_minuten") / 60) + (row.getDouble("b_seconden") / 3600);
            row.setDouble("lng", lng);
        }


    }

    /**
     * Draws the legend.
     */
    private void drawLegend() {
        // Draw the legend text
        stroke(0, 0, 0);
        fill(color(255, 0, 255));
        rect(width, height, -260, -150);

        // Draw the legend text
        fill(0, 0, 0);
        textAlign(RIGHT);
        text("Shorter quake", width - 35, height - 120);
        text("Longer quake", width - 35, height - 10);
        text("Stronger quake", width - 165, height - 115);
        text("Weaker quake", width - 165, height - 15);

        // Draw the legend shape of length. Uses a self-made gradient
        rect(width - 30, height - 130, 20, 121);
        for (int i = 0; i < 120; i++) {
            stroke(100, (float) 1.8 * i / 2, 100);
            line(width - 29, height - 129 + i, width - 11, height - 129 + i);
        }

        // Draw the legend shape of quake strength
        stroke(0, 0, 0);
        fill(color(255, 0, 255));
        ellipse(width - 140, height - 120, 10, 10);
        ellipse(width - 140, height - 90, 15, 15);
        ellipse(width - 140, height - 60, 20, 20);
        ellipse(width - 140, height - 20, 30, 30);


    }

    @Override
    public String toString() {
        return "Assignment Two - Volcanic Activity";
    }

}
