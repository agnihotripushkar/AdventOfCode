package org.devpush.com.aoc2025.day6

import org.devpush.com.aoc2025.readInput
import java.math.BigInteger
import kotlin.io.path.Path
import kotlin.io.path.readLines


fun main() {

    fun convertListToIntMatrix(test: Boolean, day: Int, partb: Boolean): Pair<Array<IntArray>, List<String>> {
        val testDir = if (test) {
            if (partb) "aoc2025/test_b" else "aoc2025/test_a"
        } else {
            if (partb) "aoc2025/input_b" else "aoc2025/input_a"
        }
        val fileName = "$testDir/day$day.txt"
        val list = Path(fileName).readLines().toMutableList()

        if (list.isEmpty()) {
            return Pair(Array(0) { IntArray(0) }, emptyList())
        }

        val operatorLine = list.removeAt(list.lastIndex)
        val operators = operatorLine.trim().split("\\s+".toRegex())

        val rows = list.size
        val split = list[0].trim().split("\\s+".toRegex())
        val cols = split.size
        val matrix = Array(rows) { IntArray(cols) }

        for (i in 0..<rows) {
            val row = list[i].trim().split("\\s+".toRegex()).map { it.trim().toInt() }
            for (j in 0..<row.size) {
                matrix[i][j] = row[j]
            }
        }

        return Pair(matrix, operators)
    }

    fun part1(): Long {
        // Implement the logic for part 1 here
        val (matrix, operators) = convertListToIntMatrix(false, 6, false)
        val rows = matrix.size
        val cols = matrix[0].size
        var totalSum = 0L

        for (j in 0 until cols) {
            val operator = operators[j]
            var colResult = matrix[0][j].toLong()

            for (i in 1 until rows) {
                val currentVal = matrix[i][j]
                if (operator == "*") {
                    colResult *= currentVal
                } else if (operator == "+") {
                    colResult += currentVal
                }
            }
            totalSum += colResult
        }

        return totalSum
    }

    fun part2(): BigInteger {
        val lines = readInput(false, 6, true)
        val map = HashMap<Int, ArrayList<String>>()

        for (line in lines) {
            if (line.isEmpty()) {
                continue
            }
            val arr = line.split("".toRegex())
            for (i in arr.indices) {
                if (!map.containsKey(i)) {
                    map[i] = ArrayList()
                }
                map[i]?.add(arr[i])
            }
        }

        var intSum = BigInteger.ZERO
        var sum = BigInteger.ZERO
        var isMul = false

        for (ent in map.values) {
            if (ent.last() == "+") {
                isMul = false
                intSum = BigInteger.ZERO
            } else if (ent.last() == "*") {
                isMul = true
                intSum = BigInteger.ZERO
            }

            var strVal = ""
            for (i in 0..<ent.size - 1) {
                strVal += ent[i]
            }

            if (strVal.isBlank()) {
                sum = sum.add(intSum);
                continue;
            }

            val value = BigInteger(strVal.trim());
            intSum = if (isMul) {
                if (intSum.equals(BigInteger.ZERO)) {
                    value
                } else {
                    intSum.multiply(value)
                }
            } else {
                intSum.add(value)
            }
        }

        return sum

    }

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")

}
