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
public class Ball extends JLabel{
    int WIDTH = 15, HEIGHT = 15 , x , y;
    public Ball(){
        this.setBounds(x, y, WIDTH,HEIGHT);
        this.setBackground(Color.black);
        this.setOpaque(true);
    }
    public int[] getBallLocation() {
        int[] location = {x, y, WIDTH, HEIGHT};
        return location;
    }
    public void setBallLocation(int x , int y){
        this.setBounds(x, y, WIDTH,HEIGHT);
    }
    
    
    
}

