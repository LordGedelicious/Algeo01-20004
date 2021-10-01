package src;

import java.util.*;

public class menuInterpolasi {
    public static void createMatriksInterpolasiKeyboard() {
        try (Scanner points_input = new Scanner(System.in)) {
            // points_input adalah scanner untuk menerima input jumlah titik-titik yang akan diinterpolasikan
            System.out.println("Masukkan jumlah koordinat yang ingin diinput: ");
            int points = points_input.nextInt();
            // membuat matriks untuk menyimpan matriks p_n(x) = a_0 + a_1 * (x ^ 1) + ... + a_n * (x ^ n)
            matriks point_array = new matriks(points, points + 1);
            System.out.println("Masukkan koordinat yang ingin dibuat interpolasinya: ");
            // menerima input koordinat yang ingin diisi ke dalam matriks point_array
            try (Scanner x_y_input = new Scanner(System.in)) {
                for (int i = 0; i < points; i++) { // i = baris
                    for (int j  = 0; j < points; j++) { // j = kolom
                        if (j == 0) { // sesuai rumus yang diberikan di spek tugas
                            point_array.elmt[i][j] = 1;
                        } else if (j == 1) { // menjadi base untuk j > 1
                            point_array.elmt[i][j] = x_y_input.nextDouble();
                        } else { // mengikuti rumus yang diberikan di spek tugas dengan pangkat sama dengan posisi kolom matriks (j)
                            point_array.elmt[i][j] = Math.pow(point_array.elmt[i][1], (double) j);
                        }
                    } // input dari y
                    point_array.elmt[i][points] = x_y_input.nextDouble();
                } // F.S. Matriks telah terbuat, diselesaikan dengan metode matriks
                solveByCramer(point_array);
            }
        }
    }
    public static void solveByCramer(matriks m) {
        int size = m.rows;
        double[] answer = new double[size];
        matriks isolated = new matriks(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                isolated.elmt[i][j] = m.elmt[i][j];
            }
        }
        double new_det = menuDeterminant.determinantKofaktor(isolated);
        if (new_det == 0) {
            System.out.println("Matriks memiliki determinan nol, tidak ada satu persamaan solusi yang valid!");
            mainMenu.Menu();
        } else {
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
        }
        finalResults(answer, size);
    }
    public static void finalResults(double[] answer, int size) {
        try (Scanner estimate_input = new Scanner(System.in)) {
            System.out.println("Masukkan titik yang ingin diestimasi : ");
            double estimate = estimate_input.nextDouble();
            double estimation_count = 0.0;
            String equation = new String();
            String estimation = new String();
            equation = "";
            equation += "p" + (size - 1) + "(" + estimate + ") = ";
            equation += answer[0] + " + ";
            for (int i = 1; i < size; i++) {
                equation += answer[i] + "(" + estimate + ")^" + i;
                if (i != size - 1) {
                    equation += " + ";
                }
            }
            System.out.println(equation);
            for (int i = 0; i < size; i++) {
                estimation_count += answer[i] * Math.pow(estimate, (double) i);
            }
            estimation = "p" + (size - 1) + "(" + estimate + ") = " + estimation_count;
            System.out.println(estimation);
        }
    }
}
