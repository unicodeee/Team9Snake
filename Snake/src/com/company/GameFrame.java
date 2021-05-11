package com.company;

import javax.sound.sampled.*;
import javax.swing.JFrame;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class GameFrame extends JFrame{
    private int units = 20;
    GameFrame(int units){

        GamePanel panel = new GamePanel(units);
        Music music = new Music();

        this.units = units;
        //frame settings
        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // fits all the components to the frame
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
