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
        // double[] ans = new double[m.cols-1];
        // if ((m.rows >= m.cols) && (m.elmt[m.cols-2][m.cols-3] == 0) && (m.elmt[m.cols-2][m.cols-2] != 0)) {
        //     ans[m.cols-2] = m.elmt[m.cols-2][m.cols-1]/m.elmt[m.cols-2][m.cols-2];
        // }
        // System.out.println("\nX" + ans.length + " adalah " + ans[m.cols-2]);
        // buat ngedisplay tiap x nya belom beres baru bikin matriks echelon doang
    }

    public static void SPLGaussJordan(matriks m) {
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
            for(int i = 0; i < m.rows; i++) {
                if ((i != j) && (m.elmt[i][j+yankee] != 0.0)) {
                    double brim = m.elmt[i][j+yankee];
                    for (int k = 0; k < m.cols; k++) {
                        m.elmt[i][k] = m.elmt[i][k] - brim/(m.elmt[j][j+yankee])*m.elmt[j][k];
                    }
                }
            }
        }
        if (m.elmt[m.cols-2][m.cols-2] == 0) {
            if (m.elmt[m.cols-2][m.cols-1] == 0) {
                // belom jadi
            }
            else {
                System.out.println("Solusi tidak ada.");
            }
        }
        else {
            for(int i = 0; i<m.cols-1; i++) {
                int count = i+1;
                System.out.println("X" + count + " = " + m.elmt[i][m.cols-1]/m.elmt[i][i]);
            }
        }
    }

    public static void SPLCramer(matriks m) {
        int size = m.rows;
        double answer[] = new double[size];
        matriks isolated = new matriks(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                isolated.elmt[i][j] = m.elmt[i][j];
            }
        }
        double new_det = menuDeterminant.determinantKofaktor(isolated);
        double temp_det;
        for (int k = 0; k < size; k++) {
            for (int l = 0; l < size; l++) {
                isolated.elmt[l][k] = m.elmt[l][size];
            }
            temp_det = menuDeterminant.determinantKofaktor(isolated);
            answer[k] = temp_det / new_det;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    isolated.elmt[i][j] = m.elmt[i][j];
                }
            }
        }
        System.out.println("Solusi dari SPL di atas adalah :");
        for (int i = 0; i < size; i++) {
            System.out.println("Nilai Veriabel ke-" + (i + 1) + " adalah " + answer[i]);
        }
    }
}
