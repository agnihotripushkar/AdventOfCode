package org.devpush.com.aoc2024.day14;

import org.devpush.com.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14a {
    public static void main(String[] args) throws IOException {
      firstStar();
    }

    private static void firstStar() throws IOException {
        int roomLength = 101;
        int roomHeight = 103;
        int seconds =100;
        int quad1=0;
        int quad2=0;
        int quad3=0;
        int quad4=0;
        List<String> lines = Utils.readInput(false,14,false);

        for (String line:lines){
            Pattern robot = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");
            Matcher match = robot.matcher(line);
            if (match.find()){
                int px = Integer.parseInt(match.group(1));
                int py = Integer.parseInt(match.group(2));
                int vx = Integer.parseInt(match.group(3));
                int vy = Integer.parseInt(match.group(4));

                BigInteger mx = BigInteger.valueOf(px).add(BigInteger.valueOf(seconds).multiply(BigInteger.valueOf(vx)));
                BigInteger my = BigInteger.valueOf(py).add(BigInteger.valueOf(seconds).multiply(BigInteger.valueOf(vy)));

                BigInteger finalx = mx.mod(BigInteger.valueOf(roomLength));
                BigInteger finaly = my.mod(BigInteger.valueOf(roomHeight));

                int verticalCut = (roomLength-1)/2;
                int horiCut = (roomHeight-1)/2;
                if (finalx.intValue() <verticalCut && finaly.intValue() > horiCut ){
                    quad1++;
                }
                if (finalx.intValue() <verticalCut && finaly.intValue() < horiCut ){
                    quad3++;
                }
                if (finalx.intValue() >verticalCut && finaly.intValue() > horiCut ){
                    quad2++;
                }
                if (finalx.intValue() >verticalCut && finaly.intValue() < horiCut ){
                    quad4++;
                }

            }

        }

        int ans = quad1* quad2 * quad3 * quad4;
        System.out.println(ans);

    }
}