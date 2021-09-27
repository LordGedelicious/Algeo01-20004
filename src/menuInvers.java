package src;

import src.menuDeterminant;

public class menuInvers {
    public static void returnCofactor(double Matrix[][], double temp[][], int row, int col, int dimension) {
        int i = 0, j = 0;
        for (int matrix_row = 0; matrix_row < dimension; matrix_row++) {
            for (int matrix_col = 0; matrix_col < dimension; matrix_col++) {
                if (matrix_col != col && matrix_row != row) {
                    temp[i][j++] = Matrix[matrix_row][matrix_col];
                    if (j == dimension - 1) {
                        j = 0;
                        i += 1;
                    }
                }
            }
        }
    }
    public static void adjointMatrix(double Matrix[][], double adj[][], int dimension) {
        if (dimension == 1) {
            adj[0][0] = 1;
            return;
        }
        int negative_sign = 1;
        double temp[][] = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                returnCofactor(Matrix, temp, i, j, dimension);
                negative_sign = ((i + j) % 2 == 0) ? 1: -1;
                adj[j][i] = (negative_sign)*(menuDeterminant.determinanKofaktor(Matrix));
            }
        }
    }
    public static boolean getInverseAdjoint(double Matrix[][], double Inverted[][], int dimension) {
        int det = menuDeterminant.determinantKofaktor(Matrix);
        if (det == 0) {
            System.out.println("Matriks singular tidak memiliki invers karena berdeterminan 0!");
            return false;
        }
        double adj[][] = new double[dimension][dimension];
        adjointMatrix(Matrix, adj, dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Inverted[i][j] = adj[i][j]/(float)det;
            }
        }
        return true;
    }
}
