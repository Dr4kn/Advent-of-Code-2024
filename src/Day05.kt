fun main() {

    fun part1(input: String): Int {
        val orderingRules = MultiMap<Int, Int>()
        val parsed = input.split("\n\n")
            .map { it.trim().lines() }

        parsed[0].map { line ->
            val pair = line.split("|")
                .map { it.toInt() }
            orderingRules.put(pair[0], pair[1])
        }

        var middlePages = 0

        parsed[1].map { line ->
            line.split(",")
                .map { it.toInt() }
        }.forEach { pages ->
            var correctOrder = true
            for (i in 1..< pages.size) {
                val before = pages.subList(0, i)
                val numbersAfter = orderingRules[pages[i]]
                numbersAfter.forEach { n ->
                    if (before.contains(n)) {
                        correctOrder = false
                    }
                }
                if (!correctOrder) break
            }
            if (correctOrder) middlePages += pages[pages.size/2]
        }

        return middlePages
    }

    fun part2(input: String): Int {
        return 0
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readRawInput("Day05_test")
    check(part1(testInput) == 143)

    // Read the input from the `src/Day01.txt` file.
    val input = readRawInput("Day05")
    part1(input).println()
    part2(input).println()
}

