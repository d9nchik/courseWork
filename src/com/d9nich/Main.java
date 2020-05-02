package com.d9nich;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final long[][] matrix1 = MultiplicationsOfMatrix.generateMatrix(65, 65);
        final long[][] matrix2 = MultiplicationsOfMatrix.generateMatrix(65, 65);
        final long[][] matrixSimple = MultiplicationsOfMatrix.simpleMultiplication(matrix1, matrix2);
        System.out.println(MultiplicationsOfMatrix.getAddingOperation() + "\t" + MultiplicationsOfMatrix.getMultiplicationOperation());
        MultiplicationsOfMatrix.clearOperation();
        final long[][] matrixVino = MultiplicationsOfMatrix.winogradMultiplication(matrix1, matrix2);
        System.out.println(MultiplicationsOfMatrix.getAddingOperation() + "\t" + MultiplicationsOfMatrix.getMultiplicationOperation());
        System.out.println(checker(matrixSimple, matrixVino));
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
