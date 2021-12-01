import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Test {
    private val day1: Day1 = Day1()

    @Test
    fun day1() {
        assertEquals(7, day1.part1())
        assertEquals(5, day1.part2())
    }
}