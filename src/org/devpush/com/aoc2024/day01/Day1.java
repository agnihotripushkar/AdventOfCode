package org.devpush.com.aoc2024.day01;

import org.devpush.com.Utils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    public static void main(String[] args) throws IOException {
        firstStar();
        secondStar();
    }

    private static void firstStar() throws IOException {
        List<String> lines = Utils.readInput(false, 1, false);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\d+)[^\\d]+(\\d+)");

        for (String line : lines) {
            if (line.isEmpty()) continue;
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                left.add(Integer.parseInt(m.group(1)));
                right.add(Integer.parseInt(m.group(2)));
            }
        }

        Collections.sort(left);
        Collections.sort(right);

        int res = 0;
        for (int i = 0; i < left.size(); i++) {
            res += Math.abs(left.get(i) - right.get(i));
        }
        System.out.println(res);
    }

    private static void secondStar() throws IOException {
        List<String> lines = Utils.readInput(false, 1, true);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\d+)[^\\d]+(\\d+)");


        for (String line : lines) {
            if (line.isEmpty()) continue;
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                left.add(Integer.parseInt(m.group(1)));
                right.add(Integer.parseInt(m.group(2)));
            }
        }

        Map<Integer, Integer> counter = new HashMap<>();
        for (Integer integer : right) {
            if (counter.containsKey(integer)) {
                counter.compute(integer, (k, val) -> val + 1);
            } else {
                counter.put(integer, 1);
            }
        }

        int similarity = 0;

        for (Integer integer : left) {
            if (counter.containsKey(integer)) {
                similarity = similarity + (integer * counter.get(integer));
            }
        }
        System.out.println(similarity);


    }

}
