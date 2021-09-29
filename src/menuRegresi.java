package src;

import java.util.Scanner;

public class menuRegresi {
    public static void createSPL() {
        Scanner data_input = new Scanner(System.in);
        int row_data = data_input.nextInt();
        int col_data = data_input.nextInt();
        matriks data = new matriks(row_data, col_data);
        matriks regresi = new matriks(col_data, col_data + 1);
        for (int i = 0; i < row_data; i++) {
            for (int j = 0; j < col_data; j++) {
                data.elmt[i][j] = data_input.nextDouble();
            }
        }
        data_input.close();
        // untuk posisi regresi.elmt[0][0]
        regresi.elmt[0][0] = row_data;
        // untuk posisi regresi.elmt[i][0] dan regresi.elmt[0][i]
        for (int j = 0; j < row_data; j++) {
            for (int k = 1; k < col_data; k++) {
                regresi.elmt[k][0] += data.elmt[j][k];
                regresi.elmt[0][k] += data.elmt[j][k]; 
            }
        }
        regresi.displayMatriks();
        // untuk posisi regresi.elmt[j][k] dengan j dan k non-nol, kecuali untuk regresi.elmt[i][col_data - 1]
        for (int l = 1; l < col_data; l++) {
            for (int k = 1; k < col_data; k++) {
                for (int j = 0; j < row_data; j++) {
                    regresi.elmt[l][k] += data.elmt[j][k] * data.elmt[j][l];
                }
            }
        }
        regresi.displayMatriks();
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
    }
}
