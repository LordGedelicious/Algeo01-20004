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
                default:
                    break;
            }
        }
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
