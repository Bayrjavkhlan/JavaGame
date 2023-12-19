package FightingGame;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler extends Thread {
    FightingGame gm;
    ServerMenu sm;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;

    public ServerSocketHandler(int port, ServerMenu sm,FightingGame gm,PingPongGame ppg) {
        this.gm = gm; 
        this.sm = sm;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started, port: "+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            while (true) {
                String receivedMessage = input.readLine();
                if (receivedMessage != null) {
                    if(ServerMenu.gm != null){
                        ServerMenu.gm.setMovementChar1(receivedMessage);
                        ServerMenu.gm.revalidate();
                        ServerMenu.gm.repaint();
                    }
                    if(ServerMenu.ppg != null){
                        ServerMenu.ppg.setMovementPaddle2(receivedMessage);
                        ServerMenu.ppg.revalidate();
                        ServerMenu.ppg.repaint();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
//            System.out.println("Server Char2 hudulguh gj uzedena");
            if(ServerMenu.gm != null){
                ServerMenu.gm.setMovementChar2(message);
                ServerMenu.gm.revalidate();
                ServerMenu.gm.repaint();
            }
            if(ServerMenu.ppg != null){
                ServerMenu.ppg.setMovementPaddle1(message);
                ServerMenu.ppg.revalidate();
                ServerMenu.ppg.repaint();
            }

        System.out.println("Sending message: " + message);
        if (output != null) {
            output.println(message);

        }

        
    }
    public void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
