package src;
import java.util.*;
import java.io.*;

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
            System.out.print("\nMasukan: ");
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
    static void Determinan() {
        try (Scanner det_input = new Scanner(System.in)) {
            System.out.println("Pilih Metode Untuk Mencari Determinan : ");
            System.out.println("1. Metode Kofaktor");
            System.out.println("2. Metode Reduksi Baris");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("\nMasukan: ");
            int det_int = det_input.nextInt();
            switch (det_int) {
                case 1:
                    matriks solve_kofaktor = readOrTypeDet();
                    double det_kofaktor = menuDeterminant.determinantKofaktor(solve_kofaktor);
                    System.out.println("Determinan matriksnya adalah " + det_kofaktor);
                    break;
                case 2:
                    matriks solve_reduksi = readOrTypeDet();
                    double det_reduksi = menuDeterminant.determinantReduksiBaris(solve_reduksi);
                    System.out.println("Determinan matriksnya adalah " + det_reduksi);
                    break;
                case 3:
                    Menu();
                    break;
                default:
                    Determinan();
                    break;
            }
        }
    }
    static void Invers() {
        matriks solve_invers = readOrType();
        matriks solved_invers = menuInvers.inverse(solve_invers);
        solved_invers.displayMatriks();
    }
    public static matriks readOrType() {
        try (Scanner choice_input = new Scanner(System.in)) {
            System.out.println("Pilih cara untuk mengisi matriks untuk diproses");
            System.out.println("1. Input keyboard");
            System.out.println("2. Baca dari file");
            System.out.print("\nMasukan: ");
            int choice = choice_input.nextInt();
            switch (choice) {
                case 1:
                    matriks input_matriks = initiateMatriks();
                    return input_matriks;
                case 2:
                    Scanner fileInput = new Scanner(System.in);
                    System.out.print("Masukkan nama file: ");
                    String fileName = fileInput.next();
                    try {
                        matriks input_matriks_1 = readFileMatriks(fileName);
                        return input_matriks_1;
                    } catch (FileNotFoundException f){}
                default:
                    System.out.println("Input tidak dikenali, silahkan coba ulang");
                    return readOrType();
            }
        }
    }
    public static matriks readOrTypeDet() {
        try (Scanner choice_input = new Scanner(System.in)) {
            System.out.println("Pilih cara untuk mengisi matriks untuk diproses");
            System.out.println("1. Input keyboard");
            System.out.println("2. Baca dari file");
            System.out.print("\nMasukan: ");
            int choice = choice_input.nextInt();
            switch (choice) {
                case 1:
                    matriks input_matriks = initiateMatriksDet();
                    return input_matriks;
                case 2:
                    Scanner fileInput = new Scanner(System.in);
                    System.out.print("Masukkan nama file: ");
                    String fileName = fileInput.next();
                    try {
                        matriks input_matriks_1 = readFileMatriks(fileName);
                        return input_matriks_1;
                    } catch (FileNotFoundException f){}
                     // ini biar kodenya jalan aja, nanti diganti
                default:
                    // ini kalo selain dari 1 atau 2 inputnya
                    System.out.println("Input tidak dikenali, silahkan coba ulang");
                    return readOrType();
            }
        }
    }
    public static matriks initiateMatriks() {
        try (Scanner matriks_input = new Scanner(System.in)) {
            System.out.println("Silahkan input jumlah baris dalam matriksnya: ");
            // ukuran matriks adalah row_matriks x col_matriks
            int row_matriks = matriks_input.nextInt();
            System.out.println("Silahkan input jumlah kolom dalam matriksnya: ");
            int col_matriks = matriks_input.nextInt();
            matriks spl = new matriks(row_matriks, col_matriks);
            System.out.println("Silahkan input elemen dalam matriksnya: ");
            for (int i = 0; i < row_matriks; i++) {
                for (int j = 0; j < col_matriks; j++) {
                    spl.elmt[i][j] = matriks_input.nextDouble();
                }
            }
            return spl;
        }
    }
    public static matriks initiateMatriksDet() {
        try (Scanner matriks_input = new Scanner(System.in)) {
            System.out.println("Silahkan input jumlah baris dan col dalam matriksnya: ");
            // ukuran matriks adalah row_matriks x col_matriks
            int row_col_matriks = matriks_input.nextInt();
            matriks spl = new matriks(row_col_matriks, row_col_matriks);
            System.out.println("Silahkan input elemen dalam matriksnya: ");
            for (int i = 0; i < row_col_matriks; i++) {
                for (int j = 0; j < row_col_matriks; j++) {
                    spl.elmt[i][j] = matriks_input.nextDouble();
                }
            }
            return spl;
        }
    }

    public static matriks readFileMatriks(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        int row = -1;
        ArrayList<ArrayList<Double>> mf = new ArrayList<ArrayList<Double>>();
        while (input.hasNextLine()){
            row++;
            mf.add(new ArrayList<Double>());
            String currRow = input.nextLine();
            Scanner scanRow = new Scanner(currRow);
            while(scanRow.hasNextDouble()){
                double elmt = scanRow.nextDouble();
                mf.get(row).add(elmt);
            }
        }
        matriks ans;
        if (row == 0) {
            ans = new matriks(1,1);
        }
        else {
            ans = new matriks(mf.size(), mf.get(0).size(), mf);
        }
        return ans;
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
            System.out.println(" ");
            System.out.print("Masukan: ");
            int first_int = first_input.nextInt();
            switch (first_int) {
                case 1:
                    SPL();
                    break;
                case 2:
                    Determinan();
                    break;
                case 3:
                    Invers();
                    break;
                case 4:
                    try (Scanner choice_input = new Scanner(System.in)) {
                        System.out.println("Pilih cara untuk mengisi matriks untuk diproses");
                        System.out.println("1. Input keyboard");
                        System.out.println("2. Baca dari file");
                        int choice = choice_input.nextInt();
                        switch (choice) {
                            case 1:
                                menuInterpolasi.createMatriksInterpolasiKeyboard();
                                break;
                            case 2:
                                // untuk isi file
                                break;
                            default:
                                // untuk input di luar 1 atau 2
                                System.out.println("Input tidak dikenali, silahkan coba ulang");
                                Menu();
                        }
                    }
                    break;
                case 5:
                    try (Scanner choice_input = new Scanner(System.in)) {
                        System.out.println("Pilih cara untuk mengisi matriks untuk diproses");
                        System.out.println("1. Input keyboard");
                        System.out.println("2. Baca dari file");
                        int choice = choice_input.nextInt();
                        switch (choice) {
                            case 1:
                                menuRegresi.createSPLKeyboard();
                                break;
                            case 2:
                                // untuk isi file
                                break;
                            default:
                                // untuk input di luar 1 atau 2
                                System.out.println("Input tidak dikenali, silahkan coba ulang");
                                Menu();
                        }
                    }
                    break;
                case 6:
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
