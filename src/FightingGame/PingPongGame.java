/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FightingGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Zero
 */
public class PingPongGame extends JPanel implements Runnable{
    private Thread gameThread;
    private static boolean isGameRunning = false;
    private int roundCounter = 0;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    int x1=200,y1=525;
    int x2=200,y2=25;
    double ballx=225,bally=275;
    double ballSpeedX = 1;
    double ballSpeedY = 1;
    int player1Score = 0;
    int player2Score = 0;
    int GameWidth = 500 , GameHeight = 600;
    public PingPongGame() {
    setSize(GameWidth, GameHeight);
    setLayout(null);
    setBackground(Color.white);

    ball = new Ball();  
    ball.setLocation((int) ballx, (int) bally);

    paddle1 = new Paddle();
    paddle1.setLocation(x1, y1);
    paddle2 = new Paddle();
    paddle2.setLocation(x2, y2);

    add(paddle1);
    add(paddle2);
    add(ball);

    gameThread = new Thread(this);
}

    
public void setMovementPaddle1(String movement) {
    if(movement != null){
        switch (movement.toLowerCase()) {
        case "a":
            if(isGameRunning == false){
                    gameThread.start();
                    isGameRunning = true;
            }
            x1 -=20;
            paddle1.setLocation(x1, y1);
            revalidate();
            repaint();
            break;
        case "d":
            if(isGameRunning == false){
                    gameThread.start();
                    isGameRunning = true;
            }
            x1 += 20;
            paddle1.setLocation(x1, y1);
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
    public void setMovementPaddle2(String movement) {
    switch (movement.toLowerCase()) {
        case "a":
            if(isGameRunning == false){
                    gameThread.start();
                    isGameRunning = true;
            }
            x2 -=20;
            paddle2.setLocation(x2, y2);
            revalidate();
            repaint();
            break;
        case "d":
            if(isGameRunning == false){
                    gameThread.start();
                    isGameRunning = true;
            }
            x2 +=20;
            paddle2.setLocation(x2, y2);
            revalidate();
            repaint();
            break;
        default:
            revalidate();
            repaint();
            break;
    }
    }
    
    public void setPaddle1Location(int xAxis){
        x1=xAxis;
        paddle1.setLocation(xAxis, y1);
        revalidate();
        repaint();
    }
    public int getPaddle1Location(){
        return x1;
    }
    public void setPaddle2Location(int xAxis){
        x1=xAxis;
        paddle2.setLocation(xAxis, y2);
        revalidate();
        repaint();
    }
    public int getPaddle2Location(){
        return x1;
    }
    //---------------------------------------------------------------------------------------
    @Override
    public void run() {
        while (isGameRunning) {
            checkScore();
            updateBallPosition();
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
public void ballRandomMove() {
    switch (roundCounter % 4) {
        case 0: // Up Left
            ballSpeedX = -1; // Move left
            ballSpeedY = -1; // Move up
            break;
        case 1: // Down Right
            ballSpeedX = 1; // Move right
            ballSpeedY = 1; // Move down
            break;
        case 2: // Up Right
            ballSpeedX = 1; // Move right
            ballSpeedY = -1; // Move up
            break;
        case 3: // Down Left
            ballSpeedX = -1; // Move left
            ballSpeedY = 1; // Move down
            break;
    }
    roundCounter++;
}

private void resetBall(){
    ballx = 225;
    bally = 275;
    ball.setLocation((int) ballx, (int) bally);
    ballRandomMove();
}
    
private boolean isCollision(Ball ball, Paddle paddle) {
    Rectangle ballBounds = ball.getBounds();
    Rectangle paddleBounds = paddle.getBounds();
    return ballBounds.intersects(paddleBounds);
}
    //endes tsaashaa chatgptges avsan code oilgod uurchilj bichere tged uurchilj bichsen ghde ene commig end uldeey
    int a=1;

private void updateBallPosition() {
        if(isCollision(ball, paddle1)){
        System.out.println("Collision detected"+a);
        a++;
    }
        else if(checkScore()){
            System.out.println("One of them scores");
            resetBall();
        }
    ballx +=  ballSpeedX;
    bally += ballSpeedY;
    if (isCollision(ball, paddle1) || isCollision(ball, paddle2)) {
        ballSpeedY = -ballSpeedY;
        if (isCollision(ball, paddle1)) {
            bally = paddle1.getY() - ball.getHeight();
        } else if (isCollision(ball, paddle2)) {
            bally = paddle2.getY() + paddle2.getHeight();
        }
    }
    if (bally < 0 || bally > 550) {
        // Reverse the vertical direction of the ball
        ballSpeedY = -ballSpeedY;
    }

    // Check if the ball hits the left or right walls
    if (ballx < 0 || ballx > (500-25)) {
        // Reverse the horizontal direction of the ball
        ballSpeedX = -ballSpeedX;
    }

    // Reposition the ball using setBallLocation
    ball.setLocation((int) ballx, (int) bally);
}
    private Boolean checkScore() {
        if (bally <= 0) {  // Player 2 scores
            player2Score++;
            resetBall();
            return true;
        } else if (bally >= 550) {  // Player 1 scores
            player1Score++;
            resetBall();
            return true;
        }
        return false;
    }

    // Add a method to stop the game thread
    public void stopGame() {
        isGameRunning = false;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Redraw the game elements (ball, paddles, etc.) in this method
        // You can also display the scores on the screen
        g.setColor(Color.BLUE);
        g.drawString("Player 1: " + player1Score, 10, 20);
        g.drawString("Player 2: " + player2Score, 10, 550);
    }
    
}
