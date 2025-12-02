package org.devpush.com.aoc2024.day10;

import org.devpush.com.aoc2024.Utils;

import java.io.IOException;
import java.util.*;

public class Day10b {
    public static void main(String[] args) throws IOException {
        secondStar();
    }

    private static void secondStar() throws IOException {
        List<String> lines = Utils.readInput(false, 10, true);

        int height = lines.size();
        int width = lines.get(0).length();

        int[][] map = Utils.convertListToIntMatrix(lines);

        long count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Map<Point, Integer> set = countTrails(map, x, y, width, height, 0);
                for (Map.Entry<Point, Integer> entry : set.entrySet()) {
                    count += entry.getValue();
                }

            }
        }
        System.out.println(count);
    }

    private static Map<Point, Integer> countTrails(int[][] map, int x, int y, int width, int height, int val) {
        Map<Point, Integer> newSet = new HashMap<>();
        if (isSafe(map, x, y)) {
            if (map[x][y] != val) return new HashMap<>();
            if (val == 9) {
                newSet.put(new Point(1, x, y), 1);
                return newSet;
            }
            Map<Point, Integer> res1 = countTrails(map, x + 1, y, width, height, val + 1);
            for (Point p : res1.keySet()) {
                if (newSet.containsKey(p)) {
                    newSet.put(p, newSet.get(p) + res1.get(p));
                } else {
                    newSet.put(p, res1.get(p));
                }
            }

            res1 = countTrails(map, x - 1, y, width, height, val + 1);
            for (Point p : res1.keySet()) {
                if (newSet.containsKey(p)) {
                    newSet.put(p, newSet.get(p) + res1.get(p));
                } else {
                    newSet.put(p, res1.get(p));
                }
            }

            res1 = countTrails(map, x, y + 1, width, height, val + 1);
            for (Point p : res1.keySet()) {
                if (newSet.containsKey(p)) {
                    newSet.put(p, newSet.get(p) + res1.get(p));
                } else {
                    newSet.put(p, res1.get(p));
                }
            }
            res1 = countTrails(map, x, y - 1, width, height, val + 1);
            for (Point p : res1.keySet()) {
                if (newSet.containsKey(p)) {
                    newSet.put(p, newSet.get(p) + res1.get(p));
                } else {
                    newSet.put(p, res1.get(p));
                }
            }
            return newSet;
        } else return newSet;
    }

    private static boolean isSafe(int[][] map, int x, int y) {
        int height = map.length;
        int width = map[0].length;
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
