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

    @Test
    fun day4() {
        val sut = Day4()
        assertEquals(4512, sut.part1())
        assertEquals(1924, sut.part2())
    }

    @Test
    fun day5() {
        val sut = Day5()
        assertEquals(5, sut.part1())
        assertEquals(12, sut.part2())
    }

    @Test
    fun day6() {
        val sut = Day6()
        assertEquals(26, sut.part1(18))
        assertEquals(5934, sut.part1(80))
        assertEquals(26984457539, sut.part2(256))
    }

    @Test
    fun day7() {
        val sut = Day7()
        assertEquals(37, sut.part1())
        assertEquals(168, sut.part2())
    }

    @Test
    fun day8() {
        val sut = Day8()
        assertEquals(1, sut.part1())
        assertEquals(1, sut.part2())
    }
}