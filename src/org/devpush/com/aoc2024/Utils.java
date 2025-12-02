package org.devpush.com.aoc2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> readInput(boolean test, int day, boolean partb) throws IOException {
        String testDir = "";
        if (test) {
            testDir = (partb) ? "test_b" : "test_a";
        } else {
            testDir = (partb) ? "input_b" : "input_a";
        }
        BufferedReader br = new BufferedReader(new FileReader(testDir + "/day" + day + ".txt"));
        String line;
        List<String> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static String readWholeinput(boolean test, int day, boolean partb) throws IOException{
        String testDir = "";
        if (test) {
            testDir = (partb) ? "test_b" : "test_a";
        } else {
            testDir = (partb) ? "input_b" : "input_a";
        }
        BufferedReader reader = new BufferedReader(new FileReader(testDir + "/day" + day + ".txt"));
        // Read the entire file content into a single string
        StringBuilder input = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            input.append(line).append("\n");
        }
        return input.toString();
    }

    public static char[][] convertListToMatrix(List<String> list) {
        if (list == null || list.isEmpty()) {
            return new char[0][0];
        }

        int rows = list.size();
        int cols = list.get(0).length();
        char[][] matrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = list.get(i).toCharArray();
        }

        return matrix;
    }

    public static int[][] convertListToIntMatrix(List<String>list){
        if (list == null || list.isEmpty()) {
            return new int[0][0];
        }

        int rows = list.size();
        int cols = list.get(0).length();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(String.valueOf(list.get(i).charAt(j)));
            }
        }

        return matrix;
    }

    public static boolean isSafe(int row, int col, char[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        if ((row == 0 || col == 0 || row == (rowSize - 1) || col == (colSize - 1)) && matrix[row][col] == '.') {
            return false;
        }

        return (row >= 0 && row < (rowSize) && col >= 0 && col < (colSize));

    }

    public static boolean isInteger(float num) {
        return num == (int) num;
    }

    public static boolean isInteger(long num) {
        return num == (int) num;
    }
}
