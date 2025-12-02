package org.devpush.com.aoc2024.day03;

import org.devpush.com.aoc2024.Utils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        firstStar();
        secondStar();
    }

    private static void firstStar() throws IOException {
        List<String> lines = Utils.readInput(false,3,false);
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        int ans =0;
        for (String line:lines){
            if (line.isEmpty()) continue;
            Matcher m = pattern.matcher(line);
            while (m.find()){
                String mul1 = m.group();
//                System.out.println(mul1);
                String[] numbers = mul1.split("\\D+");
                // Convert strings to integers
                int firstDigit = Integer.parseInt(numbers[1]);
                int secondDigit = Integer.parseInt(numbers[2]);
                ans = ans+ (firstDigit*secondDigit);
            }
        }

        System.out.println(ans);
    }

    private static void secondStar() throws IOException{
        List<String> lines = Utils.readInput(false,3,true);
        Pattern pattern = Pattern.compile("(do\\(\\))|(don't\\(\\))|(mul\\(\\d{1,3},\\d{1,3}\\))");
        int ans =0;
        boolean isValid = true;
        for (String line:lines){
            Matcher m = pattern.matcher(line);
            if (line.isEmpty())continue;
            while (m.find()){
                if (m.group(1)!=null){
                    isValid = true;
                }
                if (m.group(2)!=null){
                    isValid = false;
                }
                if (m.group(3)!=null && isValid){
                    String mulfun = m.group(3);
//                    System.out.println(mulfun);
                    String[] nos = mulfun.split("\\D+");
                    ans = ans + (Integer.parseInt(nos[1]) * Integer.parseInt(nos[2]));
                }
            }
        }
        System.out.println(ans);

    }


}
