package com.d9nich;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileWork {
    private FileWork() {
    }

    public static long[][] readMatrix(String nameOfFile) {
        File file = new File(nameOfFile);
        if (!file.exists())
            System.out.println("File doesn't exist");
        if (!nameOfFile.matches(".*\\.txt")) {
            System.out.println("File name is incorrect!");
            throw new InputMismatchException("File name is incorrect");
        }
        try (Scanner input = new Scanner(file)) {
            ArrayList<long[]> arrayList = new ArrayList<>();
            while (input.hasNext()) {
                String[] numbers = input.nextLine().split("\\s+");
                long[] temp = new long[numbers.length];
                for (int i = 0; i < numbers.length; i++)
                    temp[i] = Long.parseLong(numbers[i]);
                arrayList.add(temp);
            }
            return arrayList.toArray(new long[0][0]);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            return null;
        }
    }

    public static void writeMatrix(long[][] matrix, String nameOfFile) {
        File file = new File(nameOfFile);
        try (PrintWriter output = new PrintWriter(file)) {
            for (long[] longs : matrix) {
                for (int j = 0; j < longs.length - 1; j++)
                    output.print(longs[j] + "\t");
                output.println(longs[longs.length - 1]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }
}
