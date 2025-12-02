package org.devpush.com.aoc2024.day07;

import org.devpush.com.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day7A {
    public static void main(String[] args) throws IOException {
        FirstStar();
    }

    private static void FirstStar() throws IOException {
        List<String> lines = Utils.readInput(false, 7, true);
        long result = 0;

        for (String line : lines) {
            if (line.isEmpty()) continue;
            String[] splitArr = line.split(":");
            long target = Long.parseLong(splitArr[0]);
            String left = splitArr[1].strip();
            String[] leftSide = left.split(" ");
            int[] operands = Arrays.stream(leftSide)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            boolean canReach = canReachTarget(operands, target);
            if (canReach){
                result +=target;
            }
        }
        System.out.println(result);
    }

    public static boolean canReachTarget(int[] nums, long target) {
        return backtrack(nums, 0, 0, target);
    }

    private static boolean backtrack(int[] nums, int index, long currentSum, long target) {
        if (index == nums.length) {
            return currentSum == target;
        }

        // Try adding the current number
        if (backtrack(nums, index + 1, currentSum + nums[index], target)) {
            return true;
        }

        // Try multiplying the current number
        if (backtrack(nums, index + 1, currentSum * nums[index], target)) {
            return true;
        }

        if (backtrack(nums,index+1, Long.parseLong(currentSum+String.valueOf(nums[index])),target))
        {
            return true;
        }

        return false;
    }
}
