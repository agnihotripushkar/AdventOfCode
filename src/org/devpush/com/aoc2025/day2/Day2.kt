package org.devpush.com.aoc2025.day2

import org.devpush.com.aoc2025.readWholeinput

fun main(){
    fun part1(): Long{
        val input = readWholeinput(false,2,false)
        val input1 = input.split(",").map{it.trim()}
        var ans = 0L
        input1.forEach {
            val range = it.split("-")
            val start = range[0].toLong()
            val end = range[1].toLong()
            for(i in start..end){
                val str = i.toString()
                if (str.length%2==0){
                    val middleIndex = str.length/2
                    val firstHalf = str.take(middleIndex)
                    val secondHalf = str.takeLast(middleIndex)
                    if(firstHalf==secondHalf){
                        println("Part1: $i")
                        ans +=i
                    }
                }
            }
        }
        return ans
    }

    fun part2(): Long{
        val input = readWholeinput(false,2,true)
        val sum = input.split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .flatMap { range ->
                val (start, end) = range.split("-").map { it.toLong() }
                (start..end).filter { id ->
                    val idStr = id.toString()
                    (1..(idStr.length / 2)).any { patternLen ->
                        idStr.length % patternLen == 0 &&
                                idStr.chunked(patternLen).let { chunks ->
                                    chunks.all { it == chunks.first() }
                                }
                    }
                }
            }
            .sumOf { it }

        return sum
    }

    println(part1())
    println(part2())


}