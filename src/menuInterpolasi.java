package src;

import java.io.*;
import java.util.*;

public class menuInterpolasi {
    public static void solveInterpolasi() {
        try (Scanner points_input = new Scanner(System.in)) {
            int points = points_input.nextInt();
            matriks point_array = new matriks(points, 4);
            Scanner x_y_input = new Scanner(System.in);
            for (int i = 0; i < points; i++) {
                point_array.elmt[i][0] = 1;
                point_array.elmt[i][1] = x_y_input.nextDouble();
                point_array.elmt[i][2] = Math.pow(point_array.elmt[i][1], 2.0);
                point_array.elmt[i][3] = x_y_input.nextDouble();
            }
            x_y_input.close();
            point_array.displayMatriks();
        }
    }
}
