package src;

import java.util.ArrayList;

public class matriks {
    /* --- KONSTRUKTOR --- */
    public int rows;
    public int cols;
    public double [][] elmt;

    public matriks(int m, int n){
        this.rows = m;
        this.cols = n;
        this.elmt = new double[m][n];
        for(int i = 0; i < this.rows; i++) for(int j = 0; j < this.cols; j++) elmt[i][j] = 0;
    }

    public matriks(int m, int n, ArrayList<ArrayList<Double>> e){
        this.rows = m;
        this.cols = n;
        this.elmt = new double[m][n];
        for(int i = 0; i < this.rows; i++) for(int j = 0; j < this.cols; j++) {
            this.elmt[i][j] = e.get(i).get(j);
        }
    }

    /* --- SELEKTOR --- */
    public boolean isIdxValid(int i, int j){
        return ((0 <= i) && (i <= getLastIdxRow()) && (0 <= j) && (j <= getLastIdxCol()));
    }

    public int getLastIdxRow(){
        return this.rows - 1;
    }

    public int getLastIdxCol(){
        return this.cols - 1;
    }

    /* --- INPUT/OUTPUT --- */
    public void displayMatriks(){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                this.elmt[i][j] += 0;
                System.out.printf("%.6f ", this.elmt[i][j]);
                System.out.print(" ");
            }
            System.out.println("\n");
        }
    }

//    public void readMatriks(){
//
//    }

    /* --- OPERASI MATRIKS --- */
    public static matriks transpose(matriks m){
        matriks mm = new matriks(m.cols, m.rows);
        for(int i = 0; i < mm.rows; i++){
            for(int j = 0; j < mm.cols; j++){
                mm.elmt[i][j] = m.elmt[j][i];
            }
        }
        return mm;
    }

    public static matriks multiplyMatriks(matriks m1, matriks m2){
        matriks m = new matriks(m1.rows, m2.cols);
        for(int i=0; i<m.rows; i++){
            for(int j=0; j<m.cols; j++){
                for(int k=0; k<m1.cols; k++){
                    m.elmt[i][j] += m1.elmt[i][k] * m2.elmt[k][j];
                }
            }
        }
        return m;
    }

    public static matriks addMatriks(matriks m1, matriks m2){
        matriks m = new matriks(m1.rows, m1.cols);
        for(int i=0; i<m1.rows; i++){
            for(int j=0; j<m1.cols; j++){
                m.elmt[i][j] = m1.elmt[i][j] + m2.elmt[i][j];
            }
        }
        return m;
    }

    public static matriks substractMatriks(matriks m1, matriks m2){
        matriks m = new matriks(m1.rows, m1.cols);
        for(int i=0; i<m1.rows; i++){
            for(int j=0; j<m1.cols; j++){
                m.elmt[i][j] = m1.elmt[i][j] - m2.elmt[i][j];
            }
        }
        return m;
    }

    public static matriks multiplyConst(matriks m, double x){
        for(int i=0; i<m.rows; i++){
            for(int j=0; j<m.cols; j++){
                m.elmt[i][j] *= x;
            }
        }
        return m;
    }

    public static matriks divideConst(matriks m, double x){
        for(int i=0; i<m.rows; i++){
            for(int j=0; j<m.cols; j++) m.elmt[i][j] /= x;
        }
        return m;
    }

    public static int countElement(matriks m){
        return (m.rows * m.cols);
    }

    public static matriks minor(matriks m, int row, int col){
        matriks mnr = new matriks(m.rows - 1, m.cols - 1);
        int curRow = 0, curCol = 0;
        for(int i = 0; i < m.rows; i++){
            for(int j = 0; j < m.cols; j++){
                if(i != row && j != col){
                    mnr.elmt[curRow][curCol] = m.elmt[i][j];
                    curCol++;
                    if(curCol == m.cols - 1){
                        curCol = 0;
                        curRow++;
                    }
                }
            }
        }
        return mnr;
    }

    /* --- OPERASI BARIS ELEMENTER --- */
    public void rowSwap(int i, int j){
        if(isIdxValid(i, j)){
            for(int k=0; k < this.cols; k++){
                double tmp = this.elmt[i][k];
                this.elmt[j][k] = this.elmt[i][k];
                this.elmt[i][k] = tmp;
            }
        }
    }

    public void rowMultiply(int i, double x){
        if((0 <= i) && (i <= this.rows)){
            for(int k=0; k < this.cols; k++) this.elmt[i][k] *= x;
        }
    }

    public  void rowPlus(int i, int j){
        if(isIdxValid(i, j)){
            for(int k=0; k < this.cols; k++) this.elmt[i][k] += this.elmt[j][k];
        }
    }

    public void rowMinus(int i, int j){
        if(isIdxValid(i, j)){
            for(int k=0; k < this.cols; k++) this.elmt[i][k] -= this.elmt[j][k];
        }
    }

    /*--- PREDIKAT --- */
    public static boolean isSquare(matriks m){
        return (m.rows == m.cols);
    }

    public static boolean isEqual(matriks m1, matriks m2){
        if(m1.rows != m2.rows) return false;
        if(m1.cols != m2.cols) return false;
        for(int i=0; i<m1.rows; i++) {
            for (int j = 0; j < m1.cols; j++) {
                if(m1.elmt[i][j] != m2.elmt[i][j]) return false;
            }
        }
        return true;
    }

    public static boolean isIdentity(matriks m){
        if(!isSquare(m)) return false;
        for(int i=0;i<m.rows;i++){
            for(int j=0;j<m.cols;j++){
                if((i == j) && (m.elmt[i][j] != 1)) return false;
                if((i != j) && (m.elmt[i][j] != 0)) return false;
            }
        }
        return true;
    }

}