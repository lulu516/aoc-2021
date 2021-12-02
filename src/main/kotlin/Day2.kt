class Day2 {
    private val regex = """(forward|down|up) (\d+)""".toRegex()

    fun part1(): Long {
        val xPos = mutableListOf<Int>()
        val yPos = mutableListOf<Int>()
        readLines(this).map {
            val (direction, posStr) = regex.find(it)!!.destructured
            val pos = posStr.toInt()
            when (direction) {
                "forward" -> xPos.add(pos)
                "down" -> yPos.add(pos)
                else -> yPos.add(-pos)
            }
        }
        return xPos.sum() * yPos.sum().toLong()
    }

    fun part2(): Long {
        val xPos = mutableListOf<Int>()
        val yPos = mutableListOf<Int>()
        var aim = 0
        readLines(this).map {
            val (direction, posStr) = regex.find(it)!!.destructured
            val pos = posStr.toInt()
            when (direction) {
                "forward" -> {
                    xPos.add(pos)
                    yPos.add(pos * aim)
                }
                "down" -> aim += pos
                else -> aim -= pos
            }
        }
        return xPos.sum() * yPos.sum().toLong()
    }
}