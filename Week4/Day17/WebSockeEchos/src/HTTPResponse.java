import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

public class HTTPResponse {

    public FileInputStream fileInputStream;
    public OutputStream outputStream;
    public PrintWriter printWriter;
    public Socket ClientSocket;
    public String filename;
    public File file;
    public HashMap<String, String> map;
    public String SecWebSocketAccept_;

    // constructor
    HTTPResponse(Socket Client, String fileName, HashMap<String, String> hashMap) throws IOException {
        this.ClientSocket = Client;
        this.filename = fileName;
        this.map = hashMap;
    }

    // responding to the HTTP request
    public void SendResponseHeader() throws IOException, NoSuchAlgorithmException, InterruptedException {
//        HTTPRequest httpRequest = new HTTPRequest(ClientSocket); //
//        String fileName = httpRequest.getTheFileName();

        if (filename.equals("/")) {
            filename = "/index.html";
        }

        // go into the resources folder and grab html and css at the same time
        filename = "resources" + filename;
        System.out.println("The filename is: " + filename);

        //check if the file exist
        file = new File(filename);
        String results;
        if (file.exists()) {
            results = " 200 sucess";
        } else {
            results = " 404 not found";
        }

        System.out.println("The file is: " + file);

        // send the header
        try {
            outputStream = ClientSocket.getOutputStream();
            System.out.println("out: " + outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printWriter = new PrintWriter(outputStream);

        if (map.containsKey("Sec-WebSocket-Key")) {
            // send the handshake if it is a websocket. Otherwise, send the HTTP response
            SecWebSocketAccept();
            handshakeResponse();

        }else {
            printWriter.println("HTTP/1.1" + results);
            String extension = filename.substring(filename.lastIndexOf('.') + 1); //check string extension and put it on content type. It can be .html or .css
            printWriter.println("Content-Type: text/" + extension);
            printWriter.println("Content-Length: " + file.length());
            printWriter.println("\n"); // ends the response header with a blank line

            printWriter.flush(); // write the content of buffer to the client and empty the buffer to store further data

            SendResponseBody();
        }
    }

    public void SendResponseBody() throws IOException, InterruptedException {
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream = new FileInputStream(file);
            fileInputStream.transferTo(outputStream);
        } catch (IOException ex) {
            System.out.println("File not found");
        }
        printWriter.flush();

       /* // code to test that the server is serving multiple clients. Open the load httpserver in multiple browser. The client will load simultaneously, one after another
        fileInputStream = new FileInputStream(file);
        for (int i = 0; i < file.length(); i++) {
            printWriter.write(fileInputStream.read());
            printWriter.flush();
            Thread.sleep(100);
        }*/
    }

    // responding to the websocket request

    public void SecWebSocketAccept () throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // check if the hashmap contains thee Sec-Web-Socket-Key and connection upgrade to decide that it is  Websocket request
        String SecWebSocketKey = " ";
        String Connection = " ";
        if (map.containsKey("Sec-WebSocket-Key") || map.containsKey("Connection")) {
            System.out.println("Found the key and the Connection");
            SecWebSocketKey = map.get("Sec-WebSocket-Key");
            Connection = map.get("Connection");
        }
        System.out.println(" The SecWebSocketKey: " + SecWebSocketKey + " and the connection is: " + Connection);

        // concantinate the key with the magic key
        String magicKey = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        SecWebSocketKey = SecWebSocketKey + magicKey;
        System.out.println("SecWebSocketKey: " + SecWebSocketKey);

        //hashing
        MessageDigest message_digest = MessageDigest.getInstance("SHA-1");
//        message_digest.update(SecWebSocketKey.getBytes("UTF-8"));
        byte[] hashed = message_digest.digest(SecWebSocketKey.getBytes("UTF-8"));

        SecWebSocketAccept_ = Base64.getEncoder().encodeToString(hashed);  //Base64 class will encode the output of the Message Digest into a string
        System.out.println("the encrypted is "  + SecWebSocketAccept_);
    }

    public void handshakeResponse() {
//        header line ends with \r\n and put an extra \r\n after the last one to indicate the end of the header
        printWriter.print("HTTP/1.1 101 Switching Protocols\r\n");
        printWriter.print("Upgrade: websocket\r\n");
        printWriter.print("Connection: Upgrade\r\n");
        printWriter.print("Sec-WebSocket-Accept:" + SecWebSocketAccept_ + "\r\n\r\n");
        printWriter.flush();
    }

    public void sendMessage () throws IOException {
        // send the websocket message. Binary header
        // 1st byte is fin res res res opcode
        // 1000 0001 send opcode of 1 cz it is a text message


        // send the 2nd byte . the length of the message
        // if mask is 0 is not masked, and that's want we want.

        // TODO if the opcode is is one send the txt message



    }



}

