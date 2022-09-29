import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner; // import the scanner class

public class RainData {

    String fileName = "/Users/gloriadukuzeyesu/MSD/CS6011/week1/Day2/Rainfall/Macon.txt";
    ArrayList<String> months;
    ArrayList<Integer> years;
    ArrayList<Double> rainInches;
    ArrayList<Double> monthAve = new ArrayList<>();
    // create an array  12 months in order. Jan, feb,march,april,...
    String MyMonthsInOrder[] = new String[]{"January", "February", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};


    //constructor
    public RainData() {
    }

    //method to read in file
    public void ReadInFIle() throws IOException {
        //open the file
        File file = new File(this.fileName);
        Scanner scan = new Scanner(file);

        ArrayList<String> months = new ArrayList<String>();
        ArrayList<Integer> years = new ArrayList<Integer>();
        ArrayList<Double> rainInches = new ArrayList<Double>();
        String cityName = scan.next();
        while (scan.hasNext()) {
            months.add(scan.next());
            years.add(scan.nextInt());
            rainInches.add(scan.nextDouble());
        }


        double TotalRain = 0.00;
        int count = 0;
        // create a helper to help set the rounding to 2 decimal places
//        BigDecimal numb = new BigDecimal(TotalRain);

        for (String s : MyMonthsInOrder) {
            for (int k = 0; k < months.size(); k++) {
                if (months.get(k).equals(s)) {
                    TotalRain = TotalRain + rainInches.get(k);
                    count++;
                }
            }
            monthAve.add(TotalRain / count);
        }
    }

    //function to write into a file
    public void WriteToFile() throws IOException {
        FileWriter myWriter = new FileWriter("rainfall_results.txt");
        double averageRainFall = 0.0;

        for (int i = 0; i < MyMonthsInOrder.length; i++) {

            myWriter.write ( "The overall average rainfall amount for " + MyMonthsInOrder[i] + " is "+ monthAve.get(i) + "\n");
        }


        System.out.println("Successfully written");
        myWriter.close();
    }

    // function to find the average of each month


}



