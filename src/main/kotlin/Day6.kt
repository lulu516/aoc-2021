class Day6 {
    // 360761
    fun part1(days: Int): Int {
        val fish = readLine(this, ",").map { LanternFish(it.toInt()) }
            .toMutableList()
        (1..days).forEach { _ ->
            val newFish = mutableListOf<LanternFish>()
            fish.forEach {
                if (it.counter == 0) {
                    it.counter = 6
                    newFish.add(LanternFish(8))
                } else {
                    it.counter--
                }
            }
            fish.addAll(newFish)
        }
        return fish.count()
    }

    // 1632779838045
    fun part2(days: Int): Long {
        var fish = readLine(this, ",").map { it.toInt() }.groupBy { it }.mapValues { it.value.size.toLong() }

        (1..days).forEach { _ ->
            val temp = mutableMapOf<Int, Long>()
            // sorted by descending so that the count of 0 -> 6 fish can be summed with 7 -> 6 fish
            fish.keys.sortedByDescending { it }.forEach {
                if (it == 0) {
                    temp[6] = temp.getOrDefault(6, 0) + fish[0]!!
                    temp[8] = fish[0]!!
                } else {
                    temp[it - 1] = fish[it]!!
                }
            }
            fish = temp
        }
        return fish.values.sum()
    }

    data class LanternFish(var counter: Int)
}