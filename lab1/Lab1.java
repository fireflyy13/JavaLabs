import java.util.Arrays;

public class Lab1 {
    public static void main(String[] args) {
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