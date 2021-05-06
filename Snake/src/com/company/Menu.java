package com.company;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private JButton button1;
    private JPanel panel1;



    public Menu(){  // constructor for the frame
        this.setContentPane(new Menu().panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake Menu");
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.panel1.add(button1);
        this.add(panel1);
    }





    public static void main(String[] args) {
        new Menu();
    }
}
