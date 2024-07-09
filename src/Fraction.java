
// Generic class to handle Fractions as objects

class Fraction {
    long numerator;
    long denominator;

    public Fraction() {
        this.numerator = 0;
        this.denominator = 1;
    }
    
    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction subtract(Fraction other) {
        var newNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);
        var newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator).simplify();
    }
    
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    
    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    private Fraction simplify() {
        long commonDivisor = gcd(numerator, denominator);
        numerator = numerator / commonDivisor;
        denominator = denominator / commonDivisor;
        return this;
    }

    public Fraction reciprocal() {
        return new Fraction(denominator, numerator);
    }
    
    public Fraction multiply(Fraction other) {
    	var newNumerator = this.numerator * other.numerator;
    	var newDenominator = this.denominator*other.denominator;
    	return new Fraction(newNumerator, newDenominator).simplify();
    }
    
    public Fraction divide(Fraction other) {
    	var newNumerator = this.numerator * other.denominator;
    	var newDenominator = this.denominator * other.numerator;
    	return new Fraction(newNumerator, newDenominator).simplify();
    }
    
    public Fraction add(Fraction other) {
    	var newNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);
    	var newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator).simplify();
    }
    
    public Fraction substract(Fraction other) {
    	var newNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);
    	var newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator).simplify();
    }
    
    public Fraction duplicate() {
    	return new Fraction(this.numerator, this.denominator);
    }    
    
    public Boolean greaterThan(Fraction other){
    	var a = this.numerator;
    	var b = this.denominator;
    	var c = other.numerator;
    	var d = other.denominator;
      
        // Compute ad-bc
    	var Y = a * d - b * c;
        return Y > 0;
    }
    
    public Fraction getAbsolute() {
    	if(this.denominator < 0) 
    		return new Fraction(this.numerator, -1 * this.denominator); 
    	
    	return this;
    }
  
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
