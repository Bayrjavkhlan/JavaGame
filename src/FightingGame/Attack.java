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
public class Attack extends JLabel{
        int  WIDTH = 100 , HEIGHT = 20;
    int x = 0 , y = 0;

    public Attack() {
        this.setBounds(x, y, WIDTH, HEIGHT);
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setVisible(true);
}
}
