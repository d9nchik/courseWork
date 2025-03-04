package com.d9nich;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final long[][] matrix1 = MultiplicationsOfMatrix.generateMatrix(100, 100);
        final long[][] matrix2 = MultiplicationsOfMatrix.generateMatrix(100, 100);
        MultiplicationsOfMatrix.simpleMultiplication(matrix1, matrix2);
        System.out.println(MultiplicationsOfMatrix.getAddingOperation() + "\t"
                + MultiplicationsOfMatrix.getMultiplicationOperation());
        MultiplicationsOfMatrix.clearOperation();
        MultiplicationsOfMatrix.strassenMultiplication(matrix1, matrix2);
        System.out.println(MultiplicationsOfMatrix.getAddingOperation() + "\t"
                + MultiplicationsOfMatrix.getMultiplicationOperation());
        MultiplicationsOfMatrix.clearOperation();
        MultiplicationsOfMatrix.winogradMultiplication(matrix1, matrix2);
        System.out.println(MultiplicationsOfMatrix.getAddingOperation() + "\t"
                + MultiplicationsOfMatrix.getMultiplicationOperation());
    }

    public static void showMatrix(long[][] matrix) {
        for (long[] doubles : matrix) {
            for (int j = 0; j < doubles.length - 1; j++)
                System.out.printf("%d ", doubles[j]);
            System.out.printf("%d\n", doubles[doubles.length - 1]);
        }
        System.out.println();
    }


    public static boolean checker(long[][] matrix1, long[][] matrix2) {
        return Arrays.deepEquals(matrix1, matrix2);
    }
}
