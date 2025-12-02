package org.devpush.com.aoc2024.day05;

import org.devpush.com.aoc2024.Utils;

import java.io.IOException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws IOException {
        firstStar();
        secondStar();
    }

    private static void firstStar() throws IOException {
        List<String> lines = Utils.readInput(false, 5, false);
        Map<String, List<String>> order = new HashMap<>();
        int result = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            if (line.contains("|")) {
                String[] arr = line.split("\\|");
                if (!order.containsKey(arr[0])) {
                    order.put(arr[0], new ArrayList<>());
                }
                order.get(arr[0]).add(arr[1]);
            }
            if (line.contains(",")) {
                Map<String, Integer> ordrLineCheck = new HashMap<>();
                String[] nos = line.split(",");
                for (int i = 0; i < nos.length; i++) {
                    ordrLineCheck.put(nos[i], i);
                }

                boolean correct = true;
                for (Map.Entry<String, List<String>> entry : order.entrySet()) {
                    if (ordrLineCheck.containsKey(entry.getKey())) {
                        int start = ordrLineCheck.get(entry.getKey());
                        for (String s : entry.getValue()) {
                            if (ordrLineCheck.containsKey(s) && start > ordrLineCheck.get(s)) {
                                correct = false;
                                break;
                            }
                        }
                    }
                }
                if (correct) {
                    result += Integer.parseInt(nos[nos.length / 2]);
                }

            }
        }
        System.out.println(result);

    }

    private static void secondStar() throws IOException {
        List<String> lines = Utils.readInput(false, 5, true);
        Map<String, List<String>> order = new HashMap<>();
        int result = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            if (line.contains("|")) {
                String[] arr = line.split("\\|");
                if (!order.containsKey(arr[0])) {
                    order.put(arr[0], new ArrayList<>());
                }
                order.get(arr[0]).add(arr[1]);
            }
            if (line.contains(",")) {

                String[] splitArr = line.split(",");
                List<String> before = Arrays.asList(splitArr);
                List<String> after = new ArrayList<>();
                after.addAll(Arrays.asList(splitArr));

                after.sort((a, b) -> {
                    // return 0 means no change in a,b
                    if (!order.containsKey(a)) return 0;
                    List<String> right = order.get(a);
                    if (right.contains(b)) return -1;
                    // return -1 means swap a and b
                    return 0;
                });

                if (!before.toString().equals(after.toString())) {
                    result += Integer.parseInt(after.get(after.size() / 2));
                }
            }
        }
        System.out.println(result);
    }
}
