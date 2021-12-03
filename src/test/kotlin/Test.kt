import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Test {

    @Test
    fun day1() {
        val sut = Day1()
        assertEquals(7, sut.part1())
        assertEquals(5, sut.part2())
    }

    @Test
    fun day2() {
        val sut = Day2()
        assertEquals(150, sut.part1())
        assertEquals(900, sut.part2())
    }

    @Test
    fun day3() {
        val sut = Day3()
        assertEquals(198, sut.part1())
        assertEquals(230, sut.part2())
    }
}