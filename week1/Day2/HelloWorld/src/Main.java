import java.util.Arrays;
import java.util.Scanner; // import the scanner class

public class Main {
    public static void main(String[] args) {
        //creating an array of 10 integers
        int arrayOfNumbers[] = {10, 20, 30, 40, 50, 55, 60, 65, 70, 80};
        arrayOfNumbers[4] = 100;
        // find the sum of each element in the array
        int sum = 0;
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            sum = sum + arrayOfNumbers[i];
        }
        System.out.println("The sum is: " + sum);

        //prompt the user to enter their name
        Scanner  scannerObj = new Scanner(System.in); // creating a scanner object
        System.out.println("Enter your name");
        System.out.println("Enter your age");


        String userName = scannerObj.nextLine(); // variable to store the name
        int userAge = scannerObj.nextInt(); // variable to store the age


        // creating a bool to see if user is allowed to vote
        int OldenoughToVote = 18;


        if( userAge > OldenoughToVote) {
            System.out.println(" you are allowed to vote");
        }
        else{
            System.out.println(" you are NOT allowed to vote");
        }
        // checking which generation
        if( userAge < 1 ){
            System.out.println("Enter valid age");
            System.exit(0);
        } else if( userAge<10){
            System.out.println(" you are from Generation Alpha ");
        } else if ( userAge >= 10 && userAge <=25){
            System.out.println(" you are From iGeneration");
        } else if ( userAge>= 26 && userAge <= 41 ) {
            System.out.println(" you are a Millenial");
        } else if ( userAge > 41 && userAge < 58 ) {
            System.out.println(" you are GenX");
        } else if ( userAge >= 58 && userAge < 77) {
            System.out.println(" you are from baby Bommer generation");
        }
        else {
            System.out.println(" you are From greatest Generation");
        }

    }
}