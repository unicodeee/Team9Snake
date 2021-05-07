package com.company;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH =     600;
    static final int SCREEN_HEIGHT =     600;
    static final int UNIT_SIZE =     20; // This number Should be fully divided by HEIGHT and WIDTH
    static final int GAME_UNITS =     (SCREEN_HEIGHT*SCREEN_WIDTH)/UNIT_SIZE;
    static final int ROUND_CORNERS =     20;

    static final int DELAY =     50;
    int x[] =  new int[GAME_UNITS];// Holds all the x coordinates of body parts, including the head ( snake <= game units)
    int y[] =  new int[GAME_UNITS];// Holds all the y coordinates of body parts, including the head ( snake <= game units)
    int bodyParts = 2;
    int appelsEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timerl;
    Random random;
    int count=3;

    GamePanel(){

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

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();


    }
    public void startGame(){
        newApple();
        running = true;
        if (timerl == null) {
            timerl = new Timer(DELAY, this); //???
            timerl.start();
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
            for (int i =0; i < (SCREEN_WIDTH / UNIT_SIZE); i ++){
                g.drawLine(i*UNIT_SIZE, 0,i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            g.setColor(Color.CYAN);
            g.fillRoundRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE, ROUND_CORNERS, ROUND_CORNERS ); // draw apple

            for (int i =0; i < bodyParts; i++){
                if(i==0){ // this is the head / colorize the head
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    //g.setColor(Color.orange);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }


            g.setColor(Color.WHITE);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score:"+ appelsEaten, (SCREEN_WIDTH - metrics.stringWidth("Score:"+ appelsEaten))/2, g.getFont().getSize() );

        }
        else {
            gameOver(g);
        }
    }
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }
    public void replay(){
        startGame();
    }
    public void move(){
       for(int i = bodyParts; i>0;i-- ){
           x[i] = x[i-1];
           y[i] = y[i-1];
       }
       switch (direction){
           case 'U':
               y[0] = y[0] - UNIT_SIZE;
                break;
           case 'D':
               y[0] = y[0] + UNIT_SIZE;
               break;
           case 'L':
               x[0] = x[0] - UNIT_SIZE;
               break;
           case 'R':
               x[0] = x[0] + UNIT_SIZE;
               break;
       }
    }
    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)){
            newApple();
            bodyParts++;
            appelsEaten++;
        }
    }
    public void checkCollisions(){
        for (int i = bodyParts; i>0; i--){
            if ((x[0] ==  x[i]) && (y[0] == y[i])){ // check if the head hits the body
                running = false;
            }
        }
        // check if the snake hits the right border
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        if (x[0] < 0) {
            running = false;
        }
        if (y[0] > SCREEN_HEIGHT-UNIT_SIZE) {
            running = false;
        }
        if (y[0] < 0) {
            running = false;
        }

    }
    public void restartIn(Graphics g){

        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metric3 = getFontMetrics(g.getFont());
        String reloading = "Game reloading in: " + count;
        g.drawString(reloading, (SCREEN_WIDTH - metric3.stringWidth("You Suck"))/2, SCREEN_HEIGHT-100); // put the text in the center
        count--;
        try {
            TimeUnit.SECONDS.sleep(1); // wait 1 sec
        } catch (InterruptedException exp) {

        }

    }

    public void gameOver(Graphics g) { // game over - text
        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score:"+ appelsEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score:"+ appelsEaten))/2, g.getFont().getSize() );
        // Last message
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Your Suck!", (SCREEN_WIDTH - metrics2.stringWidth("You Suck"))/2, SCREEN_HEIGHT/2); // put the text in the center


        if (count >=0){
            restartIn(g); // keep drawing gameover screen
        }else {
            resetSettings();
            startGame();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public void resetSettings(){
        appelsEaten = 0;
        count = 3;
        direction = 'R';
        x =  new int[GAME_UNITS];// Holds all the x coordinates of body parts, including the head ( snake <= game units)
        y =  new int[GAME_UNITS];// Holds all the y coordinates of body parts, including the head ( snake <= game units)
        bodyParts = 2;
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    running=true;
                    break;
            }

        }
    }
}