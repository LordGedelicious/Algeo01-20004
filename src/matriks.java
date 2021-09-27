package src;

public class matriks {
    public int rows;
    public int cols;
    public double [][] elmt;

    public matriks(int m, int n){
        this.rows = m;
        this.cols = n;
        this.elmt = new double[m][n];
        for(int i=0; i<this.rows; i++) for(int j=0; j<this.cols; j++) elmt[i][j] = 0;
    }

    public int getLastIdxRow(){
        return this.rows - 1;
    }

    public int getLastIdxCol(){
        return this.cols - 1;
    }

    public void displayMatriks(){
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                System.out.print(this.elmt[i][j]);
                System.out.print(" ");
            }
            System.out.println("\n");
        }
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

    public static matriks multiplyConst(matriks m, int x){
        matriks mm = new matriks(m.cols, m.rows);
        for(int i=0; i<m.rows; i++){
            for(int j=0; j<m.cols; j++){
                mm.elmt[i][j] *= x;
            }
        }
        return mm;
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

    public static boolean isSquare(matriks m){
        return m.rows == m.cols;
    }

    public static int countElement(matriks m){
        return m.rows * m.cols;
    }
}