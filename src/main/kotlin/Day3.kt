class Day3 {
    fun part1(): Long {
        val input = readLines(this)
        val columns = mutableListOf<List<Char>>()
        input[0].indices.forEach { i -> columns.add(i, input.map { it[i] }) }
        val gRate = columns.map { getMostCommon(it) }.toCharArray().concatToString().fromBinary()
        val eRate = columns.map { getLeastCommon(it) }.toCharArray().concatToString().fromBinary()
        return gRate * eRate.toLong()
    }

    fun part2(): Long {
        val input = readLines(this)
        val o2Rate = input.toMutableList()
        val co2Rate = input.toMutableList()

        for (i in 0 until input[0].length) {
            val eachCount = o2Rate.map { it[i] }.groupingBy { it }.eachCount()
            val lastMostCommon = if (eachCount.values.distinct().size != 1) {
                eachCount.maxByOrNull { it.value }!!.key
            } else {
                '1'
            }
            if (o2Rate.size > 1) o2Rate.removeIf { row -> row[i] != lastMostCommon }
        }

        for (i in 0 until input[0].length) {
            val eachCount = co2Rate.map { it[i] }.groupingBy { it }.eachCount()
            val lastLeastCommon = if (eachCount.values.distinct().size != 1) {
                eachCount.minByOrNull { it.value }!!.key
            } else {
                '0'
            }
            if (co2Rate.size > 1) co2Rate.removeIf { row -> row[i] != lastLeastCommon }
        }

        return o2Rate.first().fromBinary() * co2Rate.first().fromBinary().toLong()
    }

    private fun getMostCommon(str: List<Char>): Char {
        val eachCount = str.groupingBy { it }.eachCount()
        return eachCount.maxByOrNull { it.value }!!.key
    }

    private fun getLeastCommon(str: List<Char>): Char {
        val eachCount = str.groupingBy { it }.eachCount()
        return eachCount.minByOrNull { it.value }!!.key
    }
}