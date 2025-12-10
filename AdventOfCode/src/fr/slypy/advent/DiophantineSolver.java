package fr.slypy.advent;

public class DiophantineSolver {

    /** 
     * Solve A * x = b for integer x >= 0 
     * Returns minimum sum of x_i, or -1 if no solution
     */
    public static int solveMinimumSum(int[][] A, int[] b) {
        int m = A.length;
        int n = A[0].length;

        // Step 1: Compute SNF of A: U * A * V = D
        SmithNormalFormResult snf = computeSNF(A);

        int[][] D = snf.D;
        int[][] V = snf.V;
        int[][] U = snf.U;

        // Step 2: Transform target: s' = U * b
        int[] sp = multiplyMatrixVector(U, b);

        // Step 3: Solve diagonal system D * y = s'
        int[] y0 = new int[n];
        for (int i = 0; i < m; i++) {
            if (D[i][i] == 0) {
                if (sp[i] != 0) return -1; // no solution
            } else {
                if (sp[i] % D[i][i] != 0) return -1; // no integer solution
                y0[i] = sp[i] / D[i][i];
            }
        }

        // Step 4: Transform back x = V * y0
        int[] x = multiplyMatrixVector(V, y0);

        // Step 5: Check non-negativity and return sum
        for (int xi : x) {
            if (xi < 0) {
                // TODO: handle free variables if needed
                return -1;
            }
        }

        int sum = 0;
        for (int xi : x) sum += xi;

        return sum;
    }

    /** Multiply matrix (int[][]) with vector (int[]) */
    private static int[] multiplyMatrixVector(int[][] M, int[] v) {
        int m = M.length;
        int n = v.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                s += M[i][j] * v[j];
            }
            res[i] = s;
        }
        return res;
    }

    /** SNF result container */
    private static class SmithNormalFormResult {
        int[][] D, U, V;
        SmithNormalFormResult(int[][] D, int[][] U, int[][] V) {
            this.D = D;
            this.U = U;
            this.V = V;
        }
    }

    /** Compute Smith Normal Form of integer matrix A
     *  Returns D, U, V such that U*A*V = D
     *  This is a simple version for small integer matrices
     */
    private static SmithNormalFormResult computeSNF(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        int[][] D = new int[m][n];
        int[][] U = identity(m);
        int[][] V = identity(n);

        // Copy A to D
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                D[i][j] = A[i][j];

        // TODO: Implement full SNF algorithm
        // For now, assume A is diagonalizable (or pre-diagonal)
        // This is a placeholder
        return new SmithNormalFormResult(D, U, V);
    }

    private static int[][] identity(int n) {
        int[][] I = new int[n][n];
        for (int i = 0; i < n; i++) I[i][i] = 1;
        return I;
    }

    public static void main(String[] args) {
        // Example: 2 equations, 3 variables
        int[][] A = {
            {0, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 0},
            {1, 1, 0, 1, 0, 0}
        };
        int[] b = {3, 5, 4, 7};

        int minSum = solveMinimumSum(A, b);
        System.out.println("Minimum sum = " + minSum);
    }
}
