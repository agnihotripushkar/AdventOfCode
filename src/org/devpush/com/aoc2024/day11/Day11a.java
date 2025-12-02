package org.devpush.com.aoc2024.day11;

import org.devpush.com.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day11a {
    public static void main(String[] args) throws IOException {
        firstStar();
    }

    private static void firstStar() throws IOException {
        List<String> lines = Utils.readInput(false, 11, false);
        List<Long> nos = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) continue;
            String[] splitArr = line.trim().split(" ");
            for (String s : splitArr) {
                nos.add(Long.parseLong(s));
            }
        }

        for (int i = 0; i < 75; i++) {
            nos = rules(nos);
        }

        System.out.println(nos.size());

    }

    private static List<Long> rules(List<Long> nos) {
        List<Long> result = new ArrayList<>();
        for (Long current : nos) {
            if (current == 0) {
                result.add(1L);
            } else if ((current.toString().length()) % 2 == 0) {
                int length = current.toString().length();
                String leftHalf = current.toString().substring(0, length / 2);
                String rightHalf = current.toString().substring(length / 2, length);
                result.add(Long.parseLong(leftHalf));
                result.add(Long.parseLong(rightHalf));
            } else {
                result.add(2024 * current);
            }
        }
        return result;
    }
}
