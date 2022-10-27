import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class MyRunnable implements Runnable {
    private Socket clientSocket = null;

    MyRunnable(Socket Client) {
        this.clientSocket = Client;
    }

    @Override
    public void run() {

        //handle the request header
        try {
            HTTPRequest request = new HTTPRequest(clientSocket);
            String filename = request.getTheFileName();
            HashMap<String, String> header = request.getTheBodyOfTheHeader();

            // send the response
            HTTPResponse response = new HTTPResponse(clientSocket, filename);
//       System.out.println( " Test " + response.filename + " <-filename");
            response.SendResponseHeader();
            response.SendResponseBody();
            clientSocket.close();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);


        }
    }
}