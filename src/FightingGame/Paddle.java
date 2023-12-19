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
public class Paddle extends JLabel{
    int x=0,y=0;
    int paddleWidth = 100;
    int paddleHeight = 10;
    public Paddle(){
        this.setBounds(x, y, paddleWidth,paddleHeight);
        this.setBackground(Color.black);
        this.setOpaque(true);
    }
}
