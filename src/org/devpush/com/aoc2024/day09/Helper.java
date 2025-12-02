package org.devpush.com.aoc2024.day09;

public class Helper {

     static void printNNoOfTimes(StringBuilder sb, int itemToPrint, int noOfTimesToPrint){
        for (int i = 0; i < noOfTimesToPrint; i++) {
            sb.append(itemToPrint);
        }
    }

    static void printNBlanksOfTimes(StringBuilder sb, String itemToPrint, int noOfTimesToPrint){
        for (int i = 0; i < noOfTimesToPrint; i++) {
            sb.append(itemToPrint);
        }
    }
}
