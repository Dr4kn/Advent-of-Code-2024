import kotlin.math.abs

fun main() {
    fun parse(input: List<String>): List<List<Int>> {
        return input.map { line ->
            line.split(" ")
                .map { number -> number.toInt() }
        }
    }
    fun part1(input: List<String>): Int {
        val parsed = parse(input)
        var result = 0
        parsed.forEach { line ->
            if (line == line.sorted() || line == line.sortedDescending()) {
                val adjacentLevels = line
                    .windowed(2, 1)
                    .map { comparison -> abs(comparison[0] - comparison[1]) }
                    .sorted()
                if (adjacentLevels.first() in 1..3 && adjacentLevels.last() in 1..3) {
                    result++
                }
            }
        }
        return result
    }

    fun isReportSafe(report: MutableList<Int>): Boolean {
        val levels = report.zipWithNext { a, b ->  b - a}
        return levels.all { level -> level in 1..3 } || levels.all {  level -> level in -3..-1 }
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for (report in input) {
            val numbers = report.split(" ").map { it.toInt() }
            var isSafe = false
            for (i in 0..numbers.lastIndex) {
                isSafe = isReportSafe(numbers.toMutableList().apply { removeAt(i) })
                if (isSafe) break
            }
            if (isSafe) result++
        }
        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
