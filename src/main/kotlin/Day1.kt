class Day1 {

    fun part1(): Int {
        val input = readLines(this).map { it.toInt() }
        return countIncrease(input)
    }

    fun part2(): Int {
        val input = readLines(this).map { it.toInt() }
        val windowList = mutableListOf<Int>()
        for (i in 0..input.size - 3) {
            windowList.add(i, input[i] + input[i + 1] + input[i + 2])
        }
        return countIncrease(windowList)
    }

    private fun countIncrease(list: List<Int>): Int {
        var counter = 0
        for (i in 1 until list.size) {
            if (list[i] > list[i - 1]) counter++
        }
        return counter
    }
}