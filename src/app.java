package src;

public class app {
    public static void main(String[] args) {
        matriks mat = new matriks(3,4);
        double A[][] = {{1,-3,7,13},{1,1,1,1},{1,-2,3,4}};
        mat.elmt = A;
        menuSPL.SPLCramer(mat);
    }
}