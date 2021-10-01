package src;


import java.lang.reflect.Array;

import javax.lang.model.element.Element;

import static src.menuDeterminant.*;
import static src.menuInvers.*;

public class menuSPL {
    public static void SPLGauss(matriks m) {
        // matriks sembarang
        if (m.elmt[0][0] == 0) {    // Percabangan untuk mengecek jika baris pertama perlu di-swap dengan baris lain (elmt[0][0] bernilai nol)
            int z = 0;
                while (m.isIdxValid(z+1, 0) && m.elmt[z][0] == 0) { // Pengulangan yang mencari baris yang memiliki nilai elmt[z][0] bukan nol (Bisa ditukar dengan baris 1)
                    z++;
                }if (m.elmt[z][0] != 0) {   // Percabangan memastikan ditemukan baris yang memiliki nilai elmt[z][0] bukan nol untuk ditukar
                    m.rowSwap(0, z);
                }
        // Letak baris sudah terurut
        }for(int j = 0; j < m.cols-1; j++) {    // Pengulangan tempat satu utama seharusnya (serong)
            int yankee = 0;   // yankee adalah variabel untuk menandai jika satu utama ada yang longkap (tidak menyerong lurus)
            while (m.isIdxValid(j, j+yankee) && m.elmt[j][j+yankee] != 1) { // Pengulangan jika tempat satu utama seharusnya belum satu
                if (m.elmt[j][j+yankee] != 1 && m.elmt[j][j+yankee] != 0) {
                    double withno = m.elmt[j][j+yankee];    // Variabel withno merupakan pembagi baris agar tempat satu utama seharusnya bernilai 1
                    for (int x=0; x<m.cols; x++) {
                        m.elmt[j][x] /= withno;
                    }break;
                }else if (m.elmt[j][j] == 0) {  // Percabangan jika satu utama longkap
                    yankee ++;
                }
        // Baris j sudah memiliki satu utama
            }for(int i = 1+j; i < m.rows; i++) {    // Pengulangan mengurangi baris di bawah j dengan j sehingga bawah satu utama di j 0 semua
                if (m.isIdxValid(i, j+yankee) && m.elmt[i][j+yankee] != 0.0) {
                    double brim = m.elmt[i][j+yankee];  // Variabel brim merupakan pembagi
                    for (int k = 0; k < m.cols; k++) {
                        m.elmt[i][k] = m.elmt[i][k] - brim/(m.elmt[j][j+yankee])*m.elmt[j][k];
                    }
                }
            }
        }
        // Matriks Eselon Baris Jadi
        System.out.println("Solusi dari SPL di atas adalah :");
        int dab = 0;    // Variabel untuk mengurangi baris yang di cek kalau elmt[kolom-2][kolom-2] invalid
        while (!m.isIdxValid(m.cols - 2 - dab, m.cols - 2)) dab += 1;   // Pengulangan untuk variabel dab
        if (m.rows < m.cols-1 || m.elmt[m.cols - 2 - dab][m.cols - 2] == 0) {    // Percabangan untuk mengecek penyelesaiannya unik atau tidak
            // SPL memiliki solusi infinit
            if (m.rows < m.cols-1 || m.elmt[m.cols -2 - dab][m.cols - 1] == 0) {
                double[] answer = new double[m.cols-1];     // array untuk menyimpan hasil semua x yg tidak ada x lain yang infinit
                for (int a = 0; a < m.cols-1; a++) answer[a] = -999;
                String[] answerInf = new String[m.cols-1];  // array untuk menyimpan hasil semua x yg butuh x lain yang infinit
                for (int a = 0; a < m.cols-1; a++) answerInf[a] = "";

                for(int i = m.cols-2-dab; i >= 0; i--) {    // Pengulangan mencari 1 utama dari baris paling bawah
                    int where = -1; // Variabel where adalah tempat 1 utama di suatu baris, akan -1 jika tdk ada
                    for(int j = 0; j < m.cols-1; j++) { // Mencari satu utama
                        if (m.elmt[i][j] != 0) {where = j;
                            break;
                        }
                    }if (where != -1) { // Jika ada satu utama (ada definisi x di baris itu)
                        answer[where] = (m.elmt[i][m.cols-1])/(m.elmt[i][where]);   // Definisi x tersebut
                        for(int k = where+1; k < m.cols-1; k++) {   // Pengulangan cek di kanan 1 utama apakah menggunakan variabel lain
                            int count = k+1;
                            if (m.elmt[i][k] != 0) {    // Penambahan definisi x tersebut
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
                }for(int x = 0; x < m.cols-1; x++) {    // Output hasil tiap x utk penyelesaian infinit
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
            // SPL tidak memiliki solusi
            }else {
                System.out.println("SPL tidak memiliki solusi yang memenuhi");
            }
        // SPL memiliki solusi unik
        }else {
            double answer[] = new double[m.cols - 1];   // array untuk menyimpan hasil tiap x
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
            }for (int p = 0; p < m.cols-1; p++) {   // Pengulangan output hasil tiap x
                System.out.println("X" + (p + 1) + " adalah " + answer[p]);
            }
        }
    }

    public static void SPLGaussJordan(matriks m) {
        // matriks sembarang
        if (m.elmt[0][0] == 0) {    // Percabangan untuk mengecek jika baris pertama perlu di-swap dengan baris lain (elmt[0][0] bernilai nol)
            int z = 0;
                while (m.isIdxValid(z+1, 0) && m.elmt[z][0] == 0) { // Pengulangan yang mencari baris yang memiliki nilai elmt[z][0] bukan nol (Bisa ditukar dengan baris 1)
                    z++;
                }if (m.elmt[z][0] != 0) {   // Percabangan memastikan ditemukan baris yang memiliki nilai elmt[z][0] bukan nol untuk ditukar
                    m.rowSwap(0, z);
                }
        // Letak baris sudah terurut
        }for(int j = 0; j < m.cols-1; j++) {    // Pengulangan tempat satu utama seharusnya (serong)
            int yankee = 0;   // yankee adalah variabel untuk menandai jika satu utama ada yang longkap (tidak menyerong lurus)   
            while (m.isIdxValid(j, j+yankee) && m.elmt[j][j+yankee] != 1) { // Pengulangan jika tempat satu utama seharusnya belum satu
                if (m.elmt[j][j+yankee] != 1 && m.elmt[j][j+yankee] != 0) {
                    double withno = m.elmt[j][j+yankee];    // Variabel withno merupakan pembagi baris agar tempat satu utama seharusnya bernilai 1
                    for (int x=0; x<m.cols; x++) {
                        m.elmt[j][x] /= withno;
                    }
                    break;
                }else if (m.elmt[j][j] == 0) {  // Percabangan jika satu utama longkap
                    yankee ++;
                }
        // Baris j sudah memiliki satu utama
            }for(int i = 0; i < m.rows; i++) {    // Pengulangan mengurangi baris selain j dengan j sehingga kolom tempat satu utama di j bernilai 0 semua
                if (m.isIdxValid(j, j+yankee) && (i != j) && (m.elmt[i][j+yankee] != 0.0)) {
                    double brim = m.elmt[i][j+yankee];  // Variabel brim merupakan pembagi
                    for (int k = 0; k < m.cols; k++) {
                        m.elmt[i][k] = m.elmt[i][k] - brim/(m.elmt[j][j+yankee])*m.elmt[j][k];
                    }
                }
            }
        }
        // Matriks Eselon Baris Tereduksi Jadi
        System.out.println("Solusi dari SPL di atas adalah :");
        int dab = 0;    // Variabel untuk mengurangi baris yang di cek kalau elmt[kolom-2][kolom-2] invalid
        while (!m.isIdxValid(m.cols - 2 - dab, m.cols - 2)) dab += 1;   // Pengulangan untuk variabel dab
        if (m.rows < m.cols-1 || m.elmt[m.cols-2-dab][m.cols-2] == 0) {    // Percabangan untuk mengecek penyelesaiannya unik atau tidak
            // SPL memiliki solusi infinit
            if (m.rows < m.cols-1 || m.elmt[m.cols-2-dab][m.cols-1] == 0) {
                double[] answer = new double[m.cols-1];     // array untuk menyimpan hasil semua x yg tidak ada x lain yang infinit
                for (int a = 0; a < m.cols-1; a++) answer[a] = -999;
                String[] answerInf = new String[m.cols-1];  // array untuk menyimpan hasil semua x yg butuh x lain yang infinit
                for (int a = 0; a < m.cols-1; a++) answerInf[a] = "";

                for(int i = m.cols-2-dab; i >= 0; i--) {    // Pengulangan mencari 1 utama dari baris paling bawah
                    int where = -1; // Variabel where adalah tempat 1 utama di suatu baris, akan -1 jika tdk ada
                    for(int j = 0; j < m.cols-1; j++) { // Mencari satu utama
                        if (m.elmt[i][j] != 0) {where = j;
                            break;
                        }
                    }if (where != -1) { // Jika ada satu utama (ada definisi x di baris itu)
                        answer[where] = (m.elmt[i][m.cols-1])/(m.elmt[i][where]);   // Definisi x tersebut
                        for(int k = where+1; k < m.cols-1; k++) {   // Pengulangan cek di kanan 1 utama apakah menggunakan variabel lain
                            int count = k+1;
                            if (m.elmt[i][k] != 0) {    // Penambahan definisi x tersebut
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
                }for(int x = 0; x < m.cols-1; x++) {    // Output hasil tiap x utk penyelesaian infinit
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
            // SPL tidak memiliki solusi
            }else { // Jika SPL tidak memiliki penyelesaian
                System.out.println("SPL tidak memiliki solusi yang memenuhi");
            }
        // SPL memiliki solusi unik
        }else {
            for(int i = 0; i<m.cols-1; i++) {   // Output nilai tiap x
                int count = i+1;
                System.out.println("X" + count + " adalah " + m.elmt[i][m.cols-1]/m.elmt[i][i]);
            }
        }
    }

    public static void SPLCramer(matriks m) {
        if(m.cols == m.rows + 1){
            int size = m.rows;
            double[] answer = new double[size];
            matriks isolated = new matriks(size, size);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    isolated.elmt[i][j] = m.elmt[i][j];
                }
            }
            double new_det = menuDeterminant.determinantKofaktor(isolated);
            if (new_det == 0.0) {
                System.out.println("SPL tidak memiliki satu solusi yang valid");
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
                System.out.println("Solusi dari SPL di atas adalah :");
                for (int i = 0; i < size; i++) {
                    System.out.println("X" + (i + 1) + " adalah " + answer[i]);
                }
            }
        }else{
            System.out.println("Metode SPL dengan Cramer hanya bisa menjalankan SPL dengan ukuran matriks augmented n x (n+1). Silahkan gunakan metode Gauss atau Gauss Jordan untuk menyelesaikan permasalahan anda.");
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
                System.out.printf("X%d adalah %.2f\n", i+1, ans.elmt[i][0]);
            }
        }
        else{
            System.out.println("Metode SPL dengan matriks Inverse hanya bisa menjalankan SPL dengan ukuran matriks augmented n x (n+1). Silahkan gunakan metode Gauss atau Gauss Jordan untuk menyelesaikan permasalahan anda.");
        }
    }
}
