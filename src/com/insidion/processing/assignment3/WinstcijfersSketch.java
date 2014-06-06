package com.insidion.processing.assignment3;

import processing.data.Table;

/**
 * Created by mitchell on 6/5/2014.
 */
public class WinstcijfersSketch extends TableLineGraph {


    @Override
    public String toString() {
        return "Assignment Three - Winstcijfers";
    }

    @Override
    protected Table prepareSketchAndFillData() {
        return loadTable("http://web.insidion.com/processing/o3/Winstcijfers.tsv", "header, tsv");
    }
}
