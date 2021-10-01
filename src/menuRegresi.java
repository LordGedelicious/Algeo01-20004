package src;

import java.util.Scanner;

public class menuRegresi {
    public static void createSPLKeyboard() {
        try (Scanner data_input = new Scanner(System.in)) {
            System.out.println("Silahkan input jumlah data yang ingin dimasukkan : ");
            int row_data = data_input.nextInt();
            System.out.println("Silahkan input jumlah kolom per data yang ingin dimasukkan (termasuk variabel(x) dan hasil(y))");
            int col_data = data_input.nextInt();
            matriks data = new matriks(row_data, col_data);
            matriks regresi = new matriks(col_data, col_data + 1);
            System.out.println("Silahkan input isi data dengan format y1, x1, x2, ..., xn");
            for (int i = 0; i < row_data; i++) {
                for (int j = 0; j < col_data; j++) {
                    data.elmt[i][j] = data_input.nextDouble();
                }
            }
            // untuk posisi regresi.elmt[0][0]
            regresi.elmt[0][0] = row_data;
            // untuk posisi regresi.elmt[i][0] dan regresi.elmt[0][i]
            for (int j = 0; j < row_data; j++) {
                for (int k = 1; k < col_data; k++) {
                    regresi.elmt[k][0] += data.elmt[j][k];
                    regresi.elmt[0][k] += data.elmt[j][k]; 
                }
            }
            // untuk posisi regresi.elmt[j][k] dengan j dan k non-nol, kecuali untuk regresi.elmt[i][col_data - 1]
            for (int l = 1; l < col_data; l++) {
                for (int k = 1; k < col_data; k++) {
                    for (int j = 0; j < row_data; j++) {
                        regresi.elmt[l][k] += data.elmt[j][k] * data.elmt[j][l];
                    }
                }
            }
            // untuk posisi regresi.elmt[i][col_data - 1]
            double sum_y = 0.0;
            for (int i = 0; i < row_data; i++) {
                sum_y += data.elmt[i][0];
            }
            regresi.elmt[0][col_data] = sum_y;
            for (int j = 0; j < row_data; j++) {
                for (int k = 1; k < col_data; k++) {
                    regresi.elmt[k][col_data] += data.elmt[j][0] * data.elmt[j][k]; 
                }
            }
            regresi.displayMatriks();
            solvebyGauss(regresi);
        }
    }
    public static void solvebyGauss(matriks m) {
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
                } else if (m.elmt[j][j] == 0) {
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
        int dab = 0;    // Variabel untuk mengurangi kalo serongnya bablas
        while (!m.isIdxValid(m.cols - 2 - dab, m.cols - 2)) dab += 1;   // Buat ngecek serognya valid apa ga
        if (m.rows < m.cols-1 || m.elmt[m.cols - 2 - dab][m.cols - 2] == 0) {    // Buat ngecek penyelesaiannya satu apa engga
            if (m.rows < m.cols-1 || m.elmt[m.cols -2 - dab][m.cols - 1] == 0) {     // Ngecek infinit solution apa engga
                System.out.println("Sistem regresi memiliki solusi tak hingga");
                mainMenu.Menu();
            } else {
                System.out.println("Sistem regresi tidak memiliki solusi yang memuaskan");
                mainMenu.Menu();
            }
        } else {
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
            }
            for (int i = m.rows - euy; i >= 0; i--) {
                int where = 0;
                for (int j = 0; j < (m.cols - 1); j++) {
                    if (m.elmt[i][j] == 1.0) {
                        where = i;
                        break;
                    }
                }
                answer[i] = m.elmt[i][m.cols - 1]; 
                for (int k = where + 1; k < m.cols - 1; k++) {
                    answer[i] -= answer[k] * m.elmt[i][k];
                }
            }
            for (int i = 0; i < m.cols - 1; i++) {
                System.out.println(answer[i]);
            }
            finalResults(answer, m.cols - 2);
        }
    }
    public static void finalResults(double[] answer, int size) {
        try (Scanner new_var_input = new Scanner(System.in)) {
            double[] new_var = new double[size];
            for (int i = 0; i < size; i++) {
                new_var[i] = new_var_input.nextDouble();
            }
            String regression_equation = "y = " + "(" + answer[0] + ") + ";
            double regression_result = answer[0];
            for (int i = 1; i < size + 1; i++) {
                regression_equation += "(" + answer[i] + ")x_" + (i); 
                if (i != size) {
                    regression_equation += " + ";
                }
                if (i > 1) {
                    regression_result += answer[i] * new_var[i - 1];
                }
            }
            System.out.println(regression_equation);
            System.out.println("y = " + regression_result);
        }
    }
}
