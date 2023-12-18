package FightingGame;

import java.awt.Color;
import javax.swing.JLabel;

public class HealthBar extends JLabel {
    private int health;
    private int WIDTH = 200, HEIGHT = 30;
    private int x, y; // Adjusted initial position

    public HealthBar() {
        this.setBounds(x, y, WIDTH, HEIGHT);
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setVisible(true);
        this.health = 100; // Set initial health value
        updateLabel();
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(){
        health = 100;
    }

    public void decreaseHealthLeft() {
        // Decrease health for the left character (Char1)
        health -= 10;
        if (health < 0) {
            health = 0;
        }
        updateLabel();
    }

    public void decreaseHealthRight() {
        // Decrease health for the right character (Char2)
        health -= 10;
        if (health < 0) {
            health = 0;
        }
        updateLabel();
    }

    private void updateLabel() {
        this.setText("Health: " + health);
        repaint();
        revalidate();
    }
}
