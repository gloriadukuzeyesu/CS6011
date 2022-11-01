import javax.sound.sampled.AudioFormat;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

public class MyRunnable implements Runnable {
    private Socket clientSocket = null;

    public String roomName_ = null;

    MyRunnable(Socket Client) {
        this.clientSocket = Client;
    }

    @Override
    public void run()  {

        //handle the request header
        try {
            HTTPRequest request = new HTTPRequest(clientSocket);
            String filename = request.getTheFileName();
            HashMap<String, String> header = request.getTheBodyOfTheHeader();

            // send the response,
            HTTPResponse response = new HTTPResponse(clientSocket, filename, header);
            response.SendResponseHeader();
//            response.SendResponseBody();

            if (header.containsKey("Sec-WebSocket-Key")){
                WsHandler wsRequest = new WsHandler(clientSocket);

                while (true){
                    wsRequest.readWsRequest();
                    wsRequest.respondWsRequest();

                }
            } else {
                clientSocket.close();
            }

        } catch (IOException | NoSuchAlgorithmException | InterruptedException e) {
            throw new RuntimeException(e);


        }
    }
}