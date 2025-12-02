package org.devpush.com.aoc2024.day13;

import static org.devpush.com.aoc2024.Utils.isInteger;

import org.devpush.com.aoc2024.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13b {
    public static void main(String[] args) throws IOException {
        secondStar();
    }

    private static void secondStar() throws IOException {
        String lines = Utils.readWholeinput(false,13,false);
        long result =0L;
        String[] blocks = lines.split("\\n\\n");

        for (String block: blocks){
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
                float px = (float) (numbers.get(4)+ 10000000000000.0);
                float py = (float) (numbers.get(5) + 10000000000000.0);

                float ca = (px * by - py * bx) / ( ax * by -  ay * bx);
                float cb = (px - ax * ca)/bx;

                System.out.println("ca is "+ca);
                System.out.println("cb is "+cb);

                if (isInteger(ca) && isInteger(cb)){
                    long token = (long) (3*ca + cb);
                    result +=token;
                }
            }

        }
        System.out.println(result);
    }
}