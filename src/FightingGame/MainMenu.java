/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FightingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Zero
 */
public class MainMenu extends JFrame {
    
    int gameWidth = 1000 , gameHeight = 500 , x = 0 , y = 0;
    JButton client = new JButton(" Enter to Server");
    JButton server = new JButton(" Create a Server");

    public MainMenu(){
//            getContentPane().setBackground(Color.yellow);

        setBackground(Color.yellow);
        setFocusable(true);  
        setSize(gameWidth, gameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("2D Fighting Game");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        
        Font ServerClientFont = new Font("Arial", Font.PLAIN,30); 
        Border ServerClientBorder = new LineBorder(Color.black, 2, true);
        
        JLabel GameName = new JLabel("2D Fighting Game");
        GameName.setBounds(300, 20, 500, 60);
        Font GameNameFont = new Font("Arial", Font.PLAIN, 50);
        GameName.setFont(GameNameFont);
        add(GameName);
        
        server = new JButton(" Create a Server");
        server.setBounds(380, 160, 250, 100);
        server.setFont(ServerClientFont);
        server.setBorder(ServerClientBorder);
        server.setBackground(Color.white);
        add(server);
        
        client = new JButton(" Enter to Server");
        client.setBounds(380,300,250,100);
        client.setFont(ServerClientFont);
        client.setBorder(ServerClientBorder);
        client.setBackground(Color.white);
        add(client);
        client.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Client btn daragdsan");
                ClientMenu cm = new ClientMenu();
                cm.setVisible(true);
                
                dispose();
                revalidate();
                repaint();
            }
        });   
            server.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Server btn daragdsan");
                ServerMenu sm = new ServerMenu();
                sm.setVisible(true);
                
                dispose();
                revalidate();
                repaint();
            }
        }); 
    }
 
}
