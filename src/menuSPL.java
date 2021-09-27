import src.matriks;

public class menuSPL {
    public static void gaussMethod(double Matrix[][]) {
    }
    public static int rowEchelonElim(double Matrix[][], int dimension) {
        for (int i = 0; i < dimension; i++) {
            int i_max = i;
            int v_max = (int)Matrix[i_max][i];

            for (int j = i + 1; i < dimension; j++) {
                if (Math.abs(Matrix[j][i]) > v_max) {
                    v_max = (int)Matrix[j][i];
                    i_max = j;
                } 
            }

            if (Matrix[i][i_max] == 0) {
                return i;
            }

            if (i_max != i) {
                swap_row(Matrix, i, i_max);
            }

            for (int j = i + 1; j < dimension; j++) {
                double f = Matrix[j][i] / Matrix[i][i];

                for (int k = i + 1; k <= dimension; k++) {
                    Matrix[j][k] -= Matrix[i][k] * f;
                }

                Matrix[j][i] = 0;
            }
        }
        return 1;
    }
    // referensi gw dari sini https://www.geeksforgeeks.org/gaussian-elimination/
}
