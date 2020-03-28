package com.d9nich;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        double[][] matrix1 = generateMatrix(2042, 500);
        double[][] matrix2 = generateMatrix(500, 2048);
        System.out.println(checker(subtract(toSquareMatrix(simpleMultiplication(matrix1, matrix2), 2048), strassenMultiplication(matrix1, matrix2))));
    }

    public static void showMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            for (int j = 0; j < doubles.length - 1; j++)
                System.out.printf("%.2f ", doubles[j]);
            System.out.printf("%.2f\n", doubles[doubles.length - 1]);
        }
        System.out.println();
    }

    public static double[][] simpleMultiplication(double[][] matrix1, double[][] matrix2) {
        double[][] resultMatrix = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++)
            for (int j = 0; j < matrix2[0].length; j++)
                for (int k = 0; k < matrix2.length; k++)
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
        return resultMatrix;
    }

    public static double[][] strassenMultiplication(double[][] matrix1, double[][] matrix2) {
        int dimension = getNewDimension(matrix1, matrix2);
        return realMultiStrassen(toSquareMatrix(matrix1, dimension), toSquareMatrix(matrix2, dimension));
    }

    private static double[][] realMultiStrassen(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length <= 64)
            return simpleMultiplication(matrix1, matrix2);

        int n = matrix1.length >> 1;

        double[][] a11 = new double[n][n];
        double[][] a12 = new double[n][n];
        double[][] a21 = new double[n][n];
        double[][] a22 = new double[n][n];

        double[][] b11 = new double[n][n];
        double[][] b12 = new double[n][n];
        double[][] b21 = new double[n][n];
        double[][] b22 = new double[n][n];

        splitMatrix(matrix1, a11, a12, a21, a22);
        splitMatrix(matrix2, b11, b12, b21, b22);

        double[][] p1 = realMultiStrassen(add(a11, a22), add(b11, b22));
        double[][] p2 = realMultiStrassen(add(a21, a22), b11);
        double[][] p3 = realMultiStrassen(a11, subtract(b12, b22));
        double[][] p4 = realMultiStrassen(a22, subtract(b21, b11));
        double[][] p5 = realMultiStrassen(add(a11, a12), b22);
        double[][] p6 = realMultiStrassen(subtract(a21, a11), add(b11, b12));
        double[][] p7 = realMultiStrassen(subtract(a12, a22), add(b21, b22));

        double[][] c11 = add(add(p1, p4), subtract(p7, p5));
        double[][] c12 = add(p3, p5);
        double[][] c21 = add(p2, p4);
        double[][] c22 = add(subtract(p1, p2), add(p3, p6));
        return joinMatrix(c11, c12, c21, c22);
    }

    private static int getNewDimension(double[][] matrix1, double[][] matrix2) {
        int maxSize = Collections.max(Arrays.asList(matrix1.length, matrix2.length, matrix2[0].length));
        return 1 << (int) Math.ceil(Math.log10(maxSize) / Math.log10(2));
    }

    private static double[][] toSquareMatrix(double[][] matrix, int size) {
        double[][] squareMatrix = new double[size][size];
        for (int i = 0; i < matrix.length; i++)
            System.arraycopy(matrix[i], 0, squareMatrix[i], 0, matrix[i].length);
        return squareMatrix;
    }

    private static void splitMatrix(double[][] matrix, double[][] a11, double[][] a12, double[][] a21, double[][] a22) {
        int n = matrix.length >> 1;
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, a11[i], 0, n);
            System.arraycopy(matrix[i], n, a12[i], 0, n);
            System.arraycopy(matrix[i + n], n, a21[i], 0, n);
            System.arraycopy(matrix[i + n], n, a22[i], 0, n);
        }
    }

    private static double[][] joinMatrix(double[][] a11, double[][] a12, double[][] a21, double[][] a22) {
        int n = a11.length;
        double[][] a = new double[n << 1][n << 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(a11[i], 0, a[i], 0, n);
            System.arraycopy(a12[i], 0, a[i], n, n);
            System.arraycopy(a21[i], 0, a[i + n], 0, n);
            System.arraycopy(a22[i], 0, a[i + n], n, n);
        }
        return a;
    }

    private static double[][] subtract(double[][] matrix1, double[][] matrix2) {
        double[][] matrix3 = new double[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++)
            for (int j = 0; j < matrix1[i].length; j++)
                matrix3[i][j] = matrix1[i][j] - matrix2[i][j];
        return matrix3;
    }

    private static double[][] add(double[][] matrix1, double[][] matrix2) {
        double[][] matrix3 = new double[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++)
            for (int j = 0; j < matrix1[i].length; j++)
                matrix3[i][j] = matrix1[i][j] + matrix2[i][j];
        return matrix3;
    }

    public static double[][] generateMatrix(int n, int m) {
        double[][] matrix = new double[n][m];
        Random random = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = random.nextInt(1);
        return matrix;
    }

    public static boolean checker(double[][] matrix) {
        for (double[] doubles : matrix)
            for (double aDouble : doubles)
                if (aDouble != 0)
                    return false;
        return true;
    }
}
