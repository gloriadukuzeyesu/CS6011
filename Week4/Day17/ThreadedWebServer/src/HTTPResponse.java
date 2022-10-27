import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPResponse {

    public FileInputStream fileInputStream;
    public OutputStream outputStream;
    public PrintWriter printWriter;
    public Socket ClientSocket;
    public String filename;
    public File file;


    // constructor TODO fix the error
    HTTPResponse(Socket Client, String fileName) throws IOException {
        this.ClientSocket = Client;
        this.filename = fileName;
    }

    public void SendResponseHeader() {
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

        printWriter.println("HTTP/1.1" + results);
        String extension = filename.substring(filename.lastIndexOf('.') + 1); //check string extension and put it on content type. It can be .html or .css
        printWriter.println("Content-Type: text/" + extension);
        printWriter.println("Content-Length: " + file.length());
//        printWriter.println("size: " + file.length());
        printWriter.println("\n"); // ends the response header with a blank line

        printWriter.flush(); // write the content of buffer to the client and empty the buffer to store further data

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
/*
        // code to test that the server is serving multiple clients. Open the load httpserver in multiple browser. The client will load simultaneously, one after another
        fileInputStream = new FileInputStream(file);
        for (int i = 0; i < file.length(); i++) {
            printWriter.write(fileInputStream.read());
            printWriter.flush();
            Thread.sleep(10);*/
        }
    }

