package com.d9nich;

import java.util.Arrays;

import static com.d9nich.MultiplicationsOfMatrix.generateMatrix;

public class Main {

    public static void main(String[] args) {
        long[][] matrix1 = generateMatrix(3000, 1000);
        long[][] matrix2 = generateMatrix(1000, 3000);
        System.out.println(checker(MultiplicationsOfMatrix.simpleMultiplication(matrix1, matrix2), MultiplicationsOfMatrix.strassenMultiplication(matrix1, matrix2)));
    }

    public static void showMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            for (int j = 0; j < doubles.length - 1; j++)
                System.out.printf("%.2f ", doubles[j]);
            System.out.printf("%.2f\n", doubles[doubles.length - 1]);
        }
        System.out.println();
    }


    public static boolean checker(long[][] matrix1, long[][] matrix2) {
        return Arrays.deepEquals(matrix1, matrix2);
    }
}
