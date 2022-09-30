import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Scanner;

public class HTTPServer {
    public static void main(String[] args)  {
        //  1.listen from port 8080
        // 2. set up a connection to a client. create a server socket and bind it to a specific port number
        ServerSocket serverSocket = null;
        try {
                serverSocket = new ServerSocket(8080);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
        }

        try {

            while (true) {

                // try and catch when you can't talk to the client
                String inputLine = null;
                Scanner Scanning = null;
                Socket clientSocket = null;

                // handle an exception where is no  successful connection to client
                try {
                    /* 3. listen  a connection from the client and accept it. this results into a client socket being created for connection */
                    clientSocket = serverSocket.accept();
                    //The accept method waits until a client starts up and requests a connection on the host and port of this server.

                    //4. Read data from the client via an InputStream obtained from the client socket
                    InputStream StreamIn = clientSocket.getInputStream();
                    Scanning = new Scanner(StreamIn);

                    //5. read in the request line by line. The first request line contains some bacis information on the request which includes filename at the second position
                    inputLine = Scanning.nextLine();
                } catch (SocketTimeoutException ste) {
                    System.out.println(" failed to connect to the client"); // failed to create a client socket
                }

                System.out.println(inputLine);

                // 6. slit the fistline and grab the second string.--> the filename
                String filename = "";
                String[] My_split = inputLine.split(" "); // split at the space. to get three pieces (GET, PATH, and, Protocol). Path is filename
                filename = My_split[1];
                System.out.println("file name is: " + filename);


                // 7. read the next line
                inputLine = Scanning.nextLine();
                // 8. set up connection between the client and server while there is no blank line
                HashMap<String, String> requestPairs = new HashMap<>();
                while (!inputLine.equals("")) {
                    // split the line. split at : and create an array of
                    String[] splittedLine = inputLine.split(":");
                    requestPairs.put(splittedLine[0], splittedLine[1]); // store the value into hashmap
                    // read next line
                    inputLine = Scanning.nextLine();
                    System.out.println("The inputLine is: " + inputLine);
                }

                //9. when the file name is "/"  send the index.html
                if (filename.equals("/")) {
                    filename = "index.html";
                }
                // path to file. go to the resources folder  send the files ( .css and .html) stored in resources folder
                filename = "resources/" + filename;

                //10. check if the file exist
                File file = new File(filename);
                String results;
                if (file.exists()) {
                    results = " 200 success";
                } else {
                    results = " 404 not found";
//                file = new File("notFound.html");
                }

                //11. send the response header via outputstream
                OutputStream StreamOut = clientSocket.getOutputStream();
                PrintWriter ServerWriter = new PrintWriter(StreamOut); // output stream to write to the client

                ServerWriter.println("HTTP/1.1" + results);
                String extension = filename.substring(filename.lastIndexOf('.') + 1); //check string extension and put it on content type. It can be .html or .css
                ServerWriter.println("Content-Type: text/" + extension);
                ServerWriter.println("Content-Length: " + file.length());
                ServerWriter.println("size: " + file.length());
                ServerWriter.println("\n"); // ends the response header with a blank line

                System.out.println("the results are : " + results);

                ServerWriter.flush(); // write the content of buffer to the client and empty the buffer to store further data

                //12. send the data from the file at all once using Transfer to(). create a new inputStream and use the outputstream used to send the header information
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.transferTo(StreamOut);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ServerWriter.flush(); //  write the content and clears out the buffer.
                clientSocket.close();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
