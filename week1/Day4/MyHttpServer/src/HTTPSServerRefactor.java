import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class HTTPSServerRefactor {

    public  static  void main (String[] args) {
        ServerSocket serverSocket = null;
        try {
             serverSocket = new ServerSocket(8080);
        } catch (IOException e){
            System.out.println("Failed to connect to the port");
        }

        while ( true ){
            try {
                // 1. create clientsocket
                assert serverSocket != null;
                Socket clientSocket = serverSocket.accept();
                // 2. Get the request
                HTTPRequest request = new HTTPRequest(clientSocket);
                String filename = request.getTheFileName();
                HashMap<String, String> header = request.getTheBodyOfTheHeader();


                // send the response
                HTTPResponse response = new HTTPResponse(clientSocket, filename);
//                System.out.println( " Test " + response.filename + " <-filename");
                response.SendResponseHeader();
                response.SendResponseBody();

                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Client failed to connect");

            }

        }
    }
}
