package com.company;

import javax.swing.JFrame;



public class GameFrame extends JFrame{

    GameFrame(){

        GamePanel panel = new GamePanel();

        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // fits all the components to the frame
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
