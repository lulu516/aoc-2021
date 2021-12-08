class Day8 {
    // 409
    fun part1(): Int {
        val patternToOutput = readLines(this).associate {
            val row = it.split(" | ").map { r -> r.split(" ") }
            row.first() to row.last()
        }

        return patternToOutput.values.flatten().count { it.length in listOf(2, 3, 4, 7) }
    }

    // 1024649
    fun part2(): Int {
        val patternToOutput = readLines(this).associate {
            val row = it.split(" | ").map { r -> r.split(" ") }
            row.first() to row.last()
        }

        val patternToOutputDigit = patternToOutput.mapValues {
            val map = patternToDigit(it.key)
            it.value.map { v -> map[v.toSortedSet().joinToString()]!! }.concatToInt()
        }

        return patternToOutputDigit.values.sum()
    }

    private fun patternToDigit(keys: List<String>): Map<String, Int> {
        val map = mutableMapOf<Int, String>()
        // 1, 4, 7, 8 use unique amount of segments
        keys.filter { it.length in listOf(2, 3, 4, 7) }.forEach {
            when (it.length) {
                2 -> map[1] = it
                3 -> map[7] = it
                4 -> map[4] = it
                else -> map[8] = it
            }
        }

        keys.filterNot { it.length in listOf(2, 3, 4, 7) }.forEach {
            // 0, 6, 9 use 6 segments.
            if (it.length == 6) {
                // Segments for 1 are all used in both 0 and 9, so except 6
                if (!it.containsAll(map[1]!!)) map[6] = it
                // Segments for 4 are all used in 9, so 0 can be excluded
                else if (it.containsAll(map[4]!!)) map[9] = it
                else map[0] = it
            } else if (it.containsAll(map[1]!!)) { // 2, 3, 5 are left. Segments for 1 are all used in 3, not 2 and 5.
                map[3] = it
            } else {
                // The diff between 1 and 4 are also used in 5 and not in 2.
                val bd = map[4]!!.filterNot { l -> map[1]!!.contains(l) }
                if (it.containsAll(bd)) map[5] = it
                else map[2] = it
            }

        }
        return map.entries.associateBy({ it.value.toSortedSet().joinToString() }) { it.key }
    }
}