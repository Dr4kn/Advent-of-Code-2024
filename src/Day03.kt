fun main() {
    fun part1(input: List<String>): Int {
        val matcher = "mul\\([0-9]+,[0-9]+\\)".toRegex()
        return matcher
            .findAll(input.toString())
            .map { it.value }
            .toList()
            .map { it.substring(4,it.lastIndex)
                .split(",")
                .map { it.toInt() }
                .zipWithNext { a, b ->  a * b}}
            .flatten()
            .reduce { acc, ints ->  acc + ints}
    }

    fun part2(input: List<String>): Int {
        var enabled = true

        val filteredInput = "mul\\([0-9]+,[0-9]+\\)|do\\(\\)|don't\\(\\)"
            .toRegex()
            .findAll(input.toString())
            .map { it.value }
            .toList()
            .map { it.replace("mul|\\(|\\)".toRegex(), "")}

        var result = 0

        filteredInput.forEach { instruction ->
            when(instruction) {
                "do" -> enabled = true
                "don't" -> enabled = false
                else -> if (enabled) {
                    result += instruction.split(",")
                        .map { it.toInt() }
                        .zipWithNext{a, b -> a * b}
                        .first()
                }
            }
            if (instruction == "do") enabled
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    //check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
