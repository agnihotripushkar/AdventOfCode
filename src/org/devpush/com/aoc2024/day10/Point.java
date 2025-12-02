package org.devpush.com.aoc2024.day10;

import java.util.Objects;

public class Point {
    private int rating;
    private int x;
    private int y;

    public Point(int rating, int x, int y) {
        this.rating = rating;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
