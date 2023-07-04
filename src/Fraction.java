
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
        return simplify(new Fraction(newNumerator, newDenominator));
    }
    
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    
    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    private static Fraction simplify(Fraction fraction) {
        long commonDivisor = gcd(fraction.numerator, fraction.denominator);
        long simplifiedNumerator = fraction.numerator / commonDivisor;
        long simplifiedDenominator = fraction.denominator / commonDivisor;
        
        if(simplifiedNumerator < 0 && simplifiedDenominator < 0) {
        	simplifiedNumerator *= -1;
        	simplifiedDenominator *= -1;
        }
        return new Fraction(simplifiedNumerator, simplifiedDenominator);
    }

    public Fraction reciprocal() {
        return new Fraction(denominator, numerator);
    }
    
    public Fraction multiply(Fraction other) {
    	var newNumerator = this.numerator * other.numerator;
    	var newDenominator = this.denominator*other.denominator;
    	return simplify(new Fraction(newNumerator, newDenominator));
    }
    
    public Fraction divide(Fraction other) {
    	var newNumerator = this.numerator * other.denominator;
    	var newDenominator = this.denominator * other.numerator;
    	return simplify(new Fraction(newNumerator, newDenominator));
    }
    
    public Fraction add(Fraction other) {
    	var newNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);
    	var newDenominator = this.denominator * other.denominator;
        return simplify(new Fraction(newNumerator, newDenominator));
    }
    
    public Fraction substract(Fraction other) {
    	var newNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);
    	var newDenominator = this.denominator * other.denominator;
        return simplify(new Fraction(newNumerator, newDenominator));
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
    		return new Fraction(this.numerator, -1*this.denominator); 
    	
    	return this;
    }
  
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
