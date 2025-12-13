package org.devpush.com.aoc2025.day11

import org.devpush.com.aoc2025.readInput

fun main() {
    println("Day 11 of Advent of Code 2025 - DAG with DFS and Cache")

    fun part1(): Long {
        val graph = mutableMapOf<String, List<String>>()
        val input = readInput(false, 11, false)
        for (line in input) {
            if (line.isBlank()) continue

            //aaa: you hhh
            val parts = line.split(": ")
            val source = parts[0].trim()

            if (parts.size > 1) {
                val destinations = parts[1].trim().split(" ")
                graph[source] = destinations
            }
        }

        // 2. Memoization Cache
        // Maps NodeName -> Number of paths to "out"
        val memo = mutableMapOf<String, Long>()

        // 3. DFS Function
        fun countPaths(current: String): Long {
            // Base Case: We reached the target
            if (current == "out") return 1L

            // Check Cache
            if (memo.containsKey(current)) {
                return memo[current]!!
            }

            // Recursive Sum
            var totalPaths = 0L
            val children = graph[current]

            if (children != null) {
                for (child in children) {
                    totalPaths += countPaths(child)
                }
            }

            // Store and Return
            memo[current] = totalPaths
            return totalPaths
        }

        // Start from "you"
        return countPaths("you")
    }

    fun part2(): Long {
        val rawInput = readInput(false, 11, true)

        val graph = mutableMapOf<String, List<String>>()

        for (line in rawInput) {
            // SAFETY 1: Ignore blank lines
            if (line.isBlank()) continue

            // SAFETY 2: Split by colon, but trim both sides immediately
            val parts = line.split(":")
            val source = parts[0].trim()

            if (parts.size > 1) {
                val destString = parts[1].trim()
                if (destString.isNotEmpty()) {
                    // SAFETY 3: Use Regex to split by "one or more whitespace characters"
                    // This handles tabs, single spaces, and double spaces.
                    val destinations = destString.split("\\s+".toRegex())
                    graph[source] = destinations
                }
            }
        }

        val node1 = "dac"
        val node2 = "fft"
        val start = "svr"
        val end = "out"

        val memo = mutableMapOf<String, Long>()

        fun countPaths(current: String, target: String): Long {
            if (current == target) return 1L

            // --- FIX 2: Safe Cache Key ---
            // Use a separator to avoid "ab" + "c" colliding with "a" + "bc"
            val key = "$current|$target"

            if (memo.containsKey(key)) return memo[key]!!

            var paths = 0L
            graph[current]?.forEach { child ->
                paths += countPaths(child, target)
            }

            memo[key] = paths
            return paths
        }

        // Logic to detect order
        val paths1to2 = countPaths(node1, node2) // dac -> fft
        val paths2to1 = countPaths(node2, node1) // fft -> dac

        println("Path $node1->$node2: $paths1to2")
        println("Path $node2->$node1: $paths2to1")

        if (paths1to2 > 0) {
            // Order: start -> node1 -> node2 -> end
            val s1 = countPaths(start, node1)
            val s2 = paths1to2
            val s3 = countPaths(node2, end)
            return s1 * s2 * s3
        } else if (paths2to1 > 0) {
            // Order: start -> node2 -> node1 -> end
            val s1 = countPaths(start, node2) // This was returning 0 before!
            val s2 = paths2to1
            val s3 = countPaths(node1, end)

            // Debugging print
            println("Segments: $s1 * $s2 * $s3")

            return s1 * s2 * s3
        } else {
            // This means neither node can reach the other (Parallel branches?)
            // The problem implies a single valid chain exists, so this is an error case.
            return 0L
        }
    }

    fun part2Test(): Long {
        // 1. The specific test input provided
        val inputString = readInput(true, 11, true)

        val graph = mutableMapOf<String, List<String>>()

        // 2. Parse
        inputString.forEach { line ->
            if (line.isNotBlank()) {
                val parts = line.split(":")
                val source = parts[0].trim()
                val destinations = parts[1].trim().split("\\s+".toRegex())
                graph[source] = destinations
            }
        }

        // 3. Define Nodes (Note: Start is "svr" here!)
        val start = "svr"
        val end = "out"
        val node1 = "dac"
        val node2 = "fft"

        // 4. Memoized Path Counter
        val memo = mutableMapOf<String, Long>()

        fun countPaths(current: String, target: String): Long {
            if (current == target) return 1L
            val key = "$current|$target"
            if (memo.containsKey(key)) return memo[key]!!

            var paths = 0L
            graph[current]?.forEach { child ->
                paths += countPaths(child, target)
            }

            memo[key] = paths
            return paths
        }

        // 5. Calculate Segments
        val paths1to2 = countPaths(node1, node2) // dac -> fft
        val paths2to1 = countPaths(node2, node1) // fft -> dac

        if (paths1to2 > 0) {
            // Order: start -> dac -> fft -> end
            val s1 = countPaths(start, node1)
            val s2 = paths1to2
            val s3 = countPaths(node2, end)
            return s1 * s2 * s3
        } else if (paths2to1 > 0) {
            // Order: start -> fft -> dac -> end
            // This is the case for your input!
            val s1 = countPaths(start, node2)
            val s2 = paths2to1
            val s3 = countPaths(node1, end)

            println("Segment 1 (svr->fft): $s1")
            println("Segment 2 (fft->dac): $s2")
            println("Segment 3 (dac->out): $s3")

            return s1 * s2 * s3
        }

        return 0L
    }

    println(part1())
    println(part2())
//    println("Answer: ${part2Test()}")
}
