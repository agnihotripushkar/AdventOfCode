package org.devpush.com.aoc2025.day9

import org.devpush.com.aoc2025.readInput
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(){

    data class Point(val x: Int, val y: Int)

    fun isInsidePolygon(point: Point, polygon: List<Point>): Boolean {
        var inside = false
        var j = polygon.size - 1

        for (i in polygon.indices) {
            val xi = polygon[i].x
            val yi = polygon[i].y
            val xj = polygon[j].x
            val yj = polygon[j].y

            if ((yi > point.y) != (yj > point.y) &&
                point.x < (xj - xi) * (point.y - yi) / (yj - yi) + xi) {
                inside = !inside
            }
            j = i
        }

        return inside
    }

    fun part1(): Long {
        val input = readInput(false,9,false)
        val points = input.map{ line ->
            val arr = line.split(",").map { it.toInt() }
            Point(arr[0], arr[1])
        }
        var maxArea = 0L

        for (i in points.indices) {
            for (j in i+1 until points.size) {
                val p1 = points[i]
                val p2 = points[j]

                val width = abs(p1.x - p2.x) + 1L
                val height = abs(p1.y - p2.y) + 1L
                val area = width * height

                if (area > maxArea) {
                    maxArea = area
                }

            }
        }
        return maxArea

    }

    fun part2(): Long {
        val input = readInput(false, 9, true)
        val points = input.map { line ->
            val arr = line.split(",").map { it.trim().toInt() }
            Point(arr[0], arr[1])
        }

        var maxArea = 0L

        // Loop through every pair of points (P1 and P2)
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val p1 = points[i]
                val p2 = points[j]

                // 1. Calculate Geometric Area (No +1)
                // Using Long to prevent overflow during multiplication
                val width = abs(p1.x - p2.x).toLong()
                val height = abs(p1.y - p2.y).toLong()
                val currentArea = width * height

                // Optimization: Don't check blockers if this area is already too small
                // Also ignore flat lines (area 0)
                if (currentArea <= maxArea) continue

                val minX = min(p1.x, p2.x)
                val maxX = max(p1.x, p2.x)
                val minY = min(p1.y, p2.y)
                val maxY = max(p1.y, p2.y)

                var isBlocked = false

                // 2. Check for blockers
                for (k in points.indices) {
                    // Ignore the corners themselves
                    if (k == i || k == j) continue

                    val pk = points[k]

                    // STRICT INCLUSIVE CHECK
                    // If a point touches the Top, Bottom, Left, or Right edge, it blocks.
                    if (pk.x >= minX && pk.x <= maxX && pk.y >= minY && pk.y <= maxY) {
                        isBlocked = true
                        break
                    }
                }

                if (!isBlocked) {
                    maxArea = currentArea
                }
            }
        }
        return maxArea
    }

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}