/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FightingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Zero
 */
public class ClientMenu extends JFrame implements KeyListener{
    public static FightingGame gm;
    public static PingPongGame ppg;

    int gameWidth = 1000 , gameHeight = 500 , x = 0 , y = 0;
    int port = 12345;
    JButton BackButton;
    JButton Enter;
    JTextArea ServerIP;
    JLabel enterIp;

    private ClientSocket clientSocket;
    
    public ClientMenu(){
        gm = new FightingGame();
        ppg = new PingPongGame();
//        connectToServer(localhost.getText(), port);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setFocusable(true);  
        setSize(gameWidth, gameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClientPlayer");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        
        Font font = new Font("Arial", Font.PLAIN, 30);
        Border border = new LineBorder(Color.black, 2, true);
        
        BackButton = new JButton("Back to Main menu");
        BackButton.setBounds(0, 0, 1000, 100);
        BackButton.setBackground(Color.white);
        BackButton.setFont(font);
        enterIp = new JLabel("Enter server's ip:");
        enterIp.setBounds(0, 125,250,100);
        enterIp.setFont(font);
        ServerIP = new JTextArea();
        ServerIP.setBorder(border);
        ServerIP.setFont(font);
        ServerIP.setBounds(240, 150, 240, 50);
        Enter = new JButton("Enter");
        Enter.setBounds( 480, 150, 120,50);
        Enter.setBackground(Color.white);
        Enter.setBorder(border);
//        connectToServer(ServerIP.getText(),12345);
//        connectToServer("localhost", 12345);
        //192.168.1.12 serverin IP
        
        add(BackButton);
        add(Enter);
        add(enterIp);
        add(ServerIP);

        

        Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Connect to the server
                connectToServer("localhost", 12345,gm,ppg);

                // Set focus on the ClientMenu to capture key events
                requestFocusInWindow();
                

//                clientSocket.sendMessage(P2ChosenCharNum);
                // Disable the Enter button after connecting
//                Enter.setEnabled(false);

                revalidate();
                repaint();
            }
        });

        BackButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                System.out.println("Main btn daragdsan");
                MainMenu mm = new MainMenu();
                mm.setVisible(true);
                dispose();
                revalidate();
                repaint();
            }
        });   
        ServerIP.addKeyListener(this);

    }
    public void displayGameFrame() {  
        System.out.println("displayGameFrame ajilsan");
//        if (gm == null) {
            System.out.println("displayGameFrame gm==null ajilsan");
            gm = new FightingGame();
            gm.setBounds(0, 0, gameWidth, gameHeight);
            add(gm);
        remove(BackButton);
        remove(Enter);
        remove(enterIp);
        remove(ServerIP);
        remove(ppg);

            revalidate();
            repaint();
//        }
    }
        public void displayPingPong() {
            gameWidth = 500;
            gameHeight = 600;
            this.setSize(gameWidth,gameHeight);
        System.out.println("displayPingPong ajilsan");
//        if (gm == null) {
            System.out.println("displayPingPong gm==null ajilsan");
            ppg = new PingPongGame();
            ppg.setBounds(0, 0, gameWidth, gameHeight);

            add(ppg);
        remove(BackButton);
        remove(Enter);
        remove(enterIp);
        remove(ServerIP);
        remove(gm);

            revalidate();
            repaint();
//        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("You typed keycharacter: " + e.getKeyChar());
                char pressedKey = e.getKeyChar();
        System.out.println("Client pressed: " + pressedKey);
        if (clientSocket != null) {
//            System.out.println("ene ajiljinu");
            clientSocket.sendMessage(String.valueOf(pressedKey));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
            public void connectToServer(String serverIP, int port,FightingGame gm,PingPongGame ppg) {
        // Establish a socket connection when the user enters the server's IP
        clientSocket = new ClientSocket(this,serverIP, 12345,gm,ppg);

        // Display a message based on the connection status
        if (clientSocket != null) {
            System.out.println("Connected successfully");
        } else {
            System.out.println("Error connecting to the server");
        }
    }

}
