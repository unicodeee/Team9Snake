package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame{

    private JButton startGameButton;
    private JPanel panelMain;
    private JButton buttonScale;
    private JLabel gameUnits;

    private int units=5; // game map units
    private int maxUnitsAllowed = 100; // game map units limits

    public MainMenu() {

// this block is setting for the menu window.
        this.add(panelMain);
        this.setTitle("My Snake Game");
        this.setPreferredSize(new Dimension(400, 350)); // set size for the frame
        this.setContentPane(panelMain); //The setContentPane() method allows you to replace the content panel of the JFrame, but it is rarely necessary.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.getContentPane().setBackground(Color.BLACK);

        // menu units label settings
        gameUnits.setForeground(Color.WHITE);
        gameUnits.setFont(new Font("Ink Free", Font.BOLD, 30));
        gameUnits.setText("Units: "+units);
        super.update(this.getGraphics());

        //buttons appearance settings
        buttonScale.setFocusPainted(false);
        startGameButton.setFocusPainted(false); // turn of image border

        startGameButton.addActionListener(new ActionListener() {  // runs game when button clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame(units);  // this runs the code in GameFrame.java, which runs the panel for the game/ making the game run
            }
        });

        buttonScale.addActionListener(new ActionListener() {  // adjust game's units when button clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if (units <= maxUnitsAllowed - 5){ // limit units to 100
                    units += 5;
                }
                gameUnits.setText("Units: "+units); // print/update unit label
                gameUnits.update(getGraphics());
            }
        });
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
