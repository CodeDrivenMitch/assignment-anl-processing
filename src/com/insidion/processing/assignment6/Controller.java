package com.insidion.processing.assignment6;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mitchell on 6/15/2014.
 */
public class Controller {
    private JButton btStart;
    private JButton btPause;
    private JButton btReset;
    private JSpinner spMapSize;
    private JButton btApplyMapSize;
    private JSpinner spSpeed;
    private JButton btApplySpeed;
    private JTextField tfCurrentTime;
    private JTextField tfCurrentWaterHeight;
    private JPanel controllerPanel;
    private JSpinner spMaxH;
    private JSpinner spMinH;

    private HeightMap map;

    public Controller(HeightMap map) {
        this.map = map;

    }

    /**
     * Launches a new controller
     */
    public void launch() {
        // Create the frame
        JFrame frame = new JFrame("Controller");
        frame.setContentPane(this.controllerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Set some default values
        spMapSize.setValue(map.getDistance());
        spSpeed.setValue(map.getMinuteSpeed());
        spMaxH.setValue(map.getMaxValue());
        spMinH.setValue(map.getMinValue());
        tfCurrentTime.setText("0 minutes");
        tfCurrentWaterHeight.setText(map.getCurrentWaterHeight() + "m");

        // Set listeners for buttons and spinners
        setListeners();
    }

    /**
     * Creates the GUI listeners.
     */
    private void setListeners() {
        // Start Button, simply sets pause to false in the map
        btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.setPaused(false);
            }
        });

        // Pause Button, simply sets pause to true in the map
        btPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.setPaused(true);
            }
        });

        // Reset Button, simply sets currentMinutes to 0 in the map
        btReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.setCurrentMinutes(0);
            }
        });

        // Apply Button  for the map size.
        btApplyMapSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.setDistance((Integer) spMapSize.getValue());
            }
        });

        // Apply Button for the minute Speed
        btApplySpeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.setMinuteSpeed((Integer) spSpeed.getValue());
            }
        });

        // Change listener for the maxValue
        spMaxH.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                map.setMaxValue((Integer) spMaxH.getValue());
            }
        });

        // Change listener for the minValue
        spMinH.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                map.setMinValue((Integer) spMinH.getValue());
            }
        });
    }


    /**
     * Sets the trime field in the GUI
     * @param time new Time
     */
    public void setTime(int time) {
        int minutes = time % 60;
        int hours = (time - minutes) / 60;

        tfCurrentTime.setText(hours + " hours, " + minutes + " minutes");
    }

    /**
     * Sets the water height in the GUI
     * @param height new water height
     */
    public void setWaterHeight(float height) {
        tfCurrentWaterHeight.setText(height + "m");
    }
}
