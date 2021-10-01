package src;

public class menuDeterminant {
    public static double determinantKofaktor(matriks m){
        if(m.rows == 1) return m.elmt[0][0];
        int sign = 1;
        float det = 0;
        matriks mKof = new matriks(m.rows-1, m.cols-1);
        for(int i = 0; i < mKof.rows; i++){
            for(int j = 0; j < mKof.cols; j++){
                mKof.elmt[i][j] = m.elmt[i+1][j+1];
            }
        }
        for(int i = 0; i < m.cols; i++){
            if(i != 0){
                for(int x = 0; x < mKof.rows; x++){
                    mKof.elmt[x][i-1] = m.elmt[x+1][i-1];
                }
            }
            det += m.elmt[0][i] * sign * determinantKofaktor(mKof);
            sign *= -1;
        }
        return det;
    }
    public static double determinantReduksiBaris(matriks m) {
        // matriks sembarang persegi
        for(int j = 0; j < m.cols-1; j++) {     // Pengulangan serong   
            for(int i = 1+j; i < m.rows; i++) { // Pengulangan untuk membuat elemen dibawah tiap serong bernilai 0
                if (m.elmt[i][j] != 0) {
                    double bruh = m.elmt[i][j]; // Variabel bruh merupakan pembagi
                    for (int k = 0; k < m.cols; k++) {  // Membagi tiap elemen di baris i
                        m.elmt[i][k] = m.elmt[i][k] - (bruh/(m.elmt[j][j]))*m.elmt[j][k];
                    }
                }
            }
        }
        // matriks sudah segitiga bawah
        double det = 1.0;   // Variabel tempat menyimpan determinan
        for(int x = 0; x < m.rows; x++) {   // Pengulangan mengali serong matriks ke det
            det *= m.elmt[x][x];
        }
        return det; // Mengembalikan hasil determinan
    }
}
