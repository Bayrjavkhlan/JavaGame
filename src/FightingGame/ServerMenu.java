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
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static FightingGame.PortChecker.isPortAvailable;

/**
 *
 * @author Zero
 */
public class ServerMenu extends JFrame implements KeyListener{
    public static FightingGame gm;
    public static PingPongGame ppg;
    ServerMenu sm;
    private ServerSocketHandler serverSocketHandler;
    public JButton BackButton;
    public JButton FightingGame;
    public JButton PingPongGame;
    int gameWidth = 1000 , gameHeight = 500 , x = 0 , y = 0;

    public ServerMenu(){
        gm = new FightingGame(); 
        ppg = new PingPongGame();

                if (isPortAvailable(12345)) startServer(12345,sm,gm,ppg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);  
        addKeyListener(this);  

        setSize(gameWidth, gameHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("ServerPlayer");
        Font font = new Font("Arial", Font.PLAIN, 30);
        System.out.println("Server Menu orson");
        setLayout(null);
        BackButton = new JButton("Go back to Main menu");
        BackButton.setFont(font);
        JLabel ipAddressLabel = new JLabel("IP Address: ");
        ipAddressLabel.setBounds(50,75,400,100);
        JLabel connectedPlayerIp = new JLabel("Connected Player IP:");
        connectedPlayerIp.setBounds(50, 125, 400, 100);
        connectedPlayerIp.setFont(font);
        JLabel connectedPlayerIpDisp = new JLabel("Nothing");
        connectedPlayerIpDisp.setBounds(350, 125, 400, 100);
        connectedPlayerIpDisp.setFont(font);
        
        try {
            // Get the local host's IP address
            InetAddress localhost = InetAddress.getLocalHost();
            String ipAddress = localhost.getHostAddress();
            ipAddressLabel.setText("IP Address: " + ipAddress);
            ipAddressLabel.setFont(font);

        } catch (UnknownHostException e) {
            ipAddressLabel.setText("IP Address: N/A");
        }
        BackButton.setBounds(0, 0, 1000, 100);
        BackButton.setBackground(Color.white);
        
        FightingGame = new JButton("2D Figthing Game");
        FightingGame.setBounds( 125, 300, 300,100);
        FightingGame.setBackground(Color.white);
        FightingGame.setFont(font);
        
        PingPongGame = new JButton("Ping Pong Game");
        PingPongGame.setBounds( 525, 300, 300,100);
        PingPongGame.setBackground(Color.white);
        PingPongGame.setFont(font);


        
        
        
        add(BackButton);
        add(ipAddressLabel);
        add(connectedPlayerIp);
        add(connectedPlayerIpDisp);
        add(FightingGame);
        add(PingPongGame);

        BackButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Main btn daragdsan");
                MainMenu mm = new MainMenu();
                mm.setVisible(true);
                
                dispose();
                revalidate();
                repaint();
            }
        });   
        FightingGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            if (serverSocketHandler != null) {
                serverSocketHandler.sendMessage("Fight");
            }
                System.out.println("FightingGame btn daragdsan");
//                gm = new GameFrame();
                gm.setVisible(true);
                add(gm);
                remove(BackButton);
                remove(ipAddressLabel);
                remove(connectedPlayerIp);
                remove(connectedPlayerIpDisp);
                remove(FightingGame);
                remove(PingPongGame);
    

                revalidate();
                repaint();
            }
        });   
        PingPongGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setSize(500, 600);
            if (serverSocketHandler != null) {
                serverSocketHandler.sendMessage("Ping");
            }
                System.out.println("PingPong btn daragdsan");
//                gm = new GameFrame();
                ppg.setVisible(true);
                add(ppg);
                remove(BackButton);
                remove(ipAddressLabel);
                remove(connectedPlayerIp);
                remove(connectedPlayerIpDisp);
                remove(FightingGame);
                remove(PingPongGame);
    

                revalidate();
                repaint();
            }
        });

    }
    private void startServer(int port,ServerMenu sm,FightingGame gm,PingPongGame ppg) {
        serverSocketHandler = new ServerSocketHandler(12345,sm,gm,ppg);
        serverSocketHandler.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

@Override
    public void keyPressed(KeyEvent e) {
        System.out.println("ServerPressed: " + e.getKeyChar());
                char pressedKey = e.getKeyChar();
        // Send the pressed key to the connected client
        if (serverSocketHandler != null) {
            serverSocketHandler.sendMessage(String.valueOf(pressedKey)); 
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public void log(String test){
        System.out.println("GameFrame says: " + test);
    }


        
}
