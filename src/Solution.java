import java.util.Arrays;
import java.util.ArrayList;

// I'm deviating from variable naming conventions to remain 
// close to mathematical representations
// Following this lecture: https://math.dartmouth.edu/archive/m20x06/public_html/Lecture14.pdf

public class Solution {
	
    public static long[] solution(long[][] m) {
    	if(m.length != m[0].length) throw new IllegalArgumentException();
    	
    	int numStates = m.length; // total states
    	
    	var QnR = new ArrayList<long[]>();
    	var QnRDenominator = new ArrayList<Long>();
    	
    	long sumRow = 0; 
    	
    	//Figure out which are absorbing states
    	for (int i = 0; i < m.length; i++) {
    		sumRow = 0;
    		for(int j = 0; j < m[i].length; j++) 
    			sumRow += m[i][j];
    		
	        if(sumRow != 0 && !(sumRow==1 && m[i][i]==1)) { 
	        	QnR.add(m[i]); //collect transient states
	        	QnRDenominator.add(sumRow);
	        }
    	}
    	int t = QnR.size(); // number of transient states
    	int r = numStates - t;  // number of absorbing states
    	
    	long[][] Q = new long[t][]; 
    	long[][] R = new long[t][]; 
    	long[] denominators = longToArray(QnRDenominator);
    	
    	//To create Q we need an t x t sized matrix
    	for(int i = 0; i < t; i++) {
    		Q[i] = Arrays.copyOf(QnR.get(i),t);
    		R[i] = Arrays.copyOfRange(QnR.get(i), t, numStates);
    	}
    	
    	//Convert simple matrices to fraction matrices
    	Matrix Qf = new Matrix(Q,denominators);
    	Matrix Rf = new Matrix(R,denominators);
    	
    	//First we perform I-Q
    	//Subtraction is irrelevant to the denominators
    	Matrix Ni = Matrix.createIdentityMatrix(t).substract(Qf);
    	//Now we reverse to get N
    	Matrix N = Matrix.inverse(Ni);
    	
    	//Last step is to multiple N by R
    	//We are always starting from place 0 so we only need the probabilities from that row
    	//can be skipped if the whole matrix result is needed
    	Matrix Nr = Matrix.copyRows(N, 0, 1);
    	
    	Matrix B = Nr.multiply(Rf);
    	Fraction[] Bn = B.matrix[0];
    	changeToCommonDenominator(Bn);
    	
    	//Finally to get a List of the numerators 
    	long[] solution = new long[r + 1];
    	for (int i = 0; i < r; i++) 
    		solution[i] = Bn[i].numerator;
    	solution[r] = Bn[0].denominator;
    	
//    	System.out.println("Solution " + solution.length);
//    	for (long x : solution)
//            System.out.print(x + " ");
//    	
    	return solution;
    }

    static Fraction[][] createIdentityMatrix(int n){
    	Fraction[][] a = new Fraction[n][n];
    	
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j< n; j++)
    			a[i][j] = new Fraction( i == j ? 1 : 0 ,1);
    	
    	return a;
    }
    
    public static long findCommonDenominator(Fraction[] fractions) {
        long commonDenominator = 1;

        for (Fraction fraction : fractions) {
            var denominator = fraction.denominator;
            commonDenominator = Fraction.lcm(commonDenominator, denominator);
        }

        return commonDenominator;
    }

    public static void changeToCommonDenominator(Fraction[] fractions) {
    	var commonDenominator = findCommonDenominator(fractions);
    	
        for (Fraction fraction : fractions) {
            var numerator = fraction.numerator;
            var denominator = fraction.denominator;

            var factor = commonDenominator / denominator;
            fraction.numerator = numerator * factor;
            fraction.denominator = commonDenominator;
        }
    }

    public static long[] longToArray(ArrayList<Long> arrList) {
    	final long[] arr = new long[arrList.size()];
        int index = 0;
        for (final Long value : arrList) {
           arr[index++] = value;
        }
        return arr;
    }
    
}

