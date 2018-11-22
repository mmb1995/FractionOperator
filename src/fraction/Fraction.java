package fraction;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int num, int denom) {
        if (denom == 0) {
            throw new IllegalArgumentException("The denominator of a fraction can't be zero.");
        }
        this.numerator = num;
        this.denominator = denom;
    }
    
    /**
     * Constructor that takes in a whole number and converts it into a fraction
     * @param num a whole number
     */
    public Fraction(int num) {
        this(num, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (this.denominator == 0) {
            throw new IllegalArgumentException("The denominator can't be zero");
        }
        this.denominator = denominator;
    }
    
    /**
     * Adds two fractions together and returns the result
     * @param other the Fraction to add with this one
     * @return a new Fraction representing the result of adding the two fractions together
     */
    public Fraction add(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        int lcd = lcd(this.getDenominator(), other.getDenominator());
        int myMultiple = lcd / this.getDenominator();
        int otherMultiple = lcd / other.getDenominator();
        Fraction result = new Fraction((this.getNumerator() * myMultiple) + (other.getNumerator() * otherMultiple), lcd);
        result.simplify();
        return result;
    }
    
    /**
     * Performs subtraction on two fractions
     * @param other the fraction to subtract from this one
     * @return a new Fraction representing the result of subtracting the two fractions
     */
    public Fraction subtract(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        int lcd = lcd(this.getDenominator(), other.getDenominator());
        int myMultiple = lcd / this.getDenominator();
        int otherMultiple = lcd / other.getDenominator();
        Fraction result = new Fraction((this.getNumerator() * myMultiple) - (other.getNumerator() * otherMultiple), lcd);
        result.simplify();
        return result;
    }

    /**
     * Multiplies two Fractions and returns the result of the operation
     * 
     * @param other the Fraction to multiply by
     * @return a new Fraction
     */
    public Fraction multiply(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        Fraction result = new Fraction(this.getNumerator() * other.getNumerator(), this.getDenominator() * other.getDenominator());
        result.simplify();
        return result;
    }

    /**
     * Divides two fractions and returns the result of the operation
     * 
     * @param other the Fraction to divide by
     * @return a new Fraction
     */
    public Fraction divide(Fraction other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        Fraction result = new Fraction(this.getNumerator() * other.getDenominator(), this.getDenominator() * other.getNumerator());
        result.simplify();
        return result;
    }

    /**
     * Helper method that calculates the lowest common denominator/multiple of two
     * numbers
     * 
     * @param firstDenom
     * @param secondDenom
     * @return the lcd of the two numbers
     */
    private int lcd(int firstDenom, int secondDenom) {
        int largerDenom = Math.max(firstDenom, secondDenom);
        int smallerDenom = Math.min(firstDenom, secondDenom);
        return ((firstDenom * secondDenom) / gcd(largerDenom, smallerDenom));
    }

    /**
     * Helper function that finds the greatest common denominator between two
     * numbers based off of the algorithm derived by Euclid
     * 
     * @param largerDenom  larger of the two given denominators
     * @param smallerDenom smaller of the two denominators
     * @return the gcd of the two given denominators
     */
    private int gcd(int largerDenom, int smallerDenom) {
        int remainder = smallerDenom;
        while (smallerDenom != 0) {
            remainder = smallerDenom;
            smallerDenom = largerDenom % smallerDenom;
            largerDenom = remainder;
        }
        return largerDenom;
    }

    /**
     * simplifies a fraction to its lowest form by using the gcd of the fractions
     * numerator and denominator
     */
    public void simplify() {
        int gcd;
        if (this.numerator > this.denominator) {
            gcd = gcd(this.numerator, this.denominator);
        } else {
            gcd = gcd(this.denominator, this.numerator);
        }
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }
    
    /**
     * Returns a String representation of the fraction. If the fraction is improper
     * it is converted into a mixed number before being returned.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.numerator > this.denominator) {
            int wholeNumber = this.numerator / this.denominator;
            int remainder = this.numerator % this.denominator;
            
            // Fix weird quirk to prevent two negatives from being present in toString representation
            int denom = wholeNumber > 0 ? this.denominator : this.denominator * -1;
            
            builder.append(wholeNumber);
            builder.append("_");
            builder.append(remainder + "/" + denom);
        } else {
            builder.append(this.numerator);
            builder.append("/");
            builder.append(this.denominator);
        }
        return builder.toString();
    }
    
    /**
     * Overrides equals() to compare two Fraction objects. This is mainly designed to be 
     * used to help with testing
     */
    @Override
    public boolean equals(Object o) {
       
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }
        
        // Make sure this is an instance of Fraction
        if (!(o instanceof Fraction)) {
            return false;
        }
        
        // Typecast o to Fraction
        Fraction f = (Fraction) o;
        
        // Compare the two Fractions internal data
        return Integer.compare(this.numerator, f.numerator) == 0
                && Integer.compare(this.denominator, f.denominator) == 0;
    }

}
