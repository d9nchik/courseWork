package com.d9nich;

import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        double[][] matrix1 = {{2, 3, 1}, {3, 2, 2}, {4, 3, 1}};
        showMatrix(matrix1);
        double[][] matrix2 = {{3, 1}, {4, 1}, {5, 1}};
        showMatrix(matrix2);
        showMatrix(simpleMultiplication(matrix1, matrix2));
    }

    public static void showMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            for (int j = 0; j < doubles.length - 1; j++)
                System.out.printf("%.2f ", doubles[j]);
            System.out.println(doubles[doubles.length - 1]);
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
        
    }

    public static int getNewDimension(double[][] matrix1, double[][] matrix2) {
        int maxSize = Collections.max(Arrays.asList(matrix1.length, matrix2.length, matrix2[0].length));
        return 1 << (int) Math.ceil(Math.log10(maxSize) / Math.log10(2));
    }

    public static double[][] toSquareMatrix(double[][] matrix, int size) {
        double[][] squareMatrix = new double[size][size];
        for (int i = 0; i < matrix.length; i++)
            System.arraycopy(matrix[i], 0, squareMatrix[i], 0, matrix[i].length);
        return squareMatrix;
    }
}
