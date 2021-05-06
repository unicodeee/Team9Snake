package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame{

    private JButton startGameButton;
    private JPanel panelMain;

    public MainMenu() {

// this block is setting for the menu window.
        this.add(panelMain);
        this.setTitle("My Snake Game");
        this.setPreferredSize(new Dimension(400, 400)); // set size for the frame
        this.setContentPane(panelMain); //The setContentPane() method allows you to replace the content panel of the JFrame, but it is rarely necessary.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.getContentPane().setBackground(Color.GRAY);


        startGameButton.addActionListener(new ActionListener() {  // runs game when button clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame();  // this runs the code in GameFrame.java, which runs the panel for the game/ making the game run
            }
        });
    }

    public static void main(String[] args) {

        new MainMenu();

    }
}
