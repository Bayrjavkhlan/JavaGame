/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//garign gargadag bolgochod coliision detect higed hb bartai bolgod hp bar 0 bolhod duusgadag bolgochih tgel bolo
package FightingGame;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Zero
 */
public class FightingGame extends JPanel{
    int WIDTH = 1000, HEIGHT = 500;
    int attackWidth = 150, attackheight = 25;
    Character Char1 = new Character();
    Character Char2 = new Character();
    Attack Attack1 = new Attack();
    Attack Attack2 = new Attack();
    HealthBar Char1Health = new HealthBar();
    HealthBar Char2Health = new HealthBar();
    HealthBar Char1HealthBack = new HealthBar();
    HealthBar Char2HealthBack = new HealthBar();
    Timer timer1;
    Timer timer2;
    static int x1=100,y1=270;
    static int x2=820,y2=270;
    int att1xHandPos = 85 ,att1yHandPos = 80;
    int att2xHandPos = 155 ,att2yHandPos = 80;
    int char1HealthLoc = 00 , char2HealthLoc = 690 , charHealthY = 0;
    int char1HealthWidth = 300, char2HealthWidth = 300 , charHealthHeight=50;
    public FightingGame() {
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setBackground(Color.white);
        Char1.setBackground(Color.blue);
        Char2.setBackground(Color.red);
        
        Char1.setLocation(x1, y1);
        Char2.setLocation(x2, y2);
        
        Attack1.setBounds(x1+att1xHandPos, y1+att1yHandPos, attackWidth, attackheight);
        Attack1.setBackground(Color.green);
        Attack1.setVisible(false);//end suuld false
        
        Attack2.setBounds(x2-att2xHandPos, y2+att2yHandPos, attackWidth, attackheight);
        Attack2.setBackground(Color.green);
        Attack2.setVisible(false);//end bas martva
        
        Char1Health.setBackground(Color.GREEN);
        Char1Health.setLocation(char1HealthLoc,charHealthY);
        Char1Health.setBounds(char1HealthLoc, charHealthY, char1HealthWidth, charHealthHeight);
        Char1Health.setVisible(true);
        Char1HealthBack.setBackground(Color.RED);
        Char1HealthBack.setLocation(char1HealthLoc,charHealthY);
        Char1HealthBack.setBounds(char1HealthLoc, charHealthY, char1HealthWidth, charHealthHeight);
        Char1HealthBack.setVisible(true);
        
        Char2Health.setBackground(Color.GREEN);
        Char2Health.setLocation(char2HealthLoc,charHealthY);
        Char2Health.setBounds(char2HealthLoc, charHealthY, 300, charHealthHeight);
        Char2Health.setVisible(true);
        Char2HealthBack.setBackground(Color.RED);
        Char2HealthBack.setLocation(char1HealthLoc,charHealthY);
        Char2HealthBack.setBounds(char1HealthLoc, charHealthY, 300, charHealthHeight);
        Char2HealthBack.setVisible(true);
        
        this.add(Attack1);
        this.add(Attack2);
        this.add(Char1);
        this.add(Char2);
        this.add(Char1Health);
        this.add(Char2Health);
        
        timer1 = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Attack1.setVisible(false);
                repaint();
                revalidate();
            }
        });
        timer2 = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Attack2.setVisible(false);
                repaint();
                revalidate();
            }
        });
        repaint();
        revalidate();
    }
    public void char1Attack(){
        Attack1.setVisible(true);
        timer1.start();

        this.add(Attack1);
        repaint();
        revalidate();
    }
    public void char2Attack(){
        Attack2.setVisible(true);
        timer2.start();

        this.add(Attack2);
        repaint();
        revalidate();
        

    }
    public Color getBackgroundColor() {
        return getBackground();
    }

    public void setBackgroundColor(Color color) {
        setBackground(color);
        revalidate();
        repaint();
    }


    public void setBackgroundColorByStringChar1(String colorString) {
    if(colorString != null){
            switch (colorString.toLowerCase()) {
        case "a":
            x1-=20;
            Char1.setLocation(x1, y1);
            Attack1.setLocation(x1+att1xHandPos, y1+att1yHandPos);
            revalidate();
            repaint();
            break;
        case "d":
            x1+=20;
            Char1.setLocation(x1, y1);
            Attack1.setLocation(x1+att1xHandPos, y1+att1yHandPos);
            revalidate();
            repaint();
            break;
        case "s":
            revalidate();
            repaint();
            break;
        case "w":
            revalidate();
            repaint();
            break;
        case "j":
            checkCollisions();
            char1Attack();
            revalidate();
            repaint();
            break;
        default:
            revalidate();
            repaint();
            break;
    }
    }
    else{
        System.out.println("color string deer null irchihle");
    }

}
    public void setBackgroundColorByStringChar2(String colorString) {
    switch (colorString.toLowerCase()) {
        case "a":
            checkCollisions();
            x2-=20;
            Char2.setLocation(x2, y2);
            Attack2.setLocation(x2-att2xHandPos, y2+att2yHandPos);
            revalidate();
            repaint();
            break;
        case "d":
            checkCollisions();
            x2+=20;
            Char2.setLocation(x2, y2);
            Attack2.setLocation(x2-att2xHandPos, y2+att2yHandPos);
            revalidate();
            repaint();
            break;
        case "s":
            revalidate();
            repaint();
            break;
        case "w":
            revalidate();
            repaint();
            break;
        case "j":
            checkCollisions();
            char2Attack();
            revalidate();
            repaint();
            break;
        default:
            revalidate();
            repaint();
            break;
    }
    }
    public void checkCollisions() {
            System.out.println("Chollision check");
        Rectangle Char1Bounds = Char1.getBounds();
        Rectangle Char2Bounds = Char2.getBounds();
        Rectangle attack1Bounds = Attack1.getBounds();
        Rectangle attack2Bounds = Attack2.getBounds();
            System.out.println("Char1: " + Char1Bounds.x);
            System.out.println("Char2: " + Char2Bounds.x);
            System.out.println("attack1: " + (attack1Bounds.x+150));
            System.out.println("attack2: " + (attack2Bounds.x-150));
//            if(Char1Bounds.x>(attack2Bounds.x-150)&& Attack2.isVisible()){
//                System.out.println("Char1 got hit");
//            }
//            if(Char2Bounds.x<(attack1Bounds.x+150)&& Attack1.isVisible()){
//                System.out.println("Char2 got hit");
//            }
            
            
        if (Char1Bounds.intersects(attack2Bounds) && Attack1.isVisible()) {
            x2 += 150;
            System.out.println("Char2 got hit");
            Char1Health.decreaseHealthLeft();
            Char1Health.setBounds(char1HealthLoc, charHealthY, char1HealthWidth-20, charHealthHeight);
            Char2.setLocation(x2, y2);
            Attack2.setLocation(x1+att1xHandPos, y1+att1yHandPos);
            repaint();
            revalidate();
            
            
        }
        if (Char2Bounds.intersects(attack1Bounds) && Attack2.isVisible()) {
            x1 -= 150;
            System.out.println("Char1 got hit");
            Char2Health.decreaseHealthRight();
            Char2Health.setBounds(char2HealthLoc+20, charHealthY, char2HealthWidth-20, charHealthHeight);
            Char1.setLocation(x1, y1);
            Attack1.setLocation(x1+att1xHandPos, y1+att1yHandPos);

            repaint();
            revalidate();
//            if((Char2Health.getHealth() == 0) || (Char1Health.getHealth() == 0)){
//                remove(Char1);
//                remove(Char1Health);
//                remove(Attack1);
//                remove(char1HealthWidth);
//                remove(Char2);
//                remove(Char2Health);
//                remove(Attack2);
//                remove(char2HealthWidth);
//                Char1Health.setHealth();
//                Char2Health.setHealth();
//            }

        }
    }
}