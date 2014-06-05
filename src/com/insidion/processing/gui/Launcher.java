package com.insidion.processing.gui;

import com.insidion.processing.assignment1.SketchAssignmentOne;
import com.insidion.processing.assignment2.CrimeSketch;
import com.insidion.processing.assignment2.VolcanicSketch;
import com.insidion.processing.assignment3.WinstcijfersSketch;
import com.insidion.processing.framework.Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by mitchell on 6/5/2014.
 */
public class Launcher {
    private JList listAssignments;
    private DefaultListModel<Assignment> listModel;
    private JButton btLaunch;
    private JButton btExit;
    private JTextPane textPaneDescription;
    private JPanel panelLauncher;
    private JTextPane textPaneMadeBy;

    private static final String madeByText = "These Assignments were made by: \n Mitchell Herrijgers (0867804) \n Houssein Houssein () \n\n Hogeschool Rotterdam, INFDEV08 - Visualisatie";
    private static final String startDescriptionText = "Select one of the assignments in the list on the right. You will find a description here, and can launch the assignment from the launch button!";

    public Launcher() {
        btLaunch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.get(listAssignments.getSelectedIndex()).launch();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Launcher");
        frame.setContentPane(new Launcher().launchPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel launchPanel() {
        textPaneMadeBy.setText(madeByText);
        textPaneDescription.setText(startDescriptionText);
        fillList();
        return panelLauncher;
    }

    private void fillList() {
        listModel = new DefaultListModel<Assignment>();
        listModel.addElement(new SketchAssignmentOne());
        listModel.addElement(new VolcanicSketch());
        listModel.addElement(new CrimeSketch());
        listModel.addElement(new WinstcijfersSketch());
        this.listAssignments.setModel(this.listModel);

        this.listAssignments.getSelectedIndex();
    }
}
