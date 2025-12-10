package org.devpush.com.aoc2025.day9

import org.devpush.com.aoc2025.readInput
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(){

    data class Point(val x: Long, val y: Long)

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
            val arr = line.split(",").map { it.toLong() }
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
            val arr = line.split(",").map { it.trim().toLong() }
            Point(arr[0], arr[1])
        }

        val lines = ArrayList<Pair<Point, Point>>()

        for (i in points.indices) {
            val p1 = points[i]
            val p2 = points[(i + 1) % points.size] // Wrap around to close the loop
            lines.add(p1 to p2)
        }

        var maxArea = 0L

        // Loop through every pair of points (P1 and P2)
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val p1 = points[i]
                val p2 = points[j]

                // 1. Calculate Geometric Area (No +1)
                // Using Long to prevent overflow during multiplication
                val width = abs(p1.x - p2.x) +1
                val height = abs(p1.y - p2.y) + 1
                val currentArea = width * height

                // Optimization: Don't check blockers if this area is already too small
                // Also ignore flat lines (area 0)
                if (currentArea <= maxArea) continue

                val boxMinX = min(p1.x, p2.x)
                val boxMaxX = max(p1.x, p2.x)
                val boxMinY = min(p1.y, p2.y)
                val boxMaxY = max(p1.y, p2.y)

                var isValid = true

                // 2. Check for blockers
                for ((l1, l2) in lines) {
                    val lineMinX = min(l1.x, l2.x)
                    val lineMaxX = max(l1.x, l2.x)
                    val lineMinY = min(l1.y, l2.y)
                    val lineMaxY = max(l1.y, l2.y)

                    // The box is valid ONLY if it is completely
                    // to one side of the line (Left, Right, Above, or Below).

                    // Is the Box strictly to the Left of the line segment?
                    // (Rightmost edge of box <= Leftmost edge of line)
                    val isBoxLeft = boxMaxX <= lineMinX

                    // Is Box to the Right?
                    val isBoxRight = boxMinX >= lineMaxX

                    // Is Box Above? (Assuming Y axis logic matches inputs)
                    val isBoxAbove = boxMaxY <= lineMinY

                    // Is Box Below?
                    val isBoxBelow = boxMinY >= lineMaxY

                    // If the box is NOT separated from the line, it intersects/overlaps.
                    if (!isBoxLeft && !isBoxRight && !isBoxAbove && !isBoxBelow) {
                        isValid = false
                        break
                    }
                }

                if (isValid) {
                    maxArea = currentArea
                }
            }
        }
        return maxArea
    }

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}