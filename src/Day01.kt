import kotlin.math.abs

fun main() {
    fun parse(input: List<String>): List<List<Int>> {
        return input.map { line ->
            line.split("   ").map { number ->
                number.toInt()
            }
        }
    }

    fun part1(input: List<String>): Int {
        val parsed = parse(input)

        val left = parsed.map { line -> line[0] }.sorted()
        val right = parsed.map { line -> line[1] }.sorted()
        var sum = 0
        left.forEachIndexed { index, line ->
            sum += abs(line - right[index])
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val parsed = parse(input)
        val right = HashMap<Int, Int>()
        parsed.forEach { number ->
            right[number[1]] = right.getOrDefault(number[1], 0) + 1

        }
        var sum = 0
        parsed.forEach { number ->
            if (right[number[0]] != null) {
                sum += number[0] * right[number[0]]!!
            }
        }
        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
