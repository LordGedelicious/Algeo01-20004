package src;

public class menuDeterminant {
    public static double determinantKofaktor(matriks m){
        if(m.rows == 1) return m.elmt[0][0];
        int sign = 1;
        float det = 0;
        matriks mKof = new matriks(m.rows-1, m.cols-1);
        for(int i = 0; i < mKof.rows; i++){
            for(int j = 0; j < mKof.cols; j++){
                mKof.elmt[i][j] = m.elmt[i+1][j+1];
            }
        }
        for(int i = 0; i < m.cols; i++){
            if(i != 0){
                for(int x = 0; x < mKof.rows; x++){
                    mKof.elmt[x][i-1] = m.elmt[x+1][i-1];
                }
            }
            det += m.elmt[0][i] * sign * determinantKofaktor(mKof);
            sign *= -1;
        }
        return det;
    }
    public static double determinantReduksiBaris(matriks m) {
        for(int j = 0; j < m.cols-1; j++) {    
            for(int i = 1+j; i < m.rows; i++) {
                if (m.elmt[i][j] != 0) {
                    for (int k = 0; k < m.cols; k++) {
                        m.elmt[i][k] = m.elmt[i][k] - ((m.elmt[i][j])/(m.elmt[j][j]))*m.elmt[j][k];
                    }
                }
            }
        }
        double det = 1.0;
        m.displayMatriks();
        for(int x = 0; x < m.rows; x++) {
            det *= m.elmt[x][x];
        }
        return det;
    }
}