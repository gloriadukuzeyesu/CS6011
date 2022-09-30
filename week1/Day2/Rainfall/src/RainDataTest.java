import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class RainDataTest {

    public static void main(String[] args) throws IOException {
        // create a scanner to read in the input from the user
        Scanner InputFilename = new Scanner(System.in);

        //prompt the user to enter the name of the city
        System.out.println("Enter the fileName: Either Atlanta.txt or Macon.txt");
        // grab the input
        String filename = InputFilename.nextLine();

        RainData myRainData = new RainData(filename);
        myRainData.ReadInFIle();
        myRainData.WriteToFile();
    }


}