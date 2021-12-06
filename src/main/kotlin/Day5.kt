import kotlin.math.abs

class Day5 {
    private val regex = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()

    // 8060
    fun part1(): Int {
        return readLines(this).map {
            val (x1, y1, x2, y2) = regex.find(it)!!.destructured
            coordinatesBetween(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
        }
            .filter { it.isNotEmpty() }
            .flatten()
            .groupingBy { it }
            .eachCount()
            .filter { it.value >= 2 }
            .count()
    }

    // 21577
    fun part2(): Int {
        return readLines(this).map {
            val (x1, y1, x2, y2) = regex.find(it)!!.destructured
            coordinatesBetween(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt(), true)
        }
            .filter { it.isNotEmpty() }
            .flatten()
            .groupingBy { it }
            .eachCount()
            .filter { it.value >= 2 }
            .count()
    }

    private fun coordinatesBetween(
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int,
        diagonalLine: Boolean = false
    ): List<Pair<Int, Int>> {
        return if (x1 == x2) {
            (0..abs(y1 - y2)).map { Pair(x1, y1 + it * multiplier(y1, y2)) }.toList()
        } else if (y1 == y2) {
            (0..abs(x1 - x2)).map { Pair(x1 + it * multiplier(x1, x2), y1) }.toList()
        } else if (diagonalLine && abs(x1 - x2) == abs(y1 - y2)) {
            (0..abs(x1 - x2)).map { Pair(x1 + it * multiplier(x1, x2), y1 + it * multiplier(y1, y2)) }
        } else emptyList()
    }

    private fun multiplier(n1: Int, n2: Int): Int = if (n1 > n2) -1 else 1
}