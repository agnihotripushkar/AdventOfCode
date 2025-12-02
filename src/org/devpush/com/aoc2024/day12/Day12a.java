package org.devpush.com.aoc2024.day12;

import org.devpush.com.aoc2024.Utils;

import java.io.IOException;
import java.util.*;

public class Day12a {
    public static void main(String[] args) throws IOException {
        firstStar();
    }

    private static void firstStar() throws IOException {
        List<String> lines = Utils.readInput(false, 12, false);
        char[][] arr = Utils.convertListToMatrix(lines);
        int[][] visited = new int[arr.length][arr[0].length];
        int result =0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (visited[i][j] != 1) {
                    Set<int[]> region = new HashSet<>();
                    DFS(i, j, arr, visited, arr[i][j], region);

                    if (!region.isEmpty()) {
                        int peri = calculatePerimeter(region, arr);
                        System.out.println(arr[i][j] + " Area is => " + region.size() + " Perimeter is " + peri);
                        result += ((region.size()) * peri);
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static int calculatePerimeter(Set<int[]> region, char[][] arr) {
        int peri = 0;

        for (int[] item : region) {
            int allborder = 4;
            int x = item[0];
            int y = item[1];

            //up
            if (isSafe1(x - 1, y, arr, arr[x][y])) {
                allborder--;
            }
            //down
            if (isSafe1(x + 1, y, arr, arr[x][y])) {
                allborder--;
            }
            //right
            if (isSafe1(x, y + 1, arr, arr[x][y])) {
                allborder--;
            }
            //left
            if (isSafe1(x, y - 1, arr, arr[x][y])) {
                allborder--;
            }

            peri += allborder;
        }
        return peri;
    }

    private static void DFS(int i, int j, char[][] arr, int[][] visited, char target, Set<int[]> region) {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        region.add(new int[]{i, j});
        visited[i][j] = 1;

        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            if (isSafe(newRow, newCol, arr, target, visited)) {
                DFS(newRow, newCol, arr, visited, arr[newRow][newCol], region);
            }

        }

    }

    private static boolean isSafe(int newRow, int newCol, char[][] grid, char target, int[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;

        return newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                grid[newRow][newCol] == target && visited[newRow][newCol] != 1;
    }

    private static boolean isSafe1(int newRow, int newCol, char[][] grid, char target) {
        int rows = grid.length;
        int cols = grid[0].length;

        return newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                grid[newRow][newCol] == target;
    }

    private static int calulateBulkPerimeter(Set<int[]> region, char[][] arr){
        int peri =0;
        HashMap<Integer,Integer> xmap = new HashMap<>();
        HashMap<Integer,Integer> ymap = new HashMap<>();

        for (int[] item : region){
            xmap.put(item[0], xmap.getOrDefault(item[0],0)+1);
            ymap.put(item[1], ymap.getOrDefault(item[1],0)+1);
        }



        return 0;

    }

}

