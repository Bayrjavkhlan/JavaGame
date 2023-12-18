/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FightingGame;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Zero
 */
public class Character extends JLabel{
    int  WIDTH = 80 , HEIGHT = 200;
    int x = 0 , y = 0;

    public Character() {
        this.setBounds(x, y, WIDTH, HEIGHT);
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setVisible(true);
    }
}
