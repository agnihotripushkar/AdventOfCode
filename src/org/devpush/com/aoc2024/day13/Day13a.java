package org.devpush.com.aoc2024.day13;

import static org.devpush.com.aoc2024.Utils.isInteger;

import org.devpush.com.aoc2024.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13a {
    public static void main(String[] args) throws IOException {
        firstStar();
    }

    private static void firstStar() throws IOException {
        String lines = Utils.readWholeinput(false,13,false);
        // Split input into blocks based on double newlines
        String[] blocks = lines.split("\\n\\n");
        int result =0;

        // Process each block
        for (String block : blocks) {
            // Find all integers in the block using regex
            Matcher matcher = Pattern.compile("\\d+").matcher(block);
            List<Integer> numbers = new ArrayList<>();
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
            if (numbers.size() == 6) {
                int ax = numbers.get(0);
                int ay = numbers.get(1);
                int bx = numbers.get(2);
                int by = numbers.get(3);
                int px = numbers.get(4);
                int py = numbers.get(5);

                float ca = (float) (px * by - py * bx) / (ax * by - ay * bx);
                float cb = (px - ax * ca)/bx;

                if (isInteger(ca) && isInteger(cb) && ca<=100 && cb<=100){
                    int token = (int) (3*ca + cb);
                    result +=token;
                }

            }
        }

        System.out.println(result);
    }


}