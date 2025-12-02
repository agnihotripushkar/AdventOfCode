package org.devpush.com.aoc2024.day14;

import org.devpush.com.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14b {
    public static void main(String[] args) throws IOException {
        secondStar();
    }

    private static void secondStar() throws IOException {
        List<String> lines = Utils.readInput(false, 14, false);

        int roomX = 101;
        int roomY = 103;
        Pattern robot = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");

        List<Robot> robots = new ArrayList<>();

        for (String line : lines) {
            Matcher m = robot.matcher(line);
            if (m.find()) {
                int px = Integer.parseInt(m.group(1));
                int py = Integer.parseInt(m.group(2));
                int vx = Integer.parseInt(m.group(3));
                int vy = Integer.parseInt(m.group(4));
                robots.add(new Robot(px, py, vx, vy, 0));
            }
        }

        for (long i = 0; i < 1_000_001L; i++) {
            boolean[] map = new boolean[roomX * roomY];

            for (Robot b : robots) {
                b.move();
                int ty = b.getY();
                int tx = b.getX();
                map[ty * roomX + tx] = true;
            }
            int count = 0;
            for (int y = 0; y < roomY; y++) {
                for (int x = 0; x < 50; x++) {
                    if (map[y * roomX + x] && map[y * roomX + ((roomX - 1) - x)]) {
                        count++;
                    }
                }
            }

            if (count < 39) continue;
            System.out.println(count);
            System.out.println(i + 1);

            BufferedWriter bw = new BufferedWriter(new FileWriter("data/" + (i + 1) + ".txt"));
            for (int y = 0; y < roomY; y++) {
                for (int x = 0; x < roomX; x++) {
                    bw.write(map[y * roomX + x] ? "#" : ".");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }

    }
}