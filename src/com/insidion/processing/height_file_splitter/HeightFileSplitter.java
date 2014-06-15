package com.insidion.processing.height_file_splitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HeightFileSplitter {

    // Files, should be set here, no need to make a GUI for something this small
    private static final String[] files = {
            "C:\\Users\\mitchell\\Pictures\\opendatarotterdam_hoogtebestand2012_xyz\\rotterdamopendata_hoogtebestandtotaal_oost.csv"};
    private static final String dest = "C:\\Users\\mitchell\\Pictures\\opendatarotterdam_hoogtebestand2012_xyz\\processed_500.csv";

    // Variables determining the relevance of the data in the file
    private static final int distance = 1000;
    private static final int center_x = 92796;
    private static final int center_y = 436960;

    /**
     * Creates the HeightFileSplitter and runs it
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        HeightFileSplitter splitter = new HeightFileSplitter();
        splitter.processFiles();
    }

    /**
     * Scans the files in the array for coordinates that are relevant and saves them
     * @throws IOException File not found
     */
    public void processFiles() throws IOException {
        // Open the destination File
        PrintWriter writer = new PrintWriter(dest, "UTF-8");
        for (String f : files) {
            // Open a print reader and begin processing
            BufferedReader br = new BufferedReader(new FileReader(f));

            // Some needed variables
            String line;
            long x;
            long y;

            // Loop over each line
            while ((line = br.readLine()) != null) {
                // Split the line on the Comma's
                String[] damn = line.split(",");

                // Try parsing the X and Y as doubles. If it is the header, it will throw
                // a format exception, which we catch.
                try {
                    x = Math.round(Double.parseDouble(damn[0]));
                    y = Math.round(Double.parseDouble(damn[1]));

                    // Check if parsed coordinates are relevant
                    if (x < center_x + distance && x > center_x - distance
                            && y < center_y + distance && y > center_y - distance) {

                        // Write to the file
                        writer.println(x + "," + y + "," + damn[2]);
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }

            // Close reader
            br.close();
        }

        // Close writer
        writer.close();
    }


}
