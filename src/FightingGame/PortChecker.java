package FightingGame;
import java.io.IOException;
import java.net.ServerSocket;

public class PortChecker {

    public static boolean isPortAvailable(int port) {
        try (ServerSocket ignored = new ServerSocket(port)) {
            return true; // Port is available
        } catch (IOException e) {
            return false; // Port is already in use
        }
    }

    public static void main(String[] args) {
        int portToCheck = 12345;
        if (isPortAvailable(portToCheck)) {
            System.out.println("Port " + portToCheck + " is available.");
            startServer(portToCheck);
        } else {
            System.out.println("Port " + portToCheck + " is already in use. Not starting the server.");
        }
    }

    private static void startServer(int port) {
        System.out.println("Starting server on port " + port);
        // Add your server startup logic here
    }
}
