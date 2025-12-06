package org.devpush.com.aoc2025.day5

import org.devpush.com.aoc2025.readInput

fun main() {

    data class Range(val start: Long, val end: Long)

    fun part1(): Int {
        val input1 = readInput(false, 5, false)
        val ranges = mutableListOf<Range>()
        var freshCount = 0
        var parsingRanges = true

        for (line in input1) {
            if (line.isEmpty()) {
                parsingRanges = false
                continue
            }
            if (parsingRanges) {
                val range = line.split("-").map { it.toLong() }
                ranges.add(Range(range[0], range[1]))
            } else {
                val number = line.toLong()
                var isInRange = false
                for (range in ranges) {
                    if (number >= range.start && number <= range.end) {
                        isInRange = true
                        break
                    }
                }
                if (isInRange) {
                    freshCount++
                    println("Fresh number found: $number")
                }

            }
        }
        return freshCount
    }

    fun part2(): Long {
        val input2 = readInput(false, 5, true)
        val ranges = mutableListOf<Range>()
        var totalSum = 0L

        for (line in input2) {
            if (line.isEmpty()) {
                break
            }
            val numbers = line.split("-").map { it.toLong() }
            ranges.add(Range(numbers[0], numbers[1]))
        }

        ranges.sortBy { it.start }
        val mergedRanges = mutableListOf<Range>()
        var currentRange = ranges[0]
        for (i in 1 until ranges.size) {
            val range = ranges[i]
            if (range.start <= currentRange.end + 1) {
                currentRange = Range(currentRange.start, maxOf(currentRange.end, range.end))
            } else {
                mergedRanges.add(currentRange)
                currentRange = range
            }
        }
        mergedRanges.add(currentRange)

        mergedRanges.forEach { range ->
            totalSum += range.end - range.start + 1
        }

        return totalSum
    }

    println(part1())

    println(part2())

}