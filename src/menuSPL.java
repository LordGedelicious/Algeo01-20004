package src;

import java.lang.invoke.StringConcatException;
import java.lang.reflect.Array;

import javax.lang.model.element.Element;

import static src.menuDeterminant.*;
import static src.menuInvers.*;

public class menuSPL {
    public static void SPLGauss(matriks m) {
        if (m.elmt[0][0] == 0) {
            int z = 0;
                while (m.isIdxValid(z+1, 0) && m.elmt[z][0] == 0) {
                    z++;
                }if (m.elmt[z][0] != 0) {
                    m.rowSwap(0, z);
                }
        }for(int j = 0; j < m.cols-1; j++) {
            int yankee = 0;   
            while (m.isIdxValid(j, j+yankee) && m.elmt[j][j+yankee] != 1) {
                if (m.elmt[j][j+yankee] != 1 && m.elmt[j][j+yankee] != 0) {
                    double withno = m.elmt[j][j+yankee];
                    for (int x=0; x<m.cols; x++) {
                        m.elmt[j][x] /= withno;
                    }break;
                }else if (m.elmt[j][j] == 0) {
                    yankee ++;
                }
            }for(int i = 1+j; i < m.rows; i++) {
                if (m.isIdxValid(i, j+yankee) && m.elmt[i][j+yankee] != 0.0) {
                    double brim = m.elmt[i][j+yankee];
                    for (int k = 0; k < m.cols; k++) {
                        m.elmt[i][k] = m.elmt[i][k] - brim/(m.elmt[j][j+yankee])*m.elmt[j][k];
                    }
                }
            }
        }
        // Matriks Eselon Baris Jadi
        System.out.println("Solusi dari SPL di atas adalah :");
        int dab = 0;    // Variabel untuk mengurangi kalo serongnya bablas
        while (!m.isIdxValid(m.cols - 2 - dab, m.cols - 2)) dab += 1;   // Buat ngecek serognya valid apa ga
        if (m.rows < m.cols-1 || m.elmt[m.cols - 2 - dab][m.cols - 2] == 0) {    // Buat ngecek penyelesaiannya satu apa engga
            if (m.rows < m.cols-1 || m.elmt[m.cols -2 - dab][m.cols - 1] == 0) {     // Ngecek infinit solution apa engga
                double[] answer = new double[m.cols-1];
                for (int a = 0; a < m.cols-1; a++) answer[a] = -999;
                String[] answerInf = new String[m.cols-1];
                for (int a = 0; a < m.cols-1; a++) answerInf[a] = "";

                for(int i = m.cols-2-dab; i >= 0; i--) {
                    int where = -1;
                    for(int j = 0; j < m.cols-1; j++) {
                        if (m.elmt[i][j] != 0) {where = j;
                            break;
                        }
                    }if (where != -1) {
                        answer[where] = (m.elmt[i][m.cols-1])/(m.elmt[i][where]);
                        for(int k = where+1; k < m.cols-1; k++) {
                            int count = k+1;
                            if (m.elmt[i][k] != 0) {
                                double pengali = -(m.elmt[i][k]/m.elmt[i][where]);
                                if (k != where+1 && answerInf[where] != "") answerInf[where] += " + ";
                                if (answer[k] == -999 && answerInf[k] == "") {
                                    if (pengali == 1.0) answerInf[where] += "X" + count;
                                    else if (pengali == -1.0) answerInf[where] += "(-X" + count + ")";
                                    else answerInf[where] += pengali + "(X" + count + ")";
                                }else if (answerInf[k] != "") {
                                    if (pengali == 1.0) answerInf[where] += "(" + answerInf[k] + ")";
                                    else if (pengali == -1.0) answerInf[where] += "(-(" + answerInf[k] + "))";
                                    else answerInf[where] += pengali + "(" + answerInf[k] + ")";
                                    answer[where] += pengali*answer[k];
                                }else answer[where] += pengali*answer[k];
                            }
                        }
                    }
                }for(int x = 0; x < m.cols-1; x++) {
                    int count = x+1;
                    if (answer[x] != -999) {
                        System.out.print("X" + count + " adalah ");
                        if (answer[x] != 0) System.out.print(answer[x]);
                        if (answer[x] != 0 && answerInf[x] != "") System.out.print(" + ");
                        if (answerInf[x] != "") System.out.print(answerInf[x]);
                        System.out.println("");
                    }
                    else System.out.println("X" + count + " memiliki solusi semua bilangan real");
                }
            }else {
                System.out.println("SPL tidak memiliki solusi yang memenuhi");
            }
        }else {
            double answer[] = new double[m.cols - 1];
            int euy = 0, a = m.rows - 1, b = 0;
            boolean br = true;
            while (br && a >= 0) {
                b = 0;
                while (br && (b < (m.cols - 1))) {
                    if (m.elmt[a][b] != 0) {
                        br = false;
                        break;
                    } b++;
                } a--; euy += 1;
            }for (int i = m.rows - euy; i >= 0; i--) {
                int where = 0;
                for (int j = 0; j < (m.cols - 1); j++) {
                    if (m.elmt[i][j] == 1.0) {
                        where = i;
                        break;
                    }
                }answer[i] = m.elmt[i][m.cols - 1]; 
                for (int k = where + 1; k < m.cols - 1; k++) {
                    answer[i] -= answer[k] * m.elmt[i][k];
                }
            }for (int p = 0; p < m.cols-1; p++) {
                System.out.println("X" + (p + 1) + " adalah " + answer[p]);
            }
        }
    }

