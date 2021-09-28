package src;

import java.lang.reflect.Array;

public class menuSPL {
    public static void SPLGauss(matriks m) {
        for(int j = 0; j < m.cols-1; j++) {
            int yankee = 0;   
            while (m.isIdxValid(j, j+yankee) && m.elmt[j][j+yankee] != 1) {
                if (m.elmt[j][j+yankee] != 1 && m.elmt[j][j+yankee] != 0) {
                    double withno = m.elmt[j][j+yankee];
                    for (int x=0; x<m.cols; x++) {
                        m.elmt[j][x] /= withno;
                    }
                    break;
                }
                else if (m.elmt[j][j] == 0) {
                    yankee ++;
                }
            }
            for(int i = 1+j; i < m.rows; i++) {
                if (m.elmt[i][j+yankee] != 0.0) {
                    double brim = m.elmt[i][j+yankee];
                    for (int k = 0; k < m.cols; k++) {
                        m.elmt[i][k] = m.elmt[i][k] - brim/(m.elmt[j][j+yankee])*m.elmt[j][k];
                    }
                }
            }
        }
        double[] answer = new double[m.cols-1];
        for (int i = m.cols - 1; i >= 0; i--) {
            answer[i] = m.elmt[i][m.rows - 1];
            for (int j = i + 1; j < m.cols - 1; j++) {
                answer[i] = answer[i] - m.elmt[i][j] * answer[j];
            }
        }
        System.out.println("X" + ans.length + " adalah " + ans[m.cols-2]);
        // double[] ans = new double[m.cols-1];
        // if ((m.rows >= m.cols) && (m.elmt[m.cols-2][m.cols-3] == 0) && (m.elmt[m.cols-2][m.cols-2] != 0)) {
        //     ans[m.cols-2] = m.elmt[m.cols-2][m.cols-1]/m.elmt[m.cols-2][m.cols-2];
        // }
        // System.out.println("\nX" + ans.length + " adalah " + ans[m.cols-2]);
        // buat ngedisplay tiap x nya belom beres baru bikin matriks echelon doang
    }
}
