import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

class RainDataTest {

    public static void main(String[] args) throws IOException {
        RainData myRainData = new RainData();
        myRainData.ReadInFIle();
        myRainData.WriteToFile();
    }


}