    public static void SPLGaussJordan(matriks m) {
        if (m.elmt[0][0] == 0) {
            int z = 0;
                while (m.isIdxValid(z+1, 0) && m.elmt[z][0] == 0) {
                    z++;
                }if (m.elmt[z][0] != 0) {
                    m.rowSwap(0, z);
                }
        }for(int j = 0; j < m.cols-1; j++) {
            int yankee = 0;   
            while (m.isIdxValid(j, j+yankee) && m.elmt[j][j+yankee] != 1) {
                if (m.elmt[j][j+yankee] != 1 && m.elmt[j][j+yankee] != 0) {
                    double withno = m.elmt[j][j+yankee];
                    for (int x=0; x<m.cols; x++) {
                        m.elmt[j][x] /= withno;
                    }
                    break;
                }else if (m.elmt[j][j] == 0) {
                    yankee ++;
                }
            }for(int i = 0; i < m.rows; i++) {
                if (m.isIdxValid(j, j+yankee) && (i != j) && (m.elmt[i][j+yankee] != 0.0)) {
                    double brim = m.elmt[i][j+yankee];
                    for (int k = 0; k < m.cols; k++) {
                        m.elmt[i][k] = m.elmt[i][k] - brim/(m.elmt[j][j+yankee])*m.elmt[j][k];
                    }
                }
            }
        }
        System.out.println("Solusi dari SPL di atas adalah :");
        int dab = 0;
        while (!m.isIdxValid(m.cols - 2 - dab, m.cols - 2)) dab += 1;
        if (m.rows < m.cols-1 || m.elmt[m.cols-2-dab][m.cols-2] == 0) {
            if (m.rows < m.cols-1 || m.elmt[m.cols-2-dab][m.cols-1] == 0) {
                double[] answer = new double[m.cols-1];
                for (int a = 0; a < m.cols-1; a++) answer[a] = -999;
                String[] answerInf = new String[m.cols-1];
                for (int a = 0; a < m.cols-1; a++) answerInf[a] = "";

                for(int i = m.cols-2-dab; i >= 0; i--) {
                    int where = -1;
                    for(int j = 0; j < m.cols-1; j++) {
                        if (m.elmt[i][j] != 0) {where = j;
                            break;
                        }
                    }if (where != -1) {
                        answer[where] = (m.elmt[i][m.cols-1])/(m.elmt[i][where]);
                        for(int k = where+1; k < m.cols-1; k++) {
                            int count = k+1;
                            if (m.elmt[i][k] != 0) {
                                double pengali = -(m.elmt[i][k]/m.elmt[i][where]);
                                if (k != where+1 && answerInf[where] != "") answerInf[where] += " + ";
                                if (answer[k] == -999 && answerInf[k] == "") {
                                    if (pengali == 1.0) answerInf[where] += "X" + count;
                                    else if (pengali == -1.0) answerInf[where] += "(-X" + count + ")";
                                    else answerInf[where] += pengali + "(X" + count + ")";
                                }else if (answerInf[k] != "") {
                                    if (pengali == 1.0) answerInf[where] += "(" + answerInf[k] + ")";
                                    else if (pengali == -1.0) answerInf[where] += "(-(" + answerInf[k] + "))";
                                    else answerInf[where] += pengali + "(" + answerInf[k] + ")";
                                    answer[where] += pengali*answer[k];
                                }else answer[where] += pengali*answer[k];
                            }
                        }
                    }
                }for(int x = 0; x < m.cols-1; x++) {
                    int count = x+1;
                    if (answer[x] != -999) {
                        System.out.print("X" + count + " adalah ");
                        if (answer[x] != 0) System.out.print(answer[x]);
                        if (answer[x] != 0 && answerInf[x] != "") System.out.print(" + ");
                        if (answerInf[x] != "") System.out.print(answerInf[x]);
                        System.out.println("");
                    }
                    else System.out.println("X" + count + " memiliki solusi semua bilangan real");
                }
            }else {
                System.out.println("SPL tidak memiliki solusi yang memenuhi");
            }
        }else {
            for(int i = 0; i<m.cols-1; i++) {
                int count = i+1;
                System.out.println("X" + count + " adalah " + m.elmt[i][m.cols-1]/m.elmt[i][i]);
            }
        }
    }

    public static void SPLCramer(matriks m) {
        int size = m.rows;
        double[] answer = new double[size];
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
                System.arraycopy(m.elmt[i], 0, isolated.elmt[i], 0, size);
            }
        }
        System.out.println("Solusi dari SPL di atas adalah :");
        for (int i = 0; i < size; i++) {
            System.out.println("X" + (i + 1) + " adalah " + answer[i]);
        }
    }

    public static void SPLInverse(matriks m){
        if(m.cols == m.rows + 1){
            matriks lf = new matriks(m.rows, m.rows);
            matriks ri = new matriks(m.rows, 1);
            matriks ans = new matriks(m.rows, m.rows);
            for(int i = 0; i < m.rows; i++){
                for(int j = 0; j < m.rows; j++){
                    lf.elmt[i][j] = m.elmt[i][j];
                }
            }
            for(int i = 0; i < m.rows; i++){
                    ri.elmt[i][0] = m.elmt[i][m.rows];
            }
            lf = menuInvers.inverse(lf);
            ans = matriks.multiplyMatriks(lf, ri);
            System.out.println("Solusi dari SPL di atas adalah :");
            for (int i = 0; i < m.rows; i++) {
                System.out.println("X" + (i + 1) + " adalah " + m.elmt[i][1]);
            }
        }
    }
}
