package com.company;

import javax.sound.sampled.*;
import javax.swing.JFrame;
import java.io.BufferedInputStream;
import java.io.FileInputStream;


public class GameFrame extends JFrame{
    private int units = 20;
    GameFrame(int units){
        this.units = units;

        GamePanel panel = new GamePanel(units);

        try { // this block add music to the game
            FileInputStream inputStream = new FileInputStream("src/com/company/Music/StarWars60.wav");
            AudioInputStream soundIn = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream) );
            AudioFormat format = soundIn.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(soundIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
