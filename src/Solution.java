import java.util.Arrays;
import java.util.ArrayList;

/**
 * I'm deviating from variable naming conventions to remain 
 * close to mathematical representations
 * Following this lecture: https://math.dartmouth.edu/archive/m20x06/public_html/Lecture14.pdf
 *
 * Overview (by chatgpt confirmed by me):
 * 1. Input Validation:
 *    - The code first checks if the input matrix `m` is square (i.e., the number of rows equals the number of columns).
 *      If not, it throws an `IllegalArgumentException`.
 *
 * 2. Identifying Transient and Absorbing States:
 *    - The code iterates through each row of the matrix `m` to determine if it is a transient or absorbing state.
 *      A transient state is identified if the sum of the row is not zero and it�s not an absorbing state
 *      (i.e., it doesn't have a single 1 on the diagonal with all other entries zero).
 *    - Transient states are collected in `QnR` and their corresponding row sums in `QnRDenominator`.
 *
 * 3. Creating Q and R Matrices:
 *    - The transient states are used to form the `Q` and `R` matrices.
 *      `Q` contains the probabilities of moving from one transient state to another transient state.
 *      `R` contains the probabilities of moving from transient states to absorbing states.
 *    - The denominators of the fractions in these matrices are stored in `denominators`.
 *
 * 4. Matrix Operations:
 *    - The code then constructs matrix operations involving `Q`:
 *      - It creates the identity matrix of size `t x t` (where `t` is the number of transient states).
 *      - It computes `I - Q`, where `I` is the identity matrix.
 *      - It calculates the inverse of `I - Q` to obtain the fundamental matrix `N`.
 *      - Finally, it multiplies `N` by `R` to get the matrix `B`.
 *
 * 5. Finding the Solution:
 *    - The solution vector consists of the probabilities of being absorbed into each absorbing state starting from the initial state.
 *    - It converts these probabilities into fractions with a common denominator.
 *
 * 6. Returning the Result:
 *    - The numerators of the resulting fractions, along with the common denominator, are assembled into the solution array and returned.
 *
 */

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
    	
    	System.out.println("Solution " + solution.length);
    	for (long x : solution)
            System.out.print(x + " ");
    	
    	System.out.println("");
    	
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

