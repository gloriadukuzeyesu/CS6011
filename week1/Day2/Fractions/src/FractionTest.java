import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FractionTest {

    @Test
    public void runAllTests() {
        //Test for toString()
        Fraction f1 = new Fraction( 3, -9);
        String myString = f1.toString();
        System.out.println( f1+ " changed to string is: " + myString);
        Assertions.assertEquals(myString, "-1/3");

        // Test for toDouble ()
        Fraction f2 = new Fraction( 12, 8);
        double myDouble = f2.toDouble();
        Assertions.assertEquals(myDouble, 1.50);
        System.out.println(f2 + " changed to double is: " + myDouble);


        // Test for getNumerator() and getDenominator()
        System.out.println("The denominator of " + f1 + " is " + f1.getDenominator());
        System.out.println("The numerator of "+ f1 + " is " + f1.getNumerator());

        // Test for Fraction plus
        Fraction f3 = f1.plus(f2);
        Assertions.assertEquals("7/6", f3.toString());
        System.out.println("The sum of " + f1 + " and " + f2 + " is " + f3 );

        // Test for minus
        Fraction f4 = f1.minus(f2);
        Assertions.assertEquals("-11/6", f4.toString());
        System.out.println("The result from  subtracting " + f1 + " and " + f2 + " is " + f4);

        // Test for times
        Fraction f5;
        f5 = f3.times(f1);
        Assertions.assertEquals("-7/18" , f5.toString());
        System.out.println("The result from  multiplying "+ f3 + " with " + f1 + " is " + f5);

        //Test for dividedBy()
        Fraction f6 = f2.dividedBy(f1);
        Assertions.assertEquals("-9/2", f6.toString());
        System.out.println("The result from  division of "+ f2 + " and " + f1 + " is " + f6);


        //Test for reciprocal()
        Fraction f7 = f2.reciprocal();
        Assertions.assertEquals("2/3", f7.toString());
        System.out.println("The reciprocal of " + f2+ " is " + f7);


    }

}