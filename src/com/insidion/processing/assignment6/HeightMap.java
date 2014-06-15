package com.insidion.processing.assignment6;

import com.insidion.processing.framework.Assignment;
import processing.data.Table;
import processing.data.TableRow;

import static java.lang.Thread.sleep;

public class HeightMap extends Assignment {
    /**
     * Constants. Water speed is 0.5 meters per hour, and center_x/y are the monument.
     */
    private static final float waterSpeed = 0.5f / 60;
    private static final int center_x = 92796;
    private static final int center_y = 436960;

    /**
     * Value variables. One contains the height for every pixel in the screen, the min/maxValue is to determine
     * the color(range)
     */
    private float[][] values;
    private int maxValue = 11;
    private int minValue = -2;

    private float currentWaterHeight = -10f;
    private int distance = 500;
    private int currentMinutes;
    private int minuteSpeed = 1;
    private Controller con;
    private boolean paused;
    private Table table;


    /**
     * Method to set up the screen. Loads the data from the webserver, prepares it using the prepareData method
     * and Launches the controller linked to this map.
     */
    @Override
    public void setup() {
        size(distance, distance);
        frame.setTitle(this.toString());
        table = loadTable("http://web.insidion.com/processing/o6/processed_1000.csv");
        prepareData();

        con = new Controller(this);
        con.launch();
    }

    /**
     * Prepares the data used in the animation
     */
    private void prepareData() {
        // Initialize the array for the distance
        values = new float[distance][distance];
        // Resize the window to the new distance
        resize(distance, distance);
        frame.setSize(distance, distance);

        // Loop over the table and put the values in the Array, for better access later
        for (int i = 0; i < table.getRowCount(); i++) {
            TableRow row = table.getRow(i);
            // Convert X,Y to screen pixel format
            int x = row.getInt(0) - (center_x - distance / 2);
            int y = row.getInt(1) - (center_y - distance / 2);
            // Check if the data is relevant to the current screen size
            if (x < distance && y < distance && x > -1 && y > -1) {
                values[x][y] = row.getFloat(2);
            }
        }

        /**
         * To avoid issues because of the inconsistencies in the data files, check if there is data for every pixel.
         * If the pixel does not have data (so is 0, as default when constructing an array), take the values
         * of the surrounding pixels and calculate the average.
         */
        for (int i = 0; i < distance; i++) {
            for (int j = 0; j < distance; j++) {
                if (values[i][j] == 0) {
                    float total = 0f;
                    float num = 0;

                    if (i > 0) {
                        total += values[i - 1][j];
                        num += 1;
                    }
                    if (i < distance - 1) {
                        total += values[i + 1][j];
                        num += 1;
                    }

                    if (j > 0) {
                        total += values[i][j - 1];
                        num += 1;
                    }

                    if (j < distance - 1) {
                        total += values[i][j + 1];
                        num += 1;
                    }

                    values[i][j] = total / num;
                }
            }
        }
    }

    /**
     * Method drawing the map. Will only do this if the pause variable is false.
     * Loops over every pixel in the screen, checks the value against the water height, and takes appropriate action.
     */
    @Override
    public void draw() {
        if (!isPaused()) {
            for (int i = 0; i < distance; i++) {
                for (int j = 0; j < distance; j++) {
                    float value = values[i][(distance - 1) - j];

                    if (value < currentWaterHeight) {
                        setWater(i, j);
                    } else {
                        set(i, j, getColorForValue(value));
                    }
                }
            }

            // Cycle complete, prepare height/time variables for the next frame
            setCurrentMinutes(getCurrentMinutes() + getMinuteSpeed());
            setCurrentWaterHeight(-10 + getCurrentMinutes() * waterSpeed);
        }
    }

    /**
     * Sets the pixel as water on the specified x and y.
     */
    private void setWater(int x, int y) {
        set(x, y, color(0, 0, 100 - (int) (Math.random() * 80)));
    }

    /**
     * Method to get the color for a certain height value.
     *
     * @param value Height
     * @return Color
     */
    private int getColorForValue(float value) {
        int range = maxValue + Math.abs(minValue);
        float steps = 255 / range;

        value = value + Math.abs(minValue);

        return color(value * steps, 140 - value * steps, 0);
    }

    /**
     * ToString method for use in the launcher and title
     *
     * @return title
     */
    @Override
    public String toString() {
        return "Assignment Six - Height Map";
    }

    /**
     * Getter for the minuteSpeed variable. Does nothing special
     *
     * @return minuteSpeed
     */
    public int getMinuteSpeed() {
        return minuteSpeed;
    }

    /**
     * Sets the new minutespeed. This will be used to calculate the new time after a frame is drawn
     *
     * @param minuteSpeed new value
     */
    public void setMinuteSpeed(int minuteSpeed) {
        this.minuteSpeed = minuteSpeed;
    }


    /**
     * Gets the current distance. Used in the controller.
     *
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        System.out.println("Resizing the map, this can take a few seconds!");
        // Pause the animation
        this.setPaused(true);
        // Sleep to give the program time to finish the current frame.
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Set the new distance, and call the prepareData method again, which will take care of it.
        this.distance = distance;
        prepareData();

        // Resume the animation
        this.setPaused(false);
        System.out.println("Resize Complete!");
    }

    /**
     * Returns the maximum color value, used in contructing the controller.
     * @return max
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the max value. This will be used in the next frame.
     * @param maxValue new maxValue
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Gets the current water height.
     * @return waterHeight
     */
    public float getCurrentWaterHeight() {
        return currentWaterHeight;
    }

    /**
     * Sets the new waterheight. Also updates the waterHeight text field in the controller
     * @param currentWaterHeight new water height.
     */
    public void setCurrentWaterHeight(float currentWaterHeight) {
        this.currentWaterHeight = currentWaterHeight;
        con.setWaterHeight(currentWaterHeight);
    }


    /**
     * Gets the minimal color value. Used in contructing the Controller.
     * @return minValue
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * Sets the min value. This will be used in the next frame.
     * @param minValue new minValue
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * Gets the currentMinutes variable. Used in the Controller and draw method.
     * @return current Minutes
     */
    public int getCurrentMinutes() {
        return currentMinutes;
    }

    /**
     * Sets the currentMinutes, and updates it in the controller
     * @param currentMinutes new currentMinutes variable
     */
    public void setCurrentMinutes(int currentMinutes) {
        this.currentMinutes = currentMinutes;
        con.setTime(currentMinutes);
    }

    /**
     * Gets the paused boolean, pretty straight forward.
     * @return paused
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Pause or un-pause the animation
     * @param paused pause
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
