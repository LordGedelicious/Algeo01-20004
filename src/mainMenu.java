package src;
import java.util.*;

public class mainMenu {
    public static void main(String[] args) {
        Menu();
    }
    static void SPL() {
        try (Scanner spl_input = new Scanner(System.in)) {
            System.out.println("Pilih Metode Untuk Menyelesaikan SPL : ");
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Eliminasi Gauss-Jordan");
            System.out.println("3. Metode Matriks Balikan");
            System.out.println("4. Kaidah Cramer");
            System.out.println("5. Kembali ke Menu Utama");
            int det_int = spl_input.nextInt();
            switch (det_int) {
                case 1:
                    matriks solve_gauss = readOrType();
                    menuSPL.SPLGauss(solve_gauss);
                    break;
                case 2:
                    matriks solve_gauss_jordan = readOrType();
                    menuSPL.SPLGaussJordan(solve_gauss_jordan);;
                    break;
                case 3:
                    matriks solve_invers = readOrType();
                    menuSPL.SPLInverse(solve_invers);
                    break;
                case 4:
                    matriks solve_cramer = readOrType();
                    menuSPL.SPLCramer(solve_cramer);
                    break;
                case 5:
                    Menu();
                    break;
                default:
                    System.out.println("Input tidak dikenali. Silahkan coba ulang");
                    Menu();
                    break;
            }
        }
    }
    public static matriks readOrType() {
        Scanner choice_input = new Scanner(System.in);
        System.out.println("Pilih cara untuk mengisi matriks untuk diproses");
        System.out.println("1. Input keyboard");
        System.out.println("2. Baca dari file");
        int choice = choice_input.nextInt();
        choice_input.close();
        switch (choice) {
            case 1:
                matriks input_matriks = initiateMatriks();
                return input_matriks;
            case 2:
                // ini kalo read dari file
                matriks input_matriks_1 = initiateMatriks();
                return input_matriks_1; // ini biar kodenya jalan aja, nanti diganti
            default:
                // ini kalo selain dari 1 atau 2 inputnya
                System.out.println("Input tidak dikenali, silahkan coba ulang");
                return readOrType();
        }
    }
    static matriks initiateMatriks() {
        Scanner matriks_input = new Scanner(System.in);
        // ukuran matriks adalah row_matriks x col_matriks
        int row_matriks = matriks_input.nextInt();
        int col_matriks = matriks_input.nextInt();
        matriks spl = new matriks(row_matriks, col_matriks);
        for (int i = 0; i < row_matriks; i++) {
            for (int j = 0; j < col_matriks; j++) {
                spl.elmt[i][j] = matriks_input.nextDouble();
            }
        }
        matriks_input.close();
        return spl;
    }
    static void Menu() {
        try (Scanner first_input = new Scanner(System.in)) {
            System.out.println("Menu");
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Determinan");
            System.out.println("3. Invers Matriks");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi Linear Berganda");
            System.out.println("6. Keluar");
            int first_int = first_input.nextInt();
            switch (first_int) {
                case 1:
                    SPL();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
