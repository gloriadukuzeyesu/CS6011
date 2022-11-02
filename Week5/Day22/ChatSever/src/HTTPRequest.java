import javax.sound.midi.Soundbank;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLOutput;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

// 1. filename
//2. read the header

public class HTTPRequest {

    public String filename;
    public InputStream inputStream;
    public Socket ClientSocket;
    public String inputLine;
    public Scanner scan;
    public HashMap<String, String> map;

    //constructor
    HTTPRequest(Socket client) {
        ClientSocket = client;
    }

    public String getTheFileName() throws IOException {
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
    public HashMap<String, String> getTheBodyOfTheHeader() throws NoSuchAlgorithmException {
        inputLine = scan.nextLine();
        map = new HashMap<>(); // decalre the hashp
        while (!inputLine.equals("")) {
            // split the line. split at : and create an array of
            String[] splittedLine = inputLine.split(": ");
            map.put(splittedLine[0], splittedLine[1]); // store the value into hashmap
            // read next line
            inputLine = scan.nextLine();
        }
        System.out.println("Here is the map " + map);

        return map;
    }
}
