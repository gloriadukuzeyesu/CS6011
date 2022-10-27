import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;

// 1. filename
//2. read the header

public class HTTPRequest {

    public String filename;
    public InputStream inputStream;
    public  Socket ClientSocket;
    public String inputLine;
    public Scanner scan;

    //constuctor
    HTTPRequest(Socket client) {
        ClientSocket = client;
    }


    public String getTheFileName () throws IOException {
            inputStream = ClientSocket.getInputStream();


        scan = new Scanner(inputStream);
        inputLine = scan.nextLine();
        System.out.println(inputLine);

        // find the filename
        String[] My_split = inputLine.split(" "); // split at the space. to get three pieces (GET, PATH, and, Protocol). Path is filename
        filename = My_split[1];
        return filename;
    }


    //function to get the elements
    public HashMap<String, String> getTheBodyOfTheHeader () {
        inputLine = scan.nextLine();
        HashMap<String, String> requestPairs = new HashMap<>(); // decalre the hashp
        while (!inputLine.equals("")) {
            // split the line. split at : and create an array of
            String[] splittedLine = inputLine.split(":");
            requestPairs.put(splittedLine[0], splittedLine[1]); // store the value into hashmap
            // read next line
            inputLine = scan.nextLine();
            System.out.println("The inputLine is: " + inputLine);
        }
        return requestPairs;
    }


}







//    public static void main (String[] args) {
//
//        try {
//
//
//            System.out.println("successfully connected");
//
//            while (true) {
//
////
////                if ( scan.hasNextLine()) {
////                     inputLine = scan.nextLine(); // scan the first line and extract the filename from it
////                }
////                else{
////                    System.out.println("No line found");
////                }
//
//
////                String filename = "";
////                String[] My_split = inputLine.split(" "); // split at the space. to get three pieces (GET, PATH, and, Protocol). Path is filename
////                System.out.println("My input :"+My_split);
////                filename = My_split[1];
//
////                filename_ = filename;
//
//
//                inputLine = scan.nextLine(); // scan the second line and store it into hash map
//
//                HashMap<String, String> requestPairs = new HashMap<>();
//
//                while (!inputLine.equals("")) {
//
//                    String[] splittedLine = inputLine.split(":");    // split the line. split at : and create an array of
//                    requestPairs.put(splittedLine[0], splittedLine[1]);    // store the value into hashmap
//                    inputLine = scan.nextLine();               // read next line
//                    System.out.println("The inputLine is: " + inputLine);
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println("failed to connect to the client");
//        } finally {
//            try{
//                if(ClientSocket != null)
//                    ClientSocket.close();
//                if (inputStream != null)
//                    inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}