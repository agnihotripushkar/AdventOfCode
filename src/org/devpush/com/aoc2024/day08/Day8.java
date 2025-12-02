package org.devpush.com.aoc2024.day08;

import org.devpush.com.Utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8 {
    public static void main(String[] args) throws IOException {
        Firststar();
    }

    private static void Firststar() throws IOException {
        List<String> lines = Utils.readInput(false, 8, false);

        Set<Point> points = new HashSet<>();
        Set<Point> antiPoints = new HashSet<>();

        int height = lines.size();
        int width = lines.get(0).length();

        int y = 0;
        for (String line : lines) {
            int x = 0;
            for (String character : line.split("")) {
                if (!character.equals(".")) {
                    points.add(new Point(character, x, y));
                }
                x++;
            }
            y++;
        }

        for (Point pointA : points) {
            for (Point pointB : points) {
                if (pointA.equals(pointB)) continue;
                Point newPoint = pointA.getAntiPoint(pointB, width, height);
                if (newPoint != null && !points.contains(newPoint)) {
                    antiPoints.add(newPoint);
                }
            }
        }

        System.out.println(antiPoints.size());
    }

}
