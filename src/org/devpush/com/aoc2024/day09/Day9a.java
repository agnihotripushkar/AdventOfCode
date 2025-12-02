package org.devpush.com.aoc2024.day09;

import org.devpush.com.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.devpush.com.day09.Helper.printNBlanksOfTimes;
import static org.devpush.com.day09.Helper.printNNoOfTimes;

public class Day9a {
    public static void main(String[] args) throws IOException {
        FirstStar();
    }

    private static void FirstStar() throws IOException {
        List<String> lines = Utils.readInput(false, 9, false);
        List<Integer> disk = new ArrayList<>();
        boolean space = false;
        int id = 0;
        for (String character : lines.get(0).split("")) {
            int num = Integer.parseInt(character);
            if (space) {
                for (int i = 0; i < num; i++) disk.add(-1);
            } else {
                for (int i = 0; i < num; i++) disk.add(id);
                id++;
            }
            space = !space;
        }

        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == -1) {
                int val = -1;
                while (val == -1) {
                    val = disk.remove(disk.size() - 1);
                }
                if (disk.size() <= i) {
                    disk.add(val);
                    break;
                }
                disk.remove(i);
                disk.add(i, val);
            }
        }

        BigInteger count = BigInteger.ZERO;
        for (int i = 0; i < disk.size(); i++) {
            count = count.add(BigInteger.valueOf(i).multiply(BigInteger.valueOf(disk.get(i))));
        }

        System.out.println(count);
    }


}
