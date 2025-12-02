package org.devpush.com.aoc2024.day06;

import org.devpush.com.Utils;

import java.io.IOException;
import java.util.List;

public class Day6 {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    public static void main(String[] args) throws IOException {
        firstStar();
    }

    private static void firstStar() throws IOException {
        List<String> lines = Utils.readInput(false, 6, false);
        char[][] matrix = Utils.convertListToMatrix(lines);
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int[][] visited = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (matrix[i][j] == '^') {
                    int temp = DFS(i, j, matrix, NORTH, 1, visited);
                    System.out.println(temp);
                }
            }

        }
    }

    private static int DFS(int row, int col, char[][] matrix, int currentDir, int counter, int[][] visited) {
        int newRow = row;
        int newCol = col;

        switch (currentDir) {
            case NORTH -> newRow = row - 1;
            case SOUTH -> newRow = row + 1;
            case WEST -> newCol = col - 1;
            case EAST -> newCol = col + 1;
        }

        if (Utils.isSafe(newRow, newCol, matrix)) {
            if (matrix[newRow][newCol] == '#') {
                // reset to old pointers
                newRow = row;
                newCol = col;
                if (currentDir == WEST) {
                    currentDir = NORTH;
                } else {
                    currentDir += 1;
                }
                return DFS(newRow, newCol, matrix, currentDir, counter, visited);
            } else {
                if (visited[newRow][newCol] == 0) {
                    counter++;
                    visited[newRow][newCol] = 1;
                }

                return DFS(newRow, newCol, matrix, currentDir, counter, visited);
            }
        } else {
            return counter;
        }
    }


}
