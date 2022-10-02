import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;

public class HTTPRequest {

    public static String filename;

    public static void main (String[] args) {

        InputStream inputStream = null;
        Socket ClientSocket = null;

        try {

            ClientSocket = new Socket("localhost", 8080);

            System.out.println("successfully connected");


            while (true) {

//                ClientSocket = new Socket("localhost", 8080);

                inputStream = ClientSocket.getInputStream();

                Scanner scan = new Scanner(inputStream);

                System.out.println("Scanning in process ");

                String inputLine = "";

                if ( scan.hasNextLine()) {
                     inputLine = scan.nextLine(); // scan the first line and extract the filename from it
                }
                else{
                    System.out.println("No line found");
                }


//                String filename = "";
                String[] My_split = inputLine.split(" "); // split at the space. to get three pieces (GET, PATH, and, Protocol). Path is filename
                filename = My_split[1];

//                filename_ = filename;


                inputLine = scan.nextLine(); // scan the second line and store it into hash map

                HashMap<String, String> requestPairs = new HashMap<>();

                while (!inputLine.equals("")) {

                    String[] splittedLine = inputLine.split(":");    // split the line. split at : and create an array of
                    requestPairs.put(splittedLine[0], splittedLine[1]);    // store the value into hashmap
                    inputLine = scan.nextLine();               // read next line
                    System.out.println("The inputLine is: " + inputLine);
                }
            }

        } catch (IOException e) {
            System.out.println("failed to connect to the client");
        } finally {
            try{
                if(ClientSocket != null)
                    ClientSocket.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}