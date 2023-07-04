import java.util.Arrays;

class Matrix {
	Fraction[][] matrix;
	
	public Matrix(Fraction[][] m) { matrix = m; }
	
	public Matrix(int width, int height) {
    	matrix = new Fraction[height][width];
		for(int i = 0; i < height; i++)
    		for(int j = 0; j < width; j++)
    			matrix[i][j] = new Fraction();
    }
	
	public Matrix(long[][] matrix, long[] denominators){
    	Fraction[][] toReturn = new Fraction[matrix.length][matrix[0].length];
    	for(int i = 0; i < matrix.length; i++) 
    		for(int j = 0; j < matrix[0].length; j++) 
    			toReturn[i][j] = new Fraction(matrix[i][j], denominators[i]);

    	this.matrix = toReturn;
    }
	
	static Matrix createIdentityMatrix(int n){
    	Fraction[][] a = new Fraction[n][n];
    	
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j< n; j++)
    			a[i][j] = new Fraction( i == j ? 1 : 0 ,1);
    	
    	return new Matrix(a);
    }
	
	public Matrix substract (Matrix other){
		Fraction[][] a = matrix, b = other.matrix;
		
    	assert (a.length == b.length);
    	assert (a[0].length == b[0].length);
    	
    	Fraction[][] c = new Fraction[a.length][a[0].length];
    	
    	for (int i = 0; i < a.length; i++)
    		for (int j = 0; j< a[0].length; j++)
    			c[i][j] = a[i][j].subtract(b[i][j]);    	
    	
    	return new Matrix(c);
    }
	
	public Matrix multiply(Matrix other) {
        int numRows1 = matrix.length;
        int numCols1 = matrix[0].length;
        int numRows2 = other.matrix.length;
        int numCols2 = other.matrix[0].length;

        if (numCols1 != numRows2) 
        	throw new IllegalArgumentException("Cannot multiply matrices. Incompatible dimensions.");

        Fraction[][] result = new Fraction[numRows1][numCols2];

        for (int i = 0; i < numRows1; i++) {
            for (int j = 0; j < numCols2; j++) {
                Fraction sum = new Fraction();
                for (int k = 0; k < numCols1; k++) {
                    Fraction product = matrix[i][k].multiply(other.matrix[k][j]);
                    sum = sum.add(product);
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }
	
	public static Matrix copyRows(Matrix toCopy, int start, int finish) {
		assert(start >= 0 && finish <= toCopy.matrix.length);

		Fraction[][] a = toCopy.matrix;
		Fraction[][] b = new Fraction[finish-start][a[0].length];
		
		for(int i = start; i < finish ; i++) b[i] = a[i];
		
		return new Matrix(b);		
	}
	
	//This is a modified solution from  https://www.sanfoundry.com/java-program-find-inverse-matrix/
	public static Matrix inverse(Matrix m) {
		if(! m.isSquare()) throw new IllegalArgumentException();
		
		Fraction[][] a = m.matrix;
        int n = a.length;
        Fraction x[][] = new Matrix(n,n).matrix;
        Fraction b[][] = new Matrix(n,n).matrix;
        Fraction tmp;
        int index[] = new int[n];
        for (int i=0; i<n; ++i) b[i][i] = new Fraction(1,1);
 
        // Transform the matrix into an upper triangle
        a = m.duplicate().matrix;
        gaussian(a, index);
 
        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k) {
                    tmp = a[index[j]][i].multiply(b[index[i]][k]);
                	b[index[j]][k] = b[index[j]][k].substract(tmp);
                }
        // Perform backward substitutions
        for (int i=0; i<n; ++i) {
            x[n-1][i] = b[index[n-1]][i].divide(a[index[n-1]][n-1]);
            for (int j=n-2; j>=0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k){
                	tmp = a[index[j]][k].multiply(x[k][i]);
                    x[j][i] = x[j][i].substract(tmp);
                }
                x[j][i] = x[j][i].divide(a[index[j]][j]);
            }
        }
        return new Matrix(x);
    }
 
	// Method to carry out the partial-pivoting Gaussian
	// elimination.  Here index[] stores pivoting order. 
    private static Fraction[][] gaussian(Fraction a[][], int index[]) {
        int n = index.length;
        Fraction c[] = new Fraction[n];
        Fraction zero = new Fraction(0,1);
        
        // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            Fraction c1 = zero;
            for (int j=0; j < n; ++j) {
                Fraction c0 = a[i][j].getAbsolute();
                if (c0.greaterThan(c1)) c1 = c0;
            }
            c[i] = c1;
        }
 
        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j){
            Fraction pi1 = zero;
            for (int i=j; i < n; ++i){
                Fraction pi0 = a[index[i]][j].getAbsolute();
                pi0 = pi0.divide(c[index[i]]);
                if (pi0.greaterThan(pi1)) {
                    pi1 = pi0;
                    k = i;
                }
            }
 
            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) {
                Fraction pj = a[index[i]][j].divide(a[index[j]][j]);
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
                // Modify other elements accordingly
                // substract the multiplied element 
                for (int l = j+1; l<n; ++l) {
                	Fraction tmp = 	pj.multiply(a[index[j]][l]);
                    a[index[i]][l] =  a[index[i]][l].substract(tmp);
                }
            }
        }
        return a;
    }
	
    public Matrix duplicate() {
    	Fraction[][] m = new Fraction[matrix.length][matrix[0].length];
    	for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                m[i][j] = matrix[i][j].duplicate();
            }
        }
    	return new Matrix(m);
    }
    
    public Boolean isSquare() { return matrix.length == matrix[0].length; }
    
	public void print() {
	        for (int i = 0; i < matrix.length; i++) {
	            for (int j = 0; j < matrix[i].length; j++) {
	                System.out.print(matrix[i][j].numerator + "/" + matrix[i][j].denominator + " ");
	            }
	            System.out.println();
	        }
	        System.out.println();
	 }
}

