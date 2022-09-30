import org.jetbrains.annotations.NotNull;

public class Fraction {
    //declaration of variables
    long numerator_;
    long denominator_;

    //default constructor
    public Fraction(){
        long numerator = 0;
        long denominator = 1;
    }

    // A constructor which sets the value of the fraction to a specific numerator (n) and denominator (d).
    public  Fraction ( long n, long d ) {

        if( d == 0){
            throw new ArithmeticException("denominator can't be 0! ");
        }
        numerator_ = n;
        denominator_ = d;
        reduce();
        // simply the numerator  and denominator right away
         if ( denominator_ < 0 ){
             numerator_ = numerator_ * -1 ;
             denominator_ = denominator_ * -1;
             }

    }

    // create the get for numerator and denominator
    public long getNumerator (){
        return numerator_;
    }

    public long getDenominator() {
        return denominator_;
    }

    // add fractions together
    public Fraction plus( Fraction rhs ){
        long tempNum = ( numerator_ * rhs.getDenominator() + denominator_ * rhs.getNumerator() );
        long tempDen = denominator_ * rhs.getDenominator();
        // instantiate a new fraction to store the sum
        return new Fraction (tempNum, tempDen);
    }

    //subtract fractions together
    public Fraction minus(Fraction rhs){
        long tempNum = ( numerator_ * rhs.getDenominator() - denominator_ * rhs.getNumerator() );
        long tempDen = denominator_ * rhs.getDenominator();
        return new Fraction (tempNum, tempDen);
    }

    //multiply two functions
    public Fraction times(Fraction rhs){
        long numerator = numerator_ * rhs.getNumerator();
        long denominator = denominator_ * rhs.getDenominator();
        return new Fraction(numerator, denominator);
    }

    public  Fraction dividedBy(Fraction rhs){
        long numerator = numerator_ * rhs.getDenominator();
        long denominator = denominator_ * rhs.getNumerator();
        return new Fraction(numerator, denominator);
    }

    public Fraction reciprocal(){
        long newDenominator = numerator_;
        long newNumerator = denominator_;
        return new Fraction(newNumerator, newDenominator);
    }
    // toString() returns a string representation of the fraction
    @Override
    public String toString(){
        return this.numerator_ + "/" + this.denominator_;
    }
    //Returns a (double precision) floating point number that
    //is the approximate value of this fraction, printed as a real number.
    //cast
    public double toDouble(){
        return (double) numerator_ / denominator_;
    }


    private long GCD (long numerator, long denominator) {
        long gcd = numerator;
        long remainder = denominator;
        while( remainder != 0 ) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        return Math.abs(gcd);
    }


    private void reduce(){
        // determine the greatest common divisor
        long gcd = GCD(numerator_, denominator_);
        //divide both numerator and denominator with gcd
        numerator_ = numerator_ / gcd;
        denominator_ = denominator_ / gcd;
    }
}
