fun main() {
    fun part1(input: List<String>): Int {
        val matcher = "XMAS|SAMX".toRegex()
        var occurrences = 0
        for (row in input.indices) {
            for (column in input.indices) {
                // horizontal check
                if (row + 3 < input.size) {
                    if (input[column].substring(row, row + 4).contains(matcher)) occurrences++
                }
                // vertical check
                if (column + 3 < input.size) {
                    var vertical = ""
                    for (i in column..column + 3) {
                        vertical += input[i][row]
                    }
                    if (vertical.contains(matcher)) occurrences++
                }
                // diagonal right \
                if (column + 3 < input.size && row + 3 < input.size) {
                    var diagonal = ""
                    for (i in 0..3) {
                        diagonal += input[column + i][row + i]
                    }
                    if (diagonal.contains(matcher)) occurrences++
                }
                // diagonal left /
                if (column - 3 >= 0 && row + 3 < input.size) {
                    var diagonal = ""
                    for (i in 0..3) {
                        diagonal += input[column - i][row + i]
                    }
                    if (diagonal.contains(matcher)) occurrences++
                }
            }
        }
        return occurrences
    }

    fun part2(input: List<String>): Int {
        val matcher = "MAS|SAM".toRegex()
        var occurrences = 0

        for (row in 1..input.size - 2) {
            for (column in 1..input.size - 2) {
                // /
                val diagonalLeft = "" + input[column + 1][row - 1] + input[column][row] + input[column - 1][row + 1]
                // \
                val diagonalRight = "" + input[column - 1][row - 1] + input[column][row] + input[column + 1][row + 1]

                if (diagonalLeft.contains(matcher) && diagonalRight.contains(matcher)) occurrences++
            }
        }
        return occurrences
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

