import kotlin.math.abs

class Day7 {
    // 344297
    fun part1(): Int {
        val input = readLine(this, ",").map { it.toInt() }.groupingBy { it }.eachCount()
        val counter = mutableMapOf<Int, Int>()
        val currentPos = input.keys.sorted()
        (currentPos.first()..currentPos.last()).forEach { pos ->
            counter[pos] = input.map { abs(it.key - pos) * it.value }.sum()
        }

        return counter.minByOrNull { it.value }!!.value
    }

    // 97164301
    fun part2(): Int {
        val input = readLine(this, ",").map { it.toInt() }.groupingBy { it }.eachCount()
        val counter = mutableMapOf<Int, Int>()
        val currentPos = input.keys.sorted()
        (currentPos.first()..currentPos.last()).forEach { pos ->
            counter[pos] = input.map { abs(it.key - pos).sigma() * it.value }.sum()
        }

        return counter.minByOrNull { it.value }!!.value
    }
}