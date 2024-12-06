fun main() {
    fun findStart(input: List<String>): Pair<Int, Int> {
        input.forEachIndexed { index, line ->
            if (line.indexOf("^") != -1) {
                return Pair(index, line.indexOf('^'))
            }
        }
        return Pair(0, 0) // should never trigger
    }

    fun part1(input: List<String>): Int {
        var (row, column) = findStart(input)
        val map: List<MutableList<Int>> = List(input.size) { MutableList(input.size) {0} }
        map[row][column] = 1
        /* directions:
            N = 0
            E = 1
            S = 2
            W = 3
         */
        var direction = 0
        while (true) {
            try {
                when (direction) {
                    0 -> row--
                    1 -> column++
                    2 -> row++
                    3 -> column--
                }
                if (input[row][column] == '#') {
                    when (direction) {
                        0 -> {
                            row++
                            column++
                        }
                        1 -> {
                            column--
                            row++
                        }
                        2 -> {
                            row--
                            column--
                        }
                        3 -> {
                            column++
                            row--
                        }
                    }
                    direction++
                }
                map[row][column] = 1

                if (direction == 4) direction = 0
            } catch (e: Exception) {break}
        }

        return map.flatten().sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
