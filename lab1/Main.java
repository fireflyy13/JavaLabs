import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int n = 17;
        int c_5 = n % 5;
        int c_7 = n % 7;
        int c_11 = n % 11;

        System.out.println("c_5 = " + c_5);
        System.out.println("c_7 = " + c_7);
        System.out.println("c_11 = " + c_11);

        int[][] a = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int[][] b = {
                {9, 3, 5, 1},
                {9, 1, 1, 3},
                {6, 3, 4, 1},
                {0, 3, 5, 2}
        };

        System.out.println(Arrays.deepToString(a));

        try {
            ChangeMatrix.matrixOperations(a, b);
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непередбачена помилка: " + e.getMessage());
        }

    }
}

class ChangeMatrix {
    public static void matrixOperations(int[][] matrix1, int[][] matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new IllegalArgumentException("Матриці не можуть бути null!");
        }

        if (matrix1.length == 0 || matrix2.length == 0) {
            throw new IllegalArgumentException("Матриці не можуть бути порожніми!");
        }

        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            throw new IllegalArgumentException("Матриці повинні бути однакового розміру!");
        }

        int[][] C = new int[matrix1.length][matrix1[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                C[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        System.out.println("A = " + Arrays.deepToString(matrix1));
        System.out.println("B = " + Arrays.deepToString(matrix2));
        System.out.println("C = " + Arrays.deepToString(C));

        int sumMax = 0;
        int sumMin = 0;

        for (int j = 0; j < C[0].length; j++) {
            int max = C[0][j];
            int min = C[0][j];
            for (int i = 0; i < C.length; i++) {
                if (C[i][j] > max) {
                    max = C[i][j];
                }
                if (C[i][j] < min) {
                    min = C[i][j];
                }
            }
            if ((j + 1) % 2 == 0) {
                sumMax += max;
            } else {
                sumMin += min;
            }
        }
        System.out.println("Сума найбільших елементів у парних стовпцях = " + sumMax);
        System.out.println("Сума найменших елементів у непарних стовпцях = " + sumMin);
    }
}