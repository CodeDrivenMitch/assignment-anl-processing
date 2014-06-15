package com.insidion.processing.height_file_splitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mitchell on 6/13/2014.
 */
public class HeightFileSplitter {
    private static final String[] files = {
            "C:\\Users\\mitchell\\Pictures\\opendatarotterdam_hoogtebestand2012_xyz\\rotterdamopendata_hoogtebestandtotaal_oost.csv"};
    private static final String dest = "C:\\Users\\mitchell\\Pictures\\opendatarotterdam_hoogtebestand2012_xyz\\processed_500.csv";
    private static final int distance = 500;
    private static final int center_x = 92796;
    private static final int center_y = 436960;

    public static void main(String[] args) throws IOException {
        HeightFileSplitter splitter = new HeightFileSplitter();
        splitter.processFiles();
    }

    public void processFiles() throws IOException {
        for (String f : files) {
            PrintWriter writer = new PrintWriter(dest, "UTF-8");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            long x;
            long y;
            while ((line = br.readLine()) != null) {
                String[] damn = line.split(",");
                try {
                    x = Math.round(Double.parseDouble(damn[0]));
                    y = Math.round(Double.parseDouble(damn[1]));
                    if (x < center_x + distance && x > center_x - distance
                            && y < center_y + distance && y > center_y - distance) {

                        writer.println(x + "," + y + "," + damn[2]);
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
            writer.close();
            br.close();
        }
    }


}
