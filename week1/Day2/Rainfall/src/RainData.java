import org.w3c.dom.ls.LSOutput;

import javax.print.MultiDocPrintJob;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner; // import the scanner class


public class RainData {

    String fileName;
    ArrayList<String> months;
    ArrayList<Integer> years;
    ArrayList<Double> rainInches;
    ArrayList<Double> monthAve = new ArrayList<>();
    // create an array  12 months in order. Jan, feb,march,april,...
    String[] MyMonthsInOrder = new String[]{"January", "February", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};
    //sum of all the rainInches in the city
    static double overallAverage = 0;


    //constructor
    public RainData(String fileName) {
        this.fileName = fileName;
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

        for (String s : MyMonthsInOrder) {
            for (int k = 0; k < months.size(); k++) {
                if (months.get(k).equals(s)) {
                    TotalRain = TotalRain + rainInches.get(k);
                    count++;
                }
            }
            monthAve.add(TotalRain / count);
        }

        //find the average of all rain in each city. by either using rainInches and add all rain together divide by the number of lines in the file
        for ( double d : monthAve) {
            overallAverage += d;
        };
        overallAverage /= 12;

    }


    //function to write into a file
    public void WriteToFile() throws IOException {
        PrintWriter myPrintWriter = new PrintWriter("rainfall_results.txt");
//        myPrintWriter.println( "The overall average rainfall amount for " + fileName + "is " + overallAverage);
        myPrintWriter.printf("The overall average rainfall amount for %s is %.2f.\n", fileName, overallAverage);

        for (int i = 0; i < MyMonthsInOrder.length; i++) {
            myPrintWriter.printf("The overall average rainfall amount for %s is %.2f inches.\n", MyMonthsInOrder[i], monthAve.get(i));
        }

        System.out.println("Successfully written");
        myPrintWriter.close();
    }
}



