package org.devpush.com.aoc2024.day02;

import java.util.List;

public class Helper {

    public boolean isRowSafe(List<Integer> reports){
        boolean isIncreasing = false;
        isIncreasing = reports.get(0) <= reports.get(1);

        if (isIncreasing){
            for (int i = 1; i < reports.size(); i++) {
                int diff = reports.get(i)- reports.get(i-1);
                if (diff>0 && diff <4){
                    continue;
                }
                else {
                    return false;
                }
            }
        }
        else {
            for (int i = 1; i < reports.size(); i++) {
                int diff = reports.get(i-1)- reports.get(i);
                if (diff>0 && diff <4){
                    continue;
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }

}
