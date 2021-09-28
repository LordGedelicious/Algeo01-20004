package src;

public class menuInvers {
    public static matriks adjoint(matriks mat){
        matriks adj = new matriks(mat.rows, mat.cols);
        if (mat.rows == 1) {
            adj.elmt[0][0] = 1;
            return adj;
        }
        int sign = 1;
        for(int i = 0; i < mat.rows; i++){
            for(int j = 0; j < mat.cols; j++){
                matriks tmp = matriks.minor(mat, i, j);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj.elmt[j][i] = menuDeterminant.determinantKofaktor(tmp) * sign;
            }
        }
        return adj;
    }

    public static matriks inverse(matriks mat){
        return matriks.divideConst(adjoint(mat), menuDeterminant.determinantKofaktor(mat));
    }

}
