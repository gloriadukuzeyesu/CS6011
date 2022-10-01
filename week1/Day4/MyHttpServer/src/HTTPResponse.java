import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPResponse {

    public static Socket ClientSocket;

    public  static  void main (String[] args) {

//        Socket ClientSocket = null;
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8080);

        } catch (IOException e){
            System.out.println("Failed to connect to the port");
        }


        while (true)
        {

//            HTTPRequest httpRequest = new HTTPRequest();
            String FileName = HTTPRequest.filename; // access static variable via classname


            try {
                ClientSocket = serverSocket.accept();
                if ( FileName.equals("/")){
                    FileName = "index.html";
                }
                FileName = "/resources" + FileName;

                //check if the file exist
                File file = new File(FileName);
                String results;
                if (file.exists()) {
                    results = " 200 sucess";
                } else {
                    results = " 404 not found";
                }

                // send the header

                outputStream = ClientSocket.getOutputStream();
                printWriter = new PrintWriter(outputStream);


                printWriter.println("HTTP/1.1" + results);
                String extension = FileName.substring(FileName.lastIndexOf('.') + 1); //check string extension and put it on content type. It can be .html or .css
                printWriter.println("Content-Type: text/" + extension);
                printWriter.println("Content-Length: " + file.length());
                printWriter.println("size: " + file.length());
                printWriter.println("\n"); // ends the response header with a blank line

                printWriter.flush(); // write the content of buffer to the client and empty the buffer to store further data

                //send the whole file at once using tranferTo();
                try {
                    fileInputStream = new FileInputStream(file);
                    fileInputStream.transferTo(outputStream);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                printWriter.flush();

                ClientSocket.close();
                assert fileInputStream != null;
                fileInputStream.close();
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }



}
