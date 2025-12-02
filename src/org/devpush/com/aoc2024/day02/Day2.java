package org.devpush.com.aoc2024.day02;

import org.devpush.com.aoc2024.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {
    static int safeReports =0;
    public static void main(String[] args) throws IOException {
        List<List<Integer>> ipForSecond = firstStar();
        secondStar(ipForSecond);
    }

    private static List<List<Integer>> firstStar() throws IOException {
        List<String> lines = Utils.readInput(false,2,false);
        Pattern pattern = Pattern.compile("(\\d+)");
        List<List<Integer>> unSafeReportsList = new ArrayList<>();
        for (String line:lines){
            if (line.isEmpty())continue;
            List<Integer> reports = new ArrayList<>();
            Matcher m = pattern.matcher(line);
            while (m.find()){
                reports.add(Integer.parseInt(m.group()));
            }
//            System.out.println(Arrays.toString(reports.toArray()));
            Helper helper = new Helper();
            if (helper.isRowSafe(reports)){
                safeReports++;
            }
            else {
                unSafeReportsList.add(reports);
            }
        }
        System.out.println(safeReports);
        return unSafeReportsList;

    }

    private static void secondStar(List<List<Integer>> unSafeReportList)  {
        for (List<Integer> report:unSafeReportList){
            for (int i = 0; i < report.size(); i++) {
                List<Integer> modifiedList = new ArrayList<>(report);
                modifiedList.remove(i);
                Helper helper = new Helper();
                if (helper.isRowSafe(modifiedList)){
                    safeReports++;
                    break;
                }
            }
        }
        System.out.println(safeReports);

    }


}
