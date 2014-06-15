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
 // Na met een onclick method de hoeken van de kaart vast te stellen worden ze hier als variabele genoteerd
 // als de meetstaaf voor de rest van de pixel co-ordinaten
    private PImage img;
    Table table;
    Point upperleft = new Point(102, 151);
    Point downright = new Point(790, 370);

    public void setup() {
        super.setup();
        colorMode(HSB, 360, 100, 100);
        img = loadImage("http://i.imgur.com/4Hg4sa6.jpg", "jpg");
        loadData();
        colorMode(HSB, 100);
// De kaart word hier geladen en de scherm grootte word hier bepaald
        resize(800, 500);



    }

    public void draw() {
        img.resize(width, height);
        image(img, 0, 0);
// hier worden de daadwerkelijke globale co-ordinaten van het scandinavische ijland gelinkt aan de pixel co-ordinaten
// direct gevolgd door de code die de schaal bepaald en naar hand daarvan een ellipse kleurt. en die de formaat bepaalt door
// het verwerken van de duur van de beving.
        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow row = table.getRow(i);
            int y = (int) map((float) row.getDouble("lat"), 66, 64, upperleft.y, downright.y);
            int x = (int) map((float) row.getDouble("lng"), 24, 14, upperleft.x, downright.x);

            int schaal = (int) row.getDouble("Kracht");

            fill(98, (float) (10 + 0.60 * row.getDouble("Duur")), 90);
            ellipse(x, y, schaal * 5, schaal * 5);
        }
    }


    private void loadData() {
//dataset word geladen, latitude en longtitude tabellen worden duidelijk en apart opgeroepen.
        table = loadTable("http://web.insidion.com/data.csv", "header");
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

    @Override
    public String toString() {
        return "Assignment Two - Volcanic Activity";
    }

}
