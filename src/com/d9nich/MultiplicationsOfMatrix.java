package com.d9nich;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MultiplicationsOfMatrix {
    private static long addingOperation;
    private static long multiplicationOperation;

    private MultiplicationsOfMatrix() {
    }

    public static long[][] simpleMultiplication(long[][] matrix1, long[][] matrix2) {
        long[][] resultMatrix = new long[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++)
            for (int j = 0; j < matrix2[0].length; j++)
                for (int k = 0; k < matrix2.length; k++)
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
        long addingAndMultiplicationUsage = ((long) matrix1.length) * matrix2[0].length * matrix2.length;
        addingOperation += addingAndMultiplicationUsage;
        multiplicationOperation += addingAndMultiplicationUsage;
        return resultMatrix;
    }

    public static long[][] strassenMultiplication(long[][] matrix1, long[][] matrix2) {
        int dimension = getNewDimension(matrix1, matrix2);
        long[][] resultMatrix = realMultiStrassen(toSquareMatrix(matrix1, dimension), toSquareMatrix(matrix2, dimension));
        long[][] output = new long[matrix1.length][matrix2[0].length];
        for (int i = 0; i < output.length; i++)
            System.arraycopy(resultMatrix[i], 0, output[i], 0, output[0].length);
        return output;
    }

    public static long[][] winogradMultiplication(long[][] matrix1, long[][] matrix2) {
        int dimension = getNewDimension(matrix1, matrix2);
        long[][] resultMatrix = realMultiWinograd(toSquareMatrix(matrix1, dimension), toSquareMatrix(matrix2, dimension));
        long[][] output = new long[matrix1.length][matrix2[0].length];
        for (int i = 0; i < output.length; i++)
            System.arraycopy(resultMatrix[i], 0, output[i], 0, output[0].length);
        return output;
    }

    private static long[][] realMultiWinograd(long[][] matrix1, long[][] matrix2) {
        if (matrix1.length <= 64)
            return simpleMultiplication(matrix1, matrix2);

        int n = matrix1.length >> 1;

        long[][] a11 = new long[n][n];
        long[][] a12 = new long[n][n];
        long[][] a21 = new long[n][n];
        long[][] a22 = new long[n][n];

        long[][] b11 = new long[n][n];
        long[][] b12 = new long[n][n];
        long[][] b21 = new long[n][n];
        long[][] b22 = new long[n][n];


        splitMatrix(matrix1, a11, a12, a21, a22);
        splitMatrix(matrix2, b11, b12, b21, b22);

        long[][] s1 = add(a21, a22);
        long[][] s2 = subtract(s1, a11);
        long[][] s3 = subtract(a11, a21);
        long[][] s4 = subtract(a12, s2);
        long[][] s5 = subtract(b12, b11);
        long[][] s6 = subtract(b22, s5);
        long[][] s7 = subtract(b22, b12);
        long[][] s8 = subtract(s6, b21);

        long[][] p1 = realMultiWinograd(s2, s6);
        long[][] p2 = realMultiWinograd(a11, b11);
        long[][] p3 = realMultiWinograd(a12, b21);
        long[][] p4 = realMultiWinograd(s3, s7);
        long[][] p5 = realMultiWinograd(s1, s5);
        long[][] p6 = realMultiWinograd(s4, b22);
        long[][] p7 = realMultiWinograd(a22, s8);

        long[][] t1 = add(p1, p2);
        long[][] t2 = add(t1, p4);

        long[][] c11 = add(p2, p3);
        long[][] c12 = add(t1, add(p5, p6));
        long[][] c21 = subtract(t2, p7);
        long[][] c22 = add(t2, p5);
        return joinMatrix(c11, c12, c21, c22);
    }

    private static long[][] realMultiStrassen(long[][] matrix1, long[][] matrix2) {
        if (matrix1.length <= 64)
            return simpleMultiplication(matrix1, matrix2);

        int n = matrix1.length >> 1;

        long[][] a11 = new long[n][n];
        long[][] a12 = new long[n][n];
        long[][] a21 = new long[n][n];
        long[][] a22 = new long[n][n];

        long[][] b11 = new long[n][n];
        long[][] b12 = new long[n][n];
        long[][] b21 = new long[n][n];
        long[][] b22 = new long[n][n];

        splitMatrix(matrix1, a11, a12, a21, a22);
        splitMatrix(matrix2, b11, b12, b21, b22);

        long[][] p1 = realMultiStrassen(add(a11, a22), add(b11, b22));
        long[][] p2 = realMultiStrassen(add(a21, a22), b11);
        long[][] p3 = realMultiStrassen(a11, subtract(b12, b22));
        long[][] p4 = realMultiStrassen(a22, subtract(b21, b11));
        long[][] p5 = realMultiStrassen(add(a11, a12), b22);
        long[][] p6 = realMultiStrassen(subtract(a21, a11), add(b11, b12));
        long[][] p7 = realMultiStrassen(subtract(a12, a22), add(b21, b22));

        long[][] c11 = add(add(p1, p4), subtract(p7, p5));
        long[][] c12 = add(p3, p5);
        long[][] c21 = add(p2, p4);
        long[][] c22 = add(subtract(p1, p2), add(p3, p6));
        return joinMatrix(c11, c12, c21, c22);
    }

    private static int getNewDimension(long[][] matrix1, long[][] matrix2) {
        int maxSize = Collections.max(Arrays.asList(matrix1.length, matrix2.length, matrix2[0].length));
        return 1 << (int) Math.ceil(Math.log10(maxSize) / Math.log10(2));
    }

    private static long[][] toSquareMatrix(long[][] matrix, int size) {
        long[][] squareMatrix = new long[size][size];
        for (int i = 0; i < matrix.length; i++)
            System.arraycopy(matrix[i], 0, squareMatrix[i], 0, matrix[i].length);
        return squareMatrix;
    }

    private static void splitMatrix(long[][] matrix, long[][] a11, long[][] a12, long[][] a21, long[][] a22) {
        int n = matrix.length >> 1;
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, a11[i], 0, n);
            System.arraycopy(matrix[i], n, a12[i], 0, n);
            System.arraycopy(matrix[i + n], 0, a21[i], 0, n);
            System.arraycopy(matrix[i + n], n, a22[i], 0, n);
        }
    }

    private static long[][] joinMatrix(long[][] a11, long[][] a12, long[][] a21, long[][] a22) {
        int n = a11.length;
        long[][] a = new long[n << 1][n << 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(a11[i], 0, a[i], 0, n);
            System.arraycopy(a12[i], 0, a[i], n, n);
            System.arraycopy(a21[i], 0, a[i + n], 0, n);
            System.arraycopy(a22[i], 0, a[i + n], n, n);
        }
        return a;
    }

    private static long[][] subtract(long[][] matrix1, long[][] matrix2) {
        long[][] matrix3 = new long[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++)
            for (int j = 0; j < matrix1[i].length; j++)
                matrix3[i][j] = matrix1[i][j] - matrix2[i][j];
        addingOperation += matrix1.length * matrix1[0].length;
        return matrix3;
    }

    private static long[][] add(long[][] matrix1, long[][] matrix2) {
        long[][] matrix3 = new long[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++)
            for (int j = 0; j < matrix1[i].length; j++) {
                matrix3[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        addingOperation += matrix1.length * matrix1[0].length;
        return matrix3;
    }

    public static long[][] generateMatrix(int n, int m) {
        long[][] matrix = new long[n][m];
        Random random = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = random.nextInt(10_000);
        return matrix;
    }

    public static long getAddingOperation() {
        return addingOperation;
    }

    public static void clearOperation() {
        MultiplicationsOfMatrix.addingOperation = 0;
        MultiplicationsOfMatrix.multiplicationOperation = 0;
    }

    public static long getMultiplicationOperation() {
        return multiplicationOperation;
    }
}
