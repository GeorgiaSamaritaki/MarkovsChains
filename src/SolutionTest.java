import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class SolutionTest {
	
	@Test
	void test() {
		assertArrayEquals(Solution.solution(new long[][]{
			{0,1,0,0,0,1},
			{4,0,0,3,2,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0}
		}), new long[] {0, 3, 2, 9, 14});

		assertArrayEquals(Solution.solution(new long[][]{
			{0, 2, 1, 0, 0},
			{0, 0, 0, 3, 4},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		}), new long[] {7, 6, 8, 21});


		assertArrayEquals(Solution.solution(new long[][]{
	        {1, 2, 3, 0, 0, 0},
	        {4, 5, 6, 0, 0, 0},
	        {7, 8, 9, 1, 0, 0},
	        {0, 0, 0, 0, 1, 2},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0}
		}), new long[] {1, 2, 3});
		
//		assertArrayEquals(Solution.solution(new long[][]{
//			{0}
//		}), new long[] {1, 1});

		assertArrayEquals(Solution.solution(new long[][]{
	        {0, 0, 12, 0, 15, 0, 0, 0, 1, 8},
	        {0, 0, 60, 0, 0, 7, 13, 0, 0, 0},
	        {0, 15, 0, 8, 7, 0, 0, 1, 9, 0},
	        {23, 0, 0, 0, 0, 1, 0, 0, 0, 0},
	        {37, 35, 0, 0, 0, 0, 3, 21, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new long[] {1, 2, 3, 4, 5, 15});

		assertArrayEquals(Solution.solution(new long[][]{
	        {0, 7, 0, 17, 0, 1, 0, 5, 0, 2},
	        {0, 0, 29, 0, 28, 0, 3, 0, 16, 0},
	        {0, 3, 0, 0, 0, 1, 0, 0, 0, 0},
	        {48, 0, 3, 0, 0, 0, 17, 0, 0, 0},
	        {0, 6, 0, 0, 0, 1, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new long[] {4, 5, 5, 4, 2, 20});

		assertArrayEquals(Solution.solution(new long[][]{
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new long[] {1, 1, 1, 1, 1, 5});
		
		assertArrayEquals(Solution.solution(new long[][]{
	        {1, 1, 1, 0, 1, 0, 1, 0, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new long[] {2, 1, 1, 1, 1, 6});
		
		assertArrayEquals(Solution.solution(new long[][]{
	        {0, 86, 61, 189, 0, 18, 12, 33, 66, 39},
	        {0, 0, 2, 0, 0, 1, 0, 0, 0, 0},
	        {15, 187, 0, 0, 18, 23, 0, 0, 0, 0},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new long[] {6, 44, 4, 11, 22, 13, 100});
		
		assertArrayEquals(Solution.solution(new long[][]{
	        {0, 0, 0, 0, 3, 5, 0, 0, 0, 2},
	        {0, 0, 4, 0, 0, 0, 1, 0, 0, 0},
	        {0, 0, 0, 4, 4, 0, 0, 0, 1, 1},
	        {13, 0, 0, 0, 0, 0, 2, 0, 0, 0},
	        {0, 1, 8, 7, 0, 0, 0, 1, 3, 0},
	        {1, 7, 0, 0, 0, 0, 0, 2, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new long[] {1, 1, 1, 2, 5});
	}
	
	@Test
    void testInverseSquareMatrix() {
        Fraction[][] matrix = {
            {new Fraction(1, 2), new Fraction(3, 4)},
            {new Fraction(5, 6), new Fraction(7, 8)}
        };

        Fraction[][] expectedInverse = {
            {new Fraction(-14, 3), new Fraction(4, 1)},
            {new Fraction(40, 9), new Fraction(-8, 3)}
        };

        Matrix originalMatrix = new Matrix(matrix);
        Matrix expectedInverseMatrix = new Matrix(expectedInverse);

        Matrix actualInverseMatrix = Matrix.inverse(originalMatrix);

        assertMatrixEquals(expectedInverseMatrix, actualInverseMatrix);
    }

    @Test
    void testInverseIdentityMatrix() {
        Fraction[][] matrix = {
            {new Fraction(1, 1), new Fraction(0, 1), new Fraction(0, 1)},
            {new Fraction(0, 1), new Fraction(1, 1), new Fraction(0, 1)},
            {new Fraction(0, 1), new Fraction(0, 1), new Fraction(1, 1)}
        };

        Fraction[][] expectedInverse = {
            {new Fraction(1, 1), new Fraction(0, 1), new Fraction(0, 1)},
            {new Fraction(0, 1), new Fraction(1, 1), new Fraction(0, 1)},
            {new Fraction(0, 1), new Fraction(0, 1), new Fraction(1, 1)}
        };

        Matrix originalMatrix = new Matrix(matrix);
        Matrix expectedInverseMatrix = new Matrix(expectedInverse);

        Matrix actualInverseMatrix = Matrix.inverse(originalMatrix);
        
        assertMatrixEquals(expectedInverseMatrix, actualInverseMatrix);
    }

    private void assertMatrixEquals(Matrix expectedMatrix, Matrix actualMatrix) {
    	Fraction[][] expected = expectedMatrix.matrix;
        Fraction[][] actual = actualMatrix.matrix;

        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }
	
}