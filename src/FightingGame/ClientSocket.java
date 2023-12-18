package FightingGame;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
    ClientMenu cm;
    FightingGame gm;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientSocket(ClientMenu cm,String serverIP, int port,FightingGame gm) {
        this.gm = gm;
        this.cm = cm;
        try {
            socket = new Socket(serverIP, port);
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(), true);
            
            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String receivedMessage = receiveMessage();
                        if (receivedMessage != null) {
                            if (gm != null) {
                                ClientMenu.gm.setBackgroundColorByStringChar2(receivedMessage);
                                ClientMenu.gm.revalidate();
                                ClientMenu.gm.repaint();

                            }
                        }
                            if(receivedMessage.equalsIgnoreCase("start")){
                                System.out.println("\nStaart daragsan genee\n");
//                                cm.callingPPG();
                                cm.displayGameFrame();
                            }
                            //endes ehled uur2 toglomnuda bichel yvnada
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void sendMessage(String message) {
    System.out.println("Sending message: " + message);
    output.println(message);
        if (gm != null) {
            ClientMenu.gm.setBackgroundColorByStringChar1(message);
            ClientMenu.gm.revalidate();
            ClientMenu.gm.repaint();
        }
}


    public String receiveMessage() throws IOException {
        String str=this.input.readLine().trim();
        
        System.out.println("Server pressed: "+str);
        if(str.equalsIgnoreCase("start")){
            System.out.println("\nStaaaaaaaaaaaarrtttttttt\n");
        }
        return str;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


