class Day14 {
    private val regex = """(\S+) -> (\S)""".toRegex()
    private val rules = readLines(this).drop(2).associate {
        val (s, i) = regex.find(it)!!.destructured
        s.toPair() to i[0]
    }
    private val template = readLines(this).first()


    // 4244
    fun part1(): Int {
        var result = template.toCharArray().toList()
        repeat(10) { result = insert(result) }

        val eachCount = result.groupingBy { it }.eachCount()
        val leastCommon = eachCount.minByOrNull { it.value }
        val mostCommon = eachCount.maxByOrNull { it.value }
        return mostCommon!!.value - leastCommon!!.value
    }

    // 4807056953866
    fun part2(): Long {
        var pairCounter = mutableMapOf<Pair<Char, Char>, Long>()

        (0..template.length - 2).forEach { i ->
            val pair = Pair(template[i], template[i + 1])
            pairCounter[pair] = pairCounter.getOrDefault(pair, 0) + 1
        }

        repeat(40) {
            pairCounter = countAfterInsert(pairCounter)
        }

        val charCounter = mutableMapOf<Char, Long>()
        pairCounter.forEach { (pair, count) ->
            if (pair.first == pair.second) {
                charCounter[pair.first] = charCounter.getOrDefault(pair.first, 0) + count * 2
            } else {
                charCounter[pair.first] = charCounter.getOrDefault(pair.first, 0) + count
                charCounter[pair.second] = charCounter.getOrDefault(pair.second, 0) + count
            }
        }
        // All chars in between (excluding first and last) are counted twice, so add 1 more for first and last chars.
        charCounter[template.first()] = charCounter[template.first()]!! + 1
        charCounter[template.last()] = charCounter[template.last()]!! + 1

        val sortedCounter = charCounter.values.sortedBy { it }

        // All chars are counted twice, so we need to divide 2.
        return sortedCounter.last() / 2 - sortedCounter.first() / 2
    }

    private fun insert(str: List<Char>): List<Char> {
        val after = str.toMutableList()
        var offset = 0
        (0..str.size - 2).forEach { i ->
            val pair = Pair(str[i], str[i + 1])
            if (rules.containsKey(pair)) {
                after.add(i + 1 + offset, rules[pair]!!)
                offset++
            }
        }
        return after
    }

    private fun countAfterInsert(counter: Map<Pair<Char, Char>, Long>): MutableMap<Pair<Char, Char>, Long> {
        val newPairCounter = mutableMapOf<Pair<Char, Char>, Long>()
        counter.keys.forEach { pair ->
            if (rules.containsKey(pair)) {
                val toInsert = rules.getValue(pair)
                val left = Pair(pair.first, toInsert)
                val right = Pair(toInsert, pair.second)
                newPairCounter[left] = newPairCounter.getOrDefault(left, 0L) + counter[pair]!!
                newPairCounter[right] = newPairCounter.getOrDefault(right, 0L) + counter[pair]!!
            } else {
                newPairCounter[pair] = counter[pair]!!
            }
        }
        return newPairCounter
    }
}