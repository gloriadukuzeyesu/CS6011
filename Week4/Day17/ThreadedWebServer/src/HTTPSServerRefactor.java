import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class HTTPSServerRefactor {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            System.out.println("Failed to connect to the port");
        }

        while (true) {
            try {
                // 1. create clientsocket
                assert serverSocket != null;
                Socket clientSocket = serverSocket.accept();
                MyRunnable runnable = new MyRunnable(clientSocket);
                Thread myThread = new Thread(runnable);
                myThread.start();

            } catch (IOException e) {
                System.out.println("Client failed to connect");

            }

        }
    }
}