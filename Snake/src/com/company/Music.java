package com.company;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Music {
    Music(){
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
    }
}